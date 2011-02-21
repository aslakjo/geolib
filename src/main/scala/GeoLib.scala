

package no.aslakjo
package geolib

case class Point(lat:Double, long:Double){
  def distanceTo(other:Point): Distance = {
    import math._

    var R = 6371; // km
    var dLat = toRadians(other.lat - lat);
    var dLon = toRadians(other.long - long);
    var a = sin(dLat/2) * sin(dLat/2) +
          cos(toRadians(lat)) * cos(toRadians(other.lat)) *
          sin(dLon/2) * sin(dLon/2);
    var c = 2 * atan2(sqrt(a), sqrt(1-a));
    var d = R * c;
    Distance((d * 100).round / 100.0)
  }
}


class Path(val points:List[Point])
object Path{
  def apply(points:List[Point]) = new Path(points.toList)
}


case class Distance(val km:Double)