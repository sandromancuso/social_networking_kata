package unit.com.codurance.socialnetworking.user_interface

import org.mockito.Mockito._
import org.mockito.BDDMockito._

import com.codurance.socialnetworking.user_interface.UserCommands
import unit.com.codurance.socialnetworking.UnitSpec
import com.codurance.socialnetworking.command.{Command, CommandFactory}

class UserCommandsSpec extends UnitSpec {

	val USER_POST_COMMAND = "Alice -> Hello"

	"UserCommands" should "return None when there is no matching command" in new context {
		given(commandFactory commandFor USER_POST_COMMAND) willReturn None

		userCommands execute USER_POST_COMMAND should be(None)

		verify(commandFactory) commandFor USER_POST_COMMAND
	}

	it should "execute a command when there is a matching one" in new context {
		given(commandFactory commandFor USER_POST_COMMAND) willReturn Some(postCommand)

		userCommands execute USER_POST_COMMAND

		verify(postCommand).execute(USER_POST_COMMAND)
	}

	trait context {
		val commandFactory = mock[CommandFactory]
		val postCommand = mock[Command]
		val userCommands = new UserCommands(commandFactory)
	}

}
