package acceptance.infrastructure

import com.codurance.socialnetworking.user_interface.{PostFormatter, View, UserCommands, Console}
import com.codurance.socialnetworking.command.CommandFactory
import com.codurance.socialnetworking.domain.Users
import com.codurance.socialnetworking.SocialNetworking
import scala.collection.mutable
import org.mockito.Mockito._
import org.mockito.BDDMockito.given
import org.scalatest.mock.MockitoSugar
import com.codurance.socialnetworking.infrastructure.Clock
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
		var consoleCommands: mutable.MutableList[String] = mutable.MutableList()
		var clockTimes: mutable.MutableList[LocalDateTime] = mutable.MutableList()
		var expectedMessages: mutable.MutableList[String] = mutable.MutableList()

		val socialNetworking = create_social_networking_app()

		def receives(userCommands: String*) = {
			consoleCommands ++= userCommands
		}

		def receives(time: LocalDateTime = NOW, userCommand: String) = {
			consoleCommands += userCommand
			clockTimes += time
		}

		def start() = {
			consoleCommands += "quit"
			given(console readline) willReturn(consoleCommands.head, consoleCommands.tail:_*)
			given(formatter_clock current_time) willReturn(NOW)

			if (clockTimes.isEmpty) clockTimes += LocalDateTime.now()
			given(users_clock current_time) willReturn(clockTimes.head, clockTimes.tail:_*)

			socialNetworking start
		}

		def displays(messages: String*) = {
			expectedMessages ++= messages
			expectedMessages foreach(m => verify(console).write(m))
		}

		def create_social_networking_app() = {
			val userCommands = new UserCommands(new CommandFactory(new Users(users_clock)))
			val view = new View(console, new PostFormatter(formatter_clock))
			new SocialNetworking(view, userCommands)
		}

	}

}
