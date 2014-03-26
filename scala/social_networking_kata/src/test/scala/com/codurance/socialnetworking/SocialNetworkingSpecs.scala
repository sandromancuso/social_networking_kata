package com.codurance.socialnetworking

import com.codurance.socialnetworking.user_interface.Console
import org.mockito.Mockito._

class SocialNetworkingSpecs extends UnitSpec {

	"SocialNetworking" should "read from the console" in {
		val console = mock[Console]
		val socialNetworking = new SocialNetworking(console)

		socialNetworking.start

		verify(console).readline()
	}

}
