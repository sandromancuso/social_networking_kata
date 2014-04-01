package com.codurance.socialnetworking.user_interface

import com.codurance.socialnetworking.command.CommandFactory
import com.codurance.socialnetworking.domain.Post

class UserCommands(commandFactory: CommandFactory) {

	def execute(userCommand: String): Option[List[Post]] = {
		commandFactory commandFor(userCommand) execute
	}

}
