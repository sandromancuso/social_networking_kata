package unit.com.codurance.socialnetworking.user_interface

import unit.com.codurance.socialnetworking.UnitSpec
import com.codurance.socialnetworking.domain.Post
import com.codurance.socialnetworking.user_interface.PostFormatter
import com.codurance.socialnetworking.infrastructure.Clock

import org.mockito.BDDMockito.given
import java.time.LocalDateTime

class PostFormatterSpec extends UnitSpec {

	"PostFormatter" should "compose the user name and the message separated by a dash" in new context {
		val posts = List(Post("Alice", "Hello"))

		val formatted_post = postFormatter.format(posts).head

		formatted_post should startWith("Alice - Hello")
	}

	it should "display how long ago messages have been posted" in new context {
		val posts = List(
						Post("Alice", "Hello", now.minusDays(25)),
						Post("Alice", "Hello", now.minusDays(1)),
						Post("Alice", "Hello", now.minusHours(15)),
						Post("Alice", "Hello", now.minusHours(1)),
						Post("Alice", "Hello", now.minusMinutes(20)),
						Post("Alice", "Hello", now.minusMinutes(1)),
						Post("Alice", "Hello", now.minusSeconds(10)),
						Post("Alice", "Hello", now.minusSeconds(1)),
						Post("Alice", "Hello", now)
		)

		val formatted_posts = postFormatter format(posts)

		formatted_posts(0) should be("Alice - Hello (25 days ago)")
		formatted_posts(1) should be("Alice - Hello (1 day ago)")
		formatted_posts(2) should be("Alice - Hello (15 hours ago)")
		formatted_posts(3) should be("Alice - Hello (1 hour ago)")
		formatted_posts(4) should be("Alice - Hello (20 minutes ago)")
		formatted_posts(5) should be("Alice - Hello (1 minute ago)")
		formatted_posts(6) should be("Alice - Hello (10 seconds ago)")
		formatted_posts(7) should be("Alice - Hello (1 second ago)")
		formatted_posts(8) should be("Alice - Hello (just now)")
	}

	trait context {
		val now = LocalDateTime now
		val clock = mock[Clock]
		given(clock.current_time()) willReturn (now)
		val postFormatter = new PostFormatter(clock)
	}

}
