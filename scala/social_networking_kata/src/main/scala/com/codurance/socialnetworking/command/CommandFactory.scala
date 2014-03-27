package com.codurance.socialnetworking.command

class CommandFactory {

	val POST_COMMAND_PATTERN = ".*\\s->\\s.*".r

	def commandFor(userCommand: String): Option[Command] = {
		userCommand match {
			case POST_COMMAND_PATTERN() => Some(new PostCommand(userCommand))
			case _ => None
		}
	}

}
