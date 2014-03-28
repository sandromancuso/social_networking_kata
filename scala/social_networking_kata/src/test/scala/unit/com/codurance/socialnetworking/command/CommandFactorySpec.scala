package unit.com.codurance.socialnetworking.command

import unit.com.codurance.socialnetworking.UnitSpec
import com.codurance.socialnetworking.command.{FollowCommand, ReadCommand, PostCommand, CommandFactory}
import com.codurance.socialnetworking.domain.Users

class CommandFactorySpec extends UnitSpec {

	"CommandFactory" should "return a ReadCommand by default" in new context {
		commandFactory commandFor RANDOM_STRING shouldBe a [ReadCommand]
	}

	it should "return a ReadCommand when it receives a user name" in new context {
		val command = commandFactory commandFor USER_READ_COMMAND

		command shouldBe a [ReadCommand]
	}

	it should "return a PostCommand" in new context {
		val command = commandFactory commandFor USER_POST_COMMAND

		command shouldBe a [PostCommand]
	}

	it should "return a FollowCommand" in new context {
		val command = commandFactory commandFor USER_FOLLOW_COMMAND

		command shouldBe a [FollowCommand]
	}

	trait context {
		val users = mock[Users]
		val commandFactory = new CommandFactory(users)
	}

	val RANDOM_STRING = "random string"
	val USER_POST_COMMAND = "Alice -> Hello"
	val USER_READ_COMMAND = "Alice"
	val USER_FOLLOW_COMMAND = "Bob follows Alice"

}
