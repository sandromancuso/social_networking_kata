package unit.com.codurance.socialnetworking

import com.codurance.socialnetworking.user_interface.{View, UserCommands}
import org.mockito.Mockito._
import org.mockito.Matchers._
import org.mockito.BDDMockito._
import com.codurance.socialnetworking.SocialNetworking
import com.codurance.socialnetworking.domain.{User, Post}
import com.codurance.socialnetworking.infrastructure.Clock

class SocialNetworkingSpec extends UnitSpec {

	"SocialNetworking" should "read user commands from the view" in new context {
		given(view userCommand) willReturn (ALICE_FIRST_POST_COMMAND, QUIT)

		socialNetworking.start

		verify(view, times(2)) userCommand()
	}

	it should ("execute the user's command") in new context {
		given(view userCommand) willReturn (ALICE_FIRST_POST_COMMAND, QUIT)

		socialNetworking.start

		verify(userCommands) execute ALICE_FIRST_POST_COMMAND
	}

	it should ("display the outcome of the user's command") in new context {
		given(view userCommand) willReturn (READ_COMMAND, QUIT)
		val posts = List(Post(ALICE user_name, ALICE_FIRST_MESSAGE, Clock now))
		given(userCommands execute(READ_COMMAND)) willReturn Some(posts)

		socialNetworking.start

		verify(view) display(posts)
	}

	it should("accept multiple user commands") in new context {
		given(view userCommand) willReturn (ALICE_FIRST_POST_COMMAND, ALICE_SECOND_POST_COMMAND, READ_COMMAND, QUIT)
		given(userCommands execute(ALICE_FIRST_POST_COMMAND)) willReturn None
		given(userCommands execute(ALICE_SECOND_POST_COMMAND)) willReturn None

		val posts = List(Post(ALICE user_name, ALICE_SECOND_MESSAGE, Clock now),
						 Post(ALICE user_name, ALICE_FIRST_MESSAGE, Clock now))
		given(userCommands execute(READ_COMMAND)) willReturn Some(posts)

		socialNetworking.start

		verify(view) display(posts)
	}

	val ALICE = new User("Alice")
	val ALICE_FIRST_MESSAGE = "Hello"
	val ALICE_FIRST_POST_COMMAND = ALICE.user_name + " -> " + ALICE_FIRST_MESSAGE
	val ALICE_SECOND_MESSAGE = "Hello again"
	val ALICE_SECOND_POST_COMMAND = ALICE.user_name + " -> " + ALICE_SECOND_MESSAGE

	val READ_COMMAND = "Alice"
	val QUIT: String = "quit"

	trait context {
		val userCommands = mock[UserCommands]
		val view = mock[View]
		given(userCommands execute(anyString())) willReturn None
		val socialNetworking = new SocialNetworking(view, userCommands)
	}

}
