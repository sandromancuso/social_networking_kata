package unit.com.codurance.socialnetworking.domain

import org.mockito.BDDMockito._

import unit.com.codurance.socialnetworking.UnitSpec
import com.codurance.socialnetworking.domain.Users
import java.util.{Calendar, Date}
import com.codurance.socialnetworking.infrastructure.Clock

class UsersSpec extends UnitSpec {

	"Users" should "return no posts when the user does not exist" in new context {
		users postsBy(NON_EXISTENT_USER) should be(None)
	}

	it should "create a new user when user name does not exist" in new context {
		users newPost(NEW_USER, POST)

		users.postsBy(NEW_USER).get(0).message should be(POST)
	}

	it should "return posts from a single user in reverse-chronological order" in new context {
		users newPost(USER, FIRST_POST)
		users newPost(USER, SECOND_POST)

		val posts = users postsBy(USER)

		posts.get.size should be (2)
		posts.get(0).message should be (SECOND_POST)
		posts.get(1).message should be (FIRST_POST)
	}

	it should "not return any posts in the wall of a user that does not exist" in new context {
		users wall(NON_EXISTENT_USER) should be(None)
	}

	it should "return posts from a user and all the users she follows in reverse-chronological order" in new context {
		users_receive(ALICE, ALICE_FIRST_POST, NOW)
		users_receive(BOB, BOB_FIRST_POST, NOW + ONE_SECOND)
		users_receive(ALICE, ALICE_SECOND_POST, NOW + TWO_SECONDS)

		users follow(BOB, ALICE)

		val posts = users wall(BOB)

		posts.get.size should be (3)
		posts.get(0).message should be (ALICE_SECOND_POST)
		posts.get(1).message should be (BOB_FIRST_POST)
		posts.get(2).message should be (ALICE_FIRST_POST)
	}

	trait context {
		val NOW = new Date().getTime
		val ONE_SECOND = 1000
		val TWO_SECONDS = ONE_SECOND * 2
		val clock = mock[Clock]
		val users = new Users(clock)

		def users_receive(user_name: String, post: String, time_in_millis: Long) {
			timeIs(time_in_millis)
			users newPost(user_name, post)
		}

		def timeIs(time_in_millis: Long) {
			val calendar = Calendar getInstance()
			calendar setTimeInMillis(time_in_millis)
			given(clock.current_time()) willReturn(calendar getTime)
		}
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
