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
				application receives(FIRST_POST_COMMAND_FROM_ALICE,
									 FIRST_POST_COMMAND_FROM_CHARLIE,
									 SECOND_POST_COMMAND_FROM_ALICE)
				application receives CHARLIE_FOLLOWS_ALICE_COMMAND

			When("Charlie checks his wall")
 	            application receives CHARLIE_WALL_COMMAND

			Then("Charlie sees Alice's messages and his messages in reverse-chronological order")
				application start()
 	            application displays(SECOND_POST_COMMAND_FROM_ALICE,
		                             FIRST_POST_COMMAND_FROM_CHARLIE,
				                     FIRST_POST_COMMAND_FROM_ALICE)
		}

	}

	val FIRST_MESSAGE_FROM_ALICE = "Hello, my name is Alice"
	val SECOND_MESSAGE_FROM_ALICE = "It's a lovely day today"
	val FIRST_POST_COMMAND_FROM_ALICE = "Alice -> " + FIRST_MESSAGE_FROM_ALICE
	val SECOND_POST_COMMAND_FROM_ALICE = "Alice -> " + SECOND_MESSAGE_FROM_ALICE
	val FIRST_MESSAGE_FROM_CHARLIE = "I'm in London today. Anyone up for a drink?"
	val FIRST_POST_COMMAND_FROM_CHARLIE = "Charlie -> " + FIRST_MESSAGE_FROM_CHARLIE

	val CHARLIE_FOLLOWS_ALICE_COMMAND = "Charlie follows Alice"
	val CHARLIE_WALL_COMMAND = "Charlie wall"

}
