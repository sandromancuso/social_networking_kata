package unit.com.codurance.socialnetworking.command

import unit.com.codurance.socialnetworking.UnitSpec
import com.codurance.socialnetworking.command.{PostCommand, CommandFactory}

class CommandFactorySpec extends UnitSpec {

	"CommandFactory" should "return None when there is not a matching command" in {
		val commandFactory = new CommandFactory

		commandFactory commandFor INVALID_COMMAND should be (None)
	}

	it should "return a PostCommand" in {
		val commandFactory = new CommandFactory

		val command = commandFactory commandFor USER_POST_COMMAND

		command.get shouldBe a [PostCommand]
	}

	val INVALID_COMMAND = "invalid_command"
	val USER_POST_COMMAND = "Alice -> Hello"

}
