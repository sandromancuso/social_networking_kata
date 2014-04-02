package com.codurance.socialnetworking

import com.codurance.socialnetworking.user_interface.{View, UserCommands}

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
