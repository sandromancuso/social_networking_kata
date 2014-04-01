package unit.com.codurance.socialnetworking.infrastructure

import unit.com.codurance.socialnetworking.UnitSpec
import com.codurance.socialnetworking.infrastructure.Clock
import java.util.Date

class ClockSpec extends UnitSpec {

	"Clock" should "return the current time" in {
		Clock.now() shouldBe a [Date]
	}
	
}
