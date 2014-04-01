package unit.com.codurance.socialnetworking.domain

import unit.com.codurance.socialnetworking.UnitSpec
import com.codurance.socialnetworking.domain.{Post, Users}
import com.codurance.socialnetworking.infrastructure.Clock

class UsersSpec extends UnitSpec {

	"UsersSpec" should "return no posts when the user does not exist" in new context {
		users postsBy(NON_EXISTENT_USER) should be(None)
	}

	it should "create a new user when user name does not exist" in new context {
		users newPost(NEW_USER, POST)

		users.postsBy(NEW_USER).get should be(List(Post(POST)))
	}

	it should "return posts from a single user in reverse-chronological order" in new context {
		users newPost(USER, FIRST_POST)
		users newPost(USER, SECOND_POST)

		val posts = users postsBy(USER)

		posts.get should be(List(Post(SECOND_POST), Post(FIRST_POST)))
	}

	it should "not return any posts in the wall of a user that does not exist" in new context {
		users wall(NON_EXISTENT_USER) should be(None)
	}

	ignore should "return posts from a user and all the users she follows in reverse-chronological order" in new context {
		users newPost(ALICE, ALICE_FIRST_POST)
		users newPost(BOB, BOB_FIRST_POST)
		users newPost(ALICE, ALICE_SECOND_POST)

		users follow(BOB, ALICE)

		val posts = users wall(BOB)

		posts.get should be(List(Post(ALICE_SECOND_POST), Post(BOB_FIRST_POST), Post(ALICE_FIRST_POST)))
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

	val ALICE = "Alice"
	val BOB = "Bob"
	val ALICE_FIRST_POST = "Alice first post"
	val ALICE_SECOND_POST = "Alice second post"
	val BOB_FIRST_POST = "Bob first post"
}
