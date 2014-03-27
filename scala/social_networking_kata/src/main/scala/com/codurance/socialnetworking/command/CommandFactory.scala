package com.codurance.socialnetworking.command

import com.codurance.socialnetworking.domain.Users

class CommandFactory(users: Users) {

	val POST_COMMAND_PATTERN = ".*\\s->\\s.*".r

	def commandFor(userCommand: String): Option[Command] = {
		userCommand match {
			case POST_COMMAND_PATTERN() => Some(new PostCommand(userCommand, users))
			case _ => None
		}
	}

}
