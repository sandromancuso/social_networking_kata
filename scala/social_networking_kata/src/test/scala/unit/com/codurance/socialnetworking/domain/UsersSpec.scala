package unit.com.codurance.socialnetworking.domain

import unit.com.codurance.socialnetworking.UnitSpec
import com.codurance.socialnetworking.domain.Users

class UsersSpec extends UnitSpec {

	"UsersSpec" should "return no posts when the user does not exist" in new context {
		users postsBy(NON_EXISTENT_USER) should be(None)
	}

	it should "create a new user when user name does not exist" in new context {
		users add(NEW_USER, POST)

		users.postsBy(NEW_USER).get should be(List(POST))
	}

	it should "return posts in reverse-chronological order by default" in new context {
		users add(USER, FIRST_POST)
		users add(USER, SECOND_POST)

		val posts = users postsBy(USER)

		posts.get should be(List(SECOND_POST, FIRST_POST))
	}

	trait context {
		val users = new Users
	}

	val NON_EXISTENT_USER = "non-existent user"
	val NEW_USER = "Alice"
	val USER = "Bob"
	val POST = "Hello"
	val FIRST_POST = "First post"
	val SECOND_POST = "Second post"

}
