package acceptance.scenario

import acceptance.infrastructure.AcceptanceSpec

class PostingScenario extends AcceptanceSpec {

	info("In order to share my ideas with the world")
	info("As a user")
	info("I would like to post my messages")
	info("And make them available so other users could read them")

	feature("Posting and reading messages") {

		scenario("User posts messages") {

			Given("Alice posts messages")
				application receives(FIRST_POST_COMMAND_FROM_ALICE,
									 SECOND_POST_COMMAND_FROM_ALICE)

			When("Bob reads Alice's messages")
				application receives bobReadsMessageFromAlice

			Then("Alice's messages are displayed in reverse-chronological order")
				application start()
				application displays(SECOND_MESSAGE_FROM_ALICE, FIRST_MESSAGE_FROM_ALICE)
		}
	}

	val FIRST_MESSAGE_FROM_ALICE = "Hello, my name is Alice"
	val SECOND_MESSAGE_FROM_ALICE = "It's a lovely day today"
	val FIRST_POST_COMMAND_FROM_ALICE = "Alice -> " + FIRST_MESSAGE_FROM_ALICE
	val SECOND_POST_COMMAND_FROM_ALICE = "Alice -> " + SECOND_MESSAGE_FROM_ALICE
	val bobReadsMessageFromAlice = "Alice"

}
