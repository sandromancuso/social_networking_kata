package unit.com.codurance.socialnetworking.command

import org.mockito.Mockito._
import org.mockito.BDDMockito._
import com.codurance.socialnetworking.command.{ReadCommand, WallCommand}
import unit.com.codurance.socialnetworking.UnitSpec
import com.codurance.socialnetworking.domain.Users

class WallCommandSpec extends UnitSpec {

	"WallCommand" should "not return any messages when user does not exist" in new context {
		given(users wall INVALID_USER) willReturn None

		new WallCommand(INVALID_USER, users) execute

		verify(users) wall(INVALID_USER)
	}

	it should "return a list of posts when user exists" in new context {
		given(users wall USER) willReturn Some(List(A_POST))

		new WallCommand(USER, users) execute() should be(Some(List(A_POST)))
	}

	trait context {
		val users = mock[Users]
	}

	val INVALID_USER = "invalid user"
 	val USER = "Charlie"
	val A_POST = "Hello"
}
