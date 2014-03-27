package unit.com.codurance.socialnetworking.command

import unit.com.codurance.socialnetworking.UnitSpec
import com.codurance.socialnetworking.command.{PostCommand, CommandFactory}
import com.codurance.socialnetworking.domain.Users

class CommandFactorySpec extends UnitSpec {

	"CommandFactory" should "return None when there is not a matching command" in new context {
		commandFactory commandFor INVALID_COMMAND should be (None)
	}

	it should "return a PostCommand" in new context {
		val command = commandFactory commandFor USER_POST_COMMAND

		command.get shouldBe a [PostCommand]
	}

	trait context {
		val users = mock[Users]
		val commandFactory = new CommandFactory(users)
	}

	val INVALID_COMMAND = "invalid_command"
	val USER_POST_COMMAND = "Alice -> Hello"

}
