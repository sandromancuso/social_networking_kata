package unit.com.codurance.socialnetworking.user_interface

import org.mockito.BDDMockito._
import org.mockito.Mockito._

import unit.com.codurance.socialnetworking.UnitSpec
import com.codurance.socialnetworking.user_interface.{View, Console}

class ViewSpec extends UnitSpec {

	"View" should "read commands from the console" in new context {
		given(console readline()) willReturn USER_COMMAND

		view userCommand() should be(USER_COMMAND)

		verify(console) readline()
	}

	trait context {
		val console = mock[Console]
		val view = new View(console)
	}

	val USER_COMMAND = "A user command"

}
