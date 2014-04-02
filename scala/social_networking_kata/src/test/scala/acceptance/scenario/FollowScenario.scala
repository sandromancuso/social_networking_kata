package acceptance.scenario

import acceptance.infrastructure.AcceptanceSpec

class FollowScenario extends AcceptanceSpec {

	info("In order to see what all my friends are up to")
	info("As a user")
	info("I would like to follow them")
	info("And see all their messages in my wall")

	feature("Following friends") {

		scenario("User follows friends") {

			Given("Charlie follows Alice")
				application receives(NOW, "Alice -> Hello, my name is Alice")
				application receives(NOW + ONE_SECOND, "Charlie -> I'm in London today. Anyone up for a drink?")
				application receives(NOW + TWO_SECONDS, "Alice -> It's a lovely day today")

				application receives "Charlie follows Alice"

			When("Charlie checks his wall")
 	            application receives (NOW, "Charlie wall")

			Then("Charlie sees Alice's messages and his messages in reverse-chronological order")
				application start()
 	            application displays(
		                "Alice - It's a lovely day today",
		                "Charlie - I'm in London today. Anyone up for a drink?",
		                "Alice - Hello, my name is Alice")
		}

	}

	}
