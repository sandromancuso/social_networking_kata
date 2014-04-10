package unit.com.codurance.socialnetworking.command

import unit.com.codurance.socialnetworking.UnitSpec
import com.codurance.socialnetworking.command.ExitCommand
import com.codurance.socialnetworking.infrastructure.System

import org.mockito.Mockito.verify

class ExitCommandSpec extends UnitSpec {

	"ExitCommand" should "terminate the application" in {
		val system = mock[System]
		val exitCommand = new ExitCommand("bal", system)

		exitCommand execute()

		verify(system).exit()
	}

}
