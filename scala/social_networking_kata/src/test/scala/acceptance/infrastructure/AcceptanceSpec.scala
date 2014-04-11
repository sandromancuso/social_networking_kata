package acceptance.infrastructure

import org.scalatest._
import org.scalatest.mock.MockitoSugar
import scala.sys.process._

abstract class AcceptanceSpec extends FeatureSpec
								 with MockitoSugar
								 with GivenWhenThen
								 with Matchers {

	def app_context = new {

		var userCommands: List[String] = List[String]()

		def willReceive(command: String): Unit = {
			userCommands ::= command
		}

		def outputFor(userCommands: List[String]): String = {
			(
				Seq("echo", ("exit" :: userCommands).reverse.mkString("\r\n") + "\r\n") #|
				Seq("/usr/local/bin/scala",
							"-cp",
							"./target/scala-2.10/classes",
							"com.codurance.socialnetworking.Twitter")
			).!!
		}

	}

}
