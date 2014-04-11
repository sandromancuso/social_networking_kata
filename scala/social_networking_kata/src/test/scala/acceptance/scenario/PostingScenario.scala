package acceptance.scenario

import acceptance.infrastructure.AcceptanceSpec
import acceptance.infrastructure.CustomMatchers._

class PostingScenario extends AcceptanceSpec {

	info("In order to share my ideas with the world")
	info("As a user")
	info("I would like to post my messages")
	info("And make them available so other users could read them")

	feature("Posting and reading messages") {

		scenario("User posts messages") {

			val twitter = app_context

			Given("Alice posts messages")
				twitter willReceive "Alice -> Hello, my name is Alice"
				twitter willReceive "Alice -> It's a lovely day today"

			When("Bob reads Alice's messages")
				twitter willReceive "Alice"

			Then("Alice's messages are displayed in reverse-chronological order")
				twitter outputFor(twitter userCommands) should matchOutput(
						"> " +
						"> " +
						"> " +
					    "Alice - It's a lovely day today \n" +
						"Alice - Hello, my name is Alice \n" +
						"> bye!\n")

		}
	}

	val bobReadsMessageFromAlice = "Alice"

}
