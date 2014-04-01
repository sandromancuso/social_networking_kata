package unit.com.codurance.socialnetworking.domain

import unit.com.codurance.socialnetworking.UnitSpec
import com.codurance.socialnetworking.domain.User

class UserSpec extends UnitSpec {

	"User" should "return all users followed" in {
		val Alice = User("Alice")
		val Bob = User("Bob")
		val Charlie = User("Charlie")

		Alice follows(Bob)
		Alice follows(Charlie)

		Alice followedUsers() should be(List(Bob, Charlie))
	}

}
