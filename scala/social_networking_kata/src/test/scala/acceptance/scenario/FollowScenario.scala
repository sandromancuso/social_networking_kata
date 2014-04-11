package acceptance.scenario

import acceptance.infrastructure.AcceptanceSpec
import acceptance.infrastructure.CustomMatchers._

class FollowScenario extends AcceptanceSpec {

	info("In order to see what all my friends are up to")
	info("As a user")
	info("I would like to follow them")
	info("And see all their messages in my wall")

	feature("Following friends") {

		scenario("User follows friends") {

			val twitter = app_context

			Given("Charlie follows Alice")
				twitter willReceive "Bob -> Hi, I'm Bob"
				twitter willReceive "Alice -> Hello, my name is Alice"
				twitter willReceive "Charlie -> I'm in London today. Anyone up for a drink?"
				twitter willReceive "Alice -> It's a lovely day today"

				twitter willReceive "Charlie follows Alice"
				twitter willReceive "Charlie follows Bob"

			When("Charlie checks his wall")
 	            twitter willReceive "Charlie wall"

			Then("Charlie sees Alice's messages and his messages in reverse-chronological order")
				twitter outputFor(twitter userCommands) should matchOutput(
						"> " +
						"> " +
						"> " +
						"> " +
						"> " +
						"> " +
						"> " +
		                "Alice - It's a lovely day today \n" +
		                "Charlie - I'm in London today. Anyone up for a drink? \n" +
		                "Alice - Hello, my name is Alice \n" +
		                "Bob - Hi, I'm Bob \n" +
						"> bye!\n")
		}

	}

	}
