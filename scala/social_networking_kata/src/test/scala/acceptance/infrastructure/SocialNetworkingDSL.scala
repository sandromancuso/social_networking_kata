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
import java.time.LocalDateTime

trait SocialNetworkingDSL extends MockitoSugar {

	val application = fixture
	val NOW = LocalDateTime.now()
	val ONE_SECOND_AGO = NOW.minusSeconds(1)
	val TWO_SECONDS_AGO = NOW.minusSeconds(2)

	def fixture = new {

		val QUIT = "quit"
		val console = mock[Console]
		val users_clock = mock[Clock]
		val formatter_clock = mock[Clock]
		val userCommands = new UserCommands(new CommandFactory(new Users(users_clock)))
		val view = new View(console, new PostFormatter(formatter_clock))
		val socialNetworking = new SocialNetworking(view, userCommands)
		var consoleCommands: mutable.MutableList[String] = mutable.MutableList()
		var clockTimes: mutable.MutableList[LocalDateTime] = mutable.MutableList()
		var expectedMessages: mutable.MutableList[String] = mutable.MutableList()

		def receives(userCommands: String*) = {
			consoleCommands ++= userCommands
		}

		def receives(time: LocalDateTime = NOW, userCommand: String) = {
			consoleCommands += userCommand
			clockTimes += time
		}

		def start() = {
			consoleCommands += "quit"
			when(console.readline()).thenReturn(consoleCommands.head, consoleCommands.tail:_*)
			if (clockTimes.isEmpty) clockTimes += LocalDateTime.now()
			when(users_clock.current_time()) thenReturn(clockTimes.head, clockTimes.tail:_*)
			when(formatter_clock.current_time()) thenReturn(NOW)

			socialNetworking start
		}

		def displays(messages: String*) = {
			expectedMessages ++= messages
			expectedMessages foreach(m => verify(console).write(m))
		}
	}

}
