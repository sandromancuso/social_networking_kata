package unit.com.codurance.socialnetworking.user_interface

import org.mockito.BDDMockito._
import org.mockito.Mockito._

import unit.com.codurance.socialnetworking.UnitSpec
import com.codurance.socialnetworking.user_interface.{PostFormatter, View, Console}
import com.codurance.socialnetworking.domain.Post
import java.time.LocalDateTime

class ViewSpec extends UnitSpec {

	"View" should "display console prompt when created" in {
		val console = mock[Console]
		val postFormatter = mock[PostFormatter]

		new View(console, postFormatter)

		verify(console) print("> ")
	}

	it should "read commands from the console" in new context {
		given(console readline()) willReturn USER_COMMAND

		view userCommand() should be(USER_COMMAND)

		verify(console) print("> ")
		verify(console) readline()
	}

	it should "format posts before displaying them" in new context {
		val posts = List(Post("Alice", "Hello", NOW), Post("Bob", "Ola", NOW))
		val formatted_posts = List("Alice - Hello", "Bob - Ola")
		given(postFormatter format(posts)) willReturn(formatted_posts)

		view display posts

		verify(console) write(formatted_posts(0))
		verify(console) write(formatted_posts(1))
	}

	trait context {
		val console = mock[Console]
		val postFormatter = mock[PostFormatter]
		val view = new View(console, postFormatter)
	}

	val USER_COMMAND = "A user command"
	val NOW = LocalDateTime now

}
