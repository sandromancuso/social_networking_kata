package unit.com.codurance.socialnetworking.command

import org.mockito.Mockito._

import unit.com.codurance.socialnetworking.UnitSpec
import com.codurance.socialnetworking.command.FollowCommand
import com.codurance.socialnetworking.domain.Users

class FollowCommandSpec extends UnitSpec {

	"FollowCommand" should "not return any messages" in new context {
		new FollowCommand(USER_FOLLOW_COMMAND, users) execute() should be(None)
	}

	it should "stores that a user is following another user" in new context {
		new FollowCommand(BOB + FOLLOWS + ALICE, users) execute

		verify(users) follow(BOB, ALICE)
	}

	trait context {
		val users = mock[Users]
	}

	val BOB = "Bob"
	val ALICE = "Alice"
	val FOLLOWS = " follows "
	val USER_FOLLOW_COMMAND = BOB + FOLLOWS + ALICE


}
