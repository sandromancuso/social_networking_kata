package com.codurance.socialnetworking.user_interface

import com.codurance.socialnetworking.command.{Command, CommandFactory}

class UserCommands(commandFactory: CommandFactory) {

	def execute(userCommand: String): Option[List[String]] = {
		commandFactory commandFor userCommand match {
			case Some(c:Command) => c execute userCommand
			case _ => None
		}
	}

}
