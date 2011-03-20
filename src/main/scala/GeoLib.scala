

package no.aslakjo
package geolib

import math._

case class Point(lat:Double, long:Double) extends Haversine{

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
    var last:Point = null

    points.foreach(point => {

      if(last == null){
        last = point
      }
      else{
        sum = sum + last.to(point).distance.km
        last = point
      }

    })

    Distance(sum)
  }
}
object Path{
  def apply(points:List[Point]) = new Path(points.toList)
}


case class Distance(val km:Double)
case class Bearing(val angel:Double)

case class GeoVektor(a:Point, b:Point){
  def distance = a.distanceTo(b)
  def bearing = a.bearingTo(b)
}