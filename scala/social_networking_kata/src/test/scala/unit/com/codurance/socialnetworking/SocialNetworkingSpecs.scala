package unit.com.codurance.socialnetworking

import com.codurance.socialnetworking.user_interface.Console
import org.mockito.Mockito._
import com.codurance.socialnetworking.SocialNetworking

class SocialNetworkingSpecs extends UnitSpec {

	"SocialNetworking" should "read from the console" in {
		val console = mock[Console]
		val socialNetworking = new SocialNetworking(console)

		socialNetworking.start

		verify(console).readline()
	}

}
