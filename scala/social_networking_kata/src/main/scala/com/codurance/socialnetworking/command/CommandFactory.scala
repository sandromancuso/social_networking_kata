package com.codurance.socialnetworking.command

import com.codurance.socialnetworking.domain.Users

class CommandFactory(users: Users) {

	val POST_COMMAND_PATTERN = ".*\\s->\\s.*".r
	val READ_COMMAND_PATTERN = ".*".r

	def commandFor(userCommand: String): Command = {
		userCommand match {
			case POST_COMMAND_PATTERN() => new PostCommand(userCommand, users)
			case READ_COMMAND_PATTERN() => new ReadCommand(userCommand)
		}
	}

}
