package acceptance.infrastructure

import org.scalatest._
import matchers._

trait CustomMatchers {

	// Removes the elapsed time information from the twitter output since
	// the Twitter application is being tested as a black box, that means,
	//
	class TwitterOutputMatcher(expectedTwitterOutput: String) extends Matcher[String] {

		def apply(left: String) = {
			val output = left.replaceAll("\\(.*\\)", "")
			MatchResult(
				output == expectedTwitterOutput,
				s"""$output does not match $expectedTwitterOutput""",
				s"""$output matches $expectedTwitterOutput"""
			)
		}
	}

	def matchOutput(expectedTwitterOutput: String) = new TwitterOutputMatcher(expectedTwitterOutput)

}

object CustomMatchers extends CustomMatchers
