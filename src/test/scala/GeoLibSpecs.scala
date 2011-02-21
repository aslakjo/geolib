import _root_.no.aslakjo.geolib.{Path, Point, Distance}
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

    describe ("distance"){
      it ("should be 17.57 km from a to b"){
        val pointA = Point(59.917171, 10.746002)
        val pointB = Point(59.962574, 11.048126)

        pointA.distanceTo(pointB) should be(Distance(17.57))
      }
    }
  }

}