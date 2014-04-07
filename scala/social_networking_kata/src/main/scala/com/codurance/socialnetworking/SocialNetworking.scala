package com.codurance.socialnetworking

import com.codurance.socialnetworking.user_interface.{Console, PostFormatter, View, UserCommands}
import com.codurance.socialnetworking.command.CommandFactory
import com.codurance.socialnetworking.domain.Users
import com.codurance.socialnetworking.infrastructure.Clock

class SocialNetworking(view: View,
                       userCommands: UserCommands) {

	val QUIT = "quit"

	def start() = {
		var userCommand: String = ""
		while ( {userCommand = view.userCommand(); userCommand != QUIT} ) {
			val posts = userCommands.execute(userCommand).getOrElse(List())
			view display posts
		}
	}

}

object SocialNetworking extends App {

	val view = new View(new Console, new PostFormatter(new Clock))
	val commandFactory = new CommandFactory(new Users(new Clock))
	val userCommands = new UserCommands(commandFactory)
	val socialNetworking = new SocialNetworking(view, userCommands)

	socialNetworking start
}
