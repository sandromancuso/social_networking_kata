package unit.com.codurance.socialnetworking.user_interface

import unit.com.codurance.socialnetworking.UnitSpec
import com.codurance.socialnetworking.domain.Post
import com.codurance.socialnetworking.user_interface.PostFormatter

class PostFormatterSpec extends UnitSpec {

	"PostFormatter" should "compose the user name and the message separated by a dash" in new context {
		val posts = List(Post("Alice", "Hello"))

		postFormatter format(posts) should be(List("Alice - Hello"))
	}

	trait context {
		val postFormatter = new PostFormatter
	}

}
