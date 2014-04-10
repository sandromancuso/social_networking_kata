package acceptance.scenario

import acceptance.infrastructure.AcceptanceSpec

class StartAndStopScenario extends AcceptanceSpec {

	scenario("Start and Stop the application") {

		val twitter = app_context

		Given("the application will receive an 'exit' command")
		twitter willReceive "exit"

		Then("The application should terminate")
		twitter executeWith(twitter userCommands) should be("> bye!\n")

	}

}
