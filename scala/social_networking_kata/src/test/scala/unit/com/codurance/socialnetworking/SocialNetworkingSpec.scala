package unit.com.codurance.socialnetworking

import com.codurance.socialnetworking.user_interface.{UserCommands, Console}
import org.mockito.Mockito._
import org.mockito.Matchers._
import org.mockito.BDDMockito._
import com.codurance.socialnetworking.SocialNetworking
import com.codurance.socialnetworking.domain.{User, Post}

class SocialNetworkingSpec extends UnitSpec {

	val ALICE = new User("Alice")
	val USER_FIRST_POST = "Alice -> Hello"
	val USER_SECOND_POST = "Alice -> Hello again"
	val READ_COMMAND = "Alice"
	val QUIT: String = "quit"

	trait context {
		val console = mock[Console]
		val userCommands = mock[UserCommands]
		given(userCommands execute(anyString())) willReturn None
		val socialNetworking = new SocialNetworking(console, userCommands)
	}

	"SocialNetworking" should "read from the console" in new context {
		given(console readline) willReturn (USER_FIRST_POST, QUIT)

		socialNetworking.start

		verify(console, times(2)).readline()
	}

	it should ("execute the user's command") in new context {
		given(console readline) willReturn (USER_FIRST_POST, QUIT)

		socialNetworking.start

		verify(userCommands) execute USER_FIRST_POST
	}

	it should ("display the outcome of the user's command") in new context {
		given(console readline) willReturn (READ_COMMAND, QUIT)
		given(userCommands execute(READ_COMMAND)) willReturn Some(List(Post(USER_FIRST_POST)))

		socialNetworking.start

		verify(console).write(USER_FIRST_POST)
	}

	it should("accept multiple user commands") in new context {
		given(console readline) willReturn (USER_FIRST_POST, USER_SECOND_POST, READ_COMMAND, QUIT)
		given(userCommands execute(USER_FIRST_POST)) willReturn None
		given(userCommands execute(USER_SECOND_POST)) willReturn None
		given(userCommands execute(READ_COMMAND)) willReturn Some(List(Post(USER_SECOND_POST),
																	   Post(USER_FIRST_POST)))

		socialNetworking.start

		verify(console).write(USER_SECOND_POST)
		verify(console).write(USER_FIRST_POST)
	}

}
