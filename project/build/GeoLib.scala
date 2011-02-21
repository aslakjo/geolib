
/**
 * Created by IntelliJ IDEA.
 * User: aslak johannessen
 * Date: 2/15/11
 * Time: 9:18 PM
 * To change this template use File | Settings | File Templates.
 */
import sbt._

class GeoLib(info:ProjectInfo) extends DefaultProject(info){
  val scalatest = "org.scalatest" % "scalatest" % "1.3"
}