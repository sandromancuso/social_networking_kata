package unit.com.codurance.socialnetworking.domain

import org.mockito.BDDMockito._

import unit.com.codurance.socialnetworking.UnitSpec
import com.codurance.socialnetworking.domain.Users
import java.util.Calendar
import com.codurance.socialnetworking.infrastructure.Clock
import java.time.{Instant, LocalDateTime}
import acceptance.infrastructure.CustomMatchers._
import java.time.temporal.{ChronoField, TemporalField}

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
		users_receive(BOB, BOB_FIRST_POST, NOW.plusSeconds(1))
		users_receive(ALICE, ALICE_SECOND_POST, NOW.plusSeconds(2))
		users_receive(CHARLIE, CHARLIE_FIRST_POST, NOW.plusSeconds(5))

		users follow(BOB, ALICE)
		users follow(BOB, CHARLIE)

		val posts = users wall(BOB)

		posts.get.size should be (4)
		posts.get(0).message should be (CHARLIE_FIRST_POST)
		posts.get(1).message should be (ALICE_SECOND_POST)
		posts.get(2).message should be (BOB_FIRST_POST)
		posts.get(3).message should be (ALICE_FIRST_POST)
	}

	it should "return posts in reverse-chronological order" in {
		val users = new Users(new Clock)
		users newPost("Bob",     "Hi, I'm Bob")
		users newPost("Alice",   "Hello, my name is Alice")
		users newPost("Charlie", "I'm in London today. Anyone up for a drink?")
		users newPost("Alice",   "It's a lovely day today")

		users follow("Charlie", "Alice")
		users follow("Charlie", "Bob")

		val posts = users wall "Charlie"

		posts.get(0).message shouldBe "It's a lovely day today"
		posts.get(1).message shouldBe "I'm in London today. Anyone up for a drink?"
		posts.get(2).message shouldBe "Hello, my name is Alice"
		posts.get(3).message shouldBe "Hi, I'm Bob"
	}

	trait context {
		val NOW = LocalDateTime now
		val clock = mock[Clock]
		val users = new Users(clock)

		def users_receive(user_name: String, post: String, time: LocalDateTime) {
			given(clock.current_time()) willReturn(time)
			users newPost(user_name, post)
		}

	}

	val NON_EXISTENT_USER = "non-existent user"
	val NEW_USER = "Alice"
	val USER = "Bob"
	val POST = "Hello"
	val FIRST_POST = "First post"
	val SECOND_POST = "Second post"

	val ALICE = "Alice"
	val ALICE_FIRST_POST = "Alice first post"
	val ALICE_SECOND_POST = "Alice second post"

	val BOB = "Bob"
	val BOB_FIRST_POST = "Bob first post"

	val CHARLIE = "Charlie"
	val CHARLIE_FIRST_POST = "Charlie first post"
}
