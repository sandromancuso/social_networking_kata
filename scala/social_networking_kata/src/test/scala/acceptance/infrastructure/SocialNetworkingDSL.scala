package acceptance.infrastructure

import com.codurance.socialnetworking.user_interface.{UserCommands, Console}
import com.codurance.socialnetworking.command.CommandFactory
import com.codurance.socialnetworking.domain.Users
import com.codurance.socialnetworking.SocialNetworking
import scala.collection.mutable
import org.mockito.Mockito
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar

trait SocialNetworkingDSL extends MockitoSugar {

	val application = fixture
	def fixture = new {

		val QUIT = "quit"
		val console = mock[Console]
		val userCommands = new UserCommands(new CommandFactory(new Users))
		val socialNetworking = new SocialNetworking(console, userCommands)
		var consoleCommands: mutable.MutableList[String] = mutable.MutableList()
		var expectedMessages: mutable.MutableList[String] = mutable.MutableList()

		def receives(userCommands: String*) = {
			consoleCommands ++= userCommands
		}

		def start() = {
			consoleCommands += "quit"
			Mockito.when(console.readline()).thenReturn(consoleCommands.head, consoleCommands.tail:_*)
			socialNetworking start
		}

		def displays(messages: String*) = {
			expectedMessages ++= messages
			expectedMessages foreach(m => verify(console).write(m))
		}
	}

}
