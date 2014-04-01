package unit.com.codurance.socialnetworking.command

import org.mockito.Mockito._

import unit.com.codurance.socialnetworking.UnitSpec
import com.codurance.socialnetworking.command.PostCommand
import com.codurance.socialnetworking.domain.Users

class PostCommandSpec extends UnitSpec {

	"PostCommand" should "not return any messages" in new context {
		new PostCommand(USER_POST, users) execute() should be(None)
	}

	it should "store user's post" in new context {
		new PostCommand(USER + POST + MESSAGE, users) execute

		verify(users) newPost(USER, MESSAGE)
	}

	trait context {
		val users = mock[Users]
	}

	val USER_POST = "Alice -> Hello"
	val USER = "Alice"
	val POST = " -> "
	val MESSAGE = "Hello"

}
