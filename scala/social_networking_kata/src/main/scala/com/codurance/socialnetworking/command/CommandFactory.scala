package com.codurance.socialnetworking.command

import com.codurance.socialnetworking.domain.Users
import com.codurance.socialnetworking.infrastructure.System

class CommandFactory(users: Users) {

	val POST_COMMAND_PATTERN   = ".*\\s->\\s.*".r
	val READ_COMMAND_PATTERN   = ".*".r
	val FOLLOW_COMMAND_PATTERN = ".*\\sfollows\\s.*".r
	val WALL_COMMAND_PATTERN   = ".*\\swall".r
	val EXIT_COMMAND_PATTERN   = "exit".r

	def commandFor(userCommand: String): Command = {
		userCommand match {
			case EXIT_COMMAND_PATTERN()   => new ExitCommand(new System)
			case POST_COMMAND_PATTERN()   => new PostCommand(userCommand, users)
			case FOLLOW_COMMAND_PATTERN() => new FollowCommand(userCommand, users)
			case WALL_COMMAND_PATTERN()   => new WallCommand(userCommand, users)
			case READ_COMMAND_PATTERN()   => new ReadCommand(userCommand, users)
		}
	}

}
