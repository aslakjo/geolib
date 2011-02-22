import _root_.no.aslakjo.geolib._
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec


class GeoLibSpecs extends Spec with ShouldMatchers{

  describe ("GeoLib") {
    describe ("Point") {
      it ("should have a lat and a long"){
        val point = new Point(10.1000, 60.1000)

        point.lat should be (10.1000)
        point.long should be (60.1000)
      }
    }

    describe("Path"){
      it("should hold multiple points"){
        val start = Point(10.1000, 60.1000)
        val slutt = Point(10.1001, 60.1001)

        val path = Path(start :: slutt :: Nil )
      }
    }

    describe ("Distance"){
      it ("should be 17.57 km from a to b"){
        val pointA = Point(59.917171, 10.746002)
        val pointB = Point(59.962574, 11.048126)

        pointA.distanceTo(pointB) should be(Distance(17.57))
      }

      it ("no distance should be 0"){
        val pointA = Point(59.917171, 10.746002)
        pointA.distanceTo(pointA) should be(Distance(0))
      }
    }

    describe("Bearing") {
      it ("a strait northenly path should have a bearing of 0")  {
        val south = Point(59.947231, 10.820117)
        val north = Point(59.948231, 10.820117)

        south.bearingTo(north) should be(Bearing(0))
      }

      it ("a strait eastern path should have a pearing of 0") {
        val east = Point(59.947231, 10.820117)
        val west = Point(59.947231, 10.821117)

        east.bearingTo(west) should be(Bearing(90))
      }
    }
  }

}