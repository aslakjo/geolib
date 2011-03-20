

package no.aslakjo
package object geolib{



  implicit def distance2km(distance: Distance) = distance.km

  implicit def lat2double(lat:Lat) = lat.double
  implicit def double2lat(double:Double) = Lat(double)
  implicit def lat2double(long:Long) = long.double
  implicit def double2long(double:Double) = Long(double)

}

package geolib{
    import math._

    case class Point(lat:Lat, long:Long) extends Haversine{

    def distanceTo(other:Point): Distance = {
      val distance = haversine(this, other)
      Distance((distance * 100).round / 100.0)
    }


    def bearingTo(other: Point) ={
      val dLon = long - other.long

      var y = sin(dLon) * cos(other.lat);
      var x = cos(lat) * sin(other.lat) -
              sin(lat) * cos(other.lat) * cos(dLon);
      var bearing = toDegrees(atan2(y, x))

      Bearing(bearing.round)
    }


    def to(other:Point) = new GeoVektor(this, other)

  }

  trait Haversine {
    val EARTH_RADIUS = 6371; // km
    def haversine(a: Point, b: Point): Double = {

      val dLat = toRadians(b.lat - a.lat);
      val dLon = toRadians(b.long - a.long);
      val haversinA = sin(dLat/2) * sin(dLat/2) + cos(toRadians(a.lat)) * cos(toRadians(b.lat)) * sin(dLon/2) * sin(dLon/2);
      val c = 2 * atan2(sqrt(haversinA), sqrt(1-haversinA));
      EARTH_RADIUS * c;
    }
  }


  class Path(val points:List[Point]){
    def distance = {
      var sum = 0.0
      points.foldRight[Point](null)( (next:Point, last:Point) => {
        if(last != null && next != null){
          sum = sum + last.to(next).distance.km
        }

        next
      })

      Distance(sum)
    }
  }
  object Path{
    def apply(points:List[Point]) = new Path(points.toList)
  }

  case class Lat(val double:Double)
  case class Long(val double:Double)

  case class Distance(val km:Double)
  case class Bearing(val angel:Double)

  case class GeoVektor(a:Point, b:Point){
    def distance = a.distanceTo(b)
    def bearing = a.bearingTo(b)
  }
}