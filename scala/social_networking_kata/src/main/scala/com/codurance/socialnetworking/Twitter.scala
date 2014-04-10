package com.codurance.socialnetworking

import com.codurance.socialnetworking.user_interface.{Console, PostFormatter, View, UserCommands}
import com.codurance.socialnetworking.command.CommandFactory
import com.codurance.socialnetworking.domain.Users
import com.codurance.socialnetworking.infrastructure.Clock

class Twitter(view: View, userCommands: UserCommands) {

	val QUIT = "quit"

	def start() = {
		var userCommand: String = ""
		while ( {userCommand = view.userCommand(); userCommand != QUIT} ) {
			val posts = userCommands.execute(userCommand).getOrElse(List())
			view display posts
		}
	}

}

object Twitter extends App {

	val clock = new Clock
	val view = new View(new Console, new PostFormatter(clock))
	val commandFactory = new CommandFactory(new Users(clock))
	val userCommands = new UserCommands(commandFactory)
	val twitter = new Twitter(view, userCommands)

	twitter start
}
