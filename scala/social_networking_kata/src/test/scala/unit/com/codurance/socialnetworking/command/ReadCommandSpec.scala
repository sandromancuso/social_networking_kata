package unit.com.codurance.socialnetworking.command

import org.mockito.Mockito._
import org.mockito.BDDMockito._

import unit.com.codurance.socialnetworking.UnitSpec
import com.codurance.socialnetworking.command.ReadCommand
import com.codurance.socialnetworking.domain.{Post, User, Users}
import com.codurance.socialnetworking.infrastructure.Clock

class ReadCommandSpec extends UnitSpec {

	"ReadCommand" should "not return any messages when user does not exist" in new context {
		given(users.postsBy(INVALID_USER)) willReturn None

		new ReadCommand(INVALID_USER, users) execute() should be(None)

		verify(users) postsBy(INVALID_USER)
	}

	it should "return a list of user posts when user exists" in new context {
		given(users.postsBy(USER)) willReturn Some(List(ALICE_POST))

		new ReadCommand(USER, users) execute() should be(Some(List(ALICE_POST)))
	}

	trait context {
		val users = mock[Users]
	}

	val INVALID_USER = "invalid user"
	val USER = "Alice"
	val ALICE = new User("Alice")
	val A_POST = "Hello"
	val ALICE_POST = Post(ALICE user_name, A_POST, Clock now)
}
