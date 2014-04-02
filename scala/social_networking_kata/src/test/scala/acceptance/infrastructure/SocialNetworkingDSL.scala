package acceptance.infrastructure

import com.codurance.socialnetworking.user_interface.{PostFormatter, View, UserCommands, Console}
import com.codurance.socialnetworking.command.CommandFactory
import com.codurance.socialnetworking.domain.Users
import com.codurance.socialnetworking.SocialNetworking
import scala.collection.mutable
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import com.codurance.socialnetworking.infrastructure.Clock
import java.util.{Date, Calendar}

trait SocialNetworkingDSL extends MockitoSugar {

	val application = fixture
	val NOW = Calendar getInstance() getTimeInMillis
	val ONE_SECOND = 1000
	val TWO_SECONDS = 2000

	def fixture = new {

		val QUIT = "quit"
		val console = mock[Console]
		val clock = mock[Clock]
		val userCommands = new UserCommands(new CommandFactory(new Users(clock)))
		val view = new View(console, new PostFormatter)
		val socialNetworking = new SocialNetworking(view, userCommands)
		var consoleCommands: mutable.MutableList[String] = mutable.MutableList()
		var clockTimes: mutable.MutableList[Date] = mutable.MutableList()
		var expectedMessages: mutable.MutableList[String] = mutable.MutableList()

		def receives(userCommands: String*) = {
			consoleCommands ++= userCommands
		}

		def receives(time_in_millis: Long = NOW, userCommand: String) = {
			consoleCommands += userCommand
			clockTimes += new Date(time_in_millis)
		}

		def start() = {
			consoleCommands += "quit"
			when(console.readline()).thenReturn(consoleCommands.head, consoleCommands.tail:_*)
			if (clockTimes.isEmpty) clockTimes += new Date()
			when(clock.current_time()) thenReturn(clockTimes.head, clockTimes.tail:_*)

			socialNetworking start
		}

		def displays(messages: String*) = {
			expectedMessages ++= messages
			expectedMessages foreach(m => verify(console).write(m))
		}
	}

}
