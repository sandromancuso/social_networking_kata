package unit.com.codurance.socialnetworking.domain

import unit.com.codurance.socialnetworking.UnitSpec
import com.codurance.socialnetworking.domain.{User, Post}

class PostSpec extends UnitSpec {

	"Post" should "inform if it belongs to a specific user" in {
		val ALICE_POST = Post("Alice", "message")

		ALICE_POST isFrom(User("Alice")) shouldBe true
		ALICE_POST isFrom(User("Bob")) shouldBe false
	}

	it should "inform if it belongs to one of the users" in {
		val ALICE_POST = Post("Alice", "message")

		ALICE_POST isFromOneOf(User("Bob"), User("Alice"), User("Jim")) shouldBe true
		ALICE_POST isFromOneOf(User("Bob"), User("Jim")) shouldBe false
	}

}
