package acceptance.scenario

import acceptance.AcceptanceSpec
import org.mockito.Mockito
import org.mockito.Mockito._
import com.codurance.socialnetworking.SocialNetworking
import com.codurance.socialnetworking.user_interface.Console

class PostingScenario extends AcceptanceSpec {

	info("In order to share my ideas with the world")
	info("As a user")
	info("I would like to post my messages")
	info("And make them available so other users could read them")

	feature("Posting and reading messages") {

		scenario("User posts messages") {
			val application = fixture

			Given("Alice posts messages")
				val firstMessageFromAlice = "Alice -> Hello, my name is Alice"
				val secondMessageFromAlice = "Alice -> It's a lovely day today"
				application receives(firstMessageFromAlice, secondMessageFromAlice)

			When("Bob reads Alice's messages")
				val bobReadsMessageFromAlice = "Alice"
				application receives bobReadsMessageFromAlice
				application start

			Then("Alice's messages are displayed in reverse-chronological order")
				application displays(secondMessageFromAlice, firstMessageFromAlice)
		}
	}

	def fixture = new {

		val console = mock[Console]
		val socialNetworking = new SocialNetworking(console)
		var consoleCommands: List[String] = List()
		var expectedMessages: List[String] = List()

		def receives(userCommands: String*) = {
			consoleCommands = consoleCommands ++ userCommands
			Mockito.when(console.readline()).thenReturn(userCommands.head, userCommands.tail:_*)
		}

		def start() = {
			socialNetworking start
		}

		def displays(messages: String*) = {
			expectedMessages = expectedMessages ++ messages
			expectedMessages foreach(m => verify(console).write(m))
		}
	}



}
