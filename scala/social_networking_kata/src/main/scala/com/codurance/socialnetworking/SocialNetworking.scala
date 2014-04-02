package com.codurance.socialnetworking

import com.codurance.socialnetworking.user_interface.{View, UserCommands, Console}

class SocialNetworking(view: View,
                       console: Console,
                       userCommands: UserCommands) {

	val QUIT = "quit"

	def start() = {
		var userCommand: String = ""
		while ( {userCommand = console.readline(); userCommand != QUIT} ) {
			val posts = userCommands.execute(userCommand).getOrElse(List())
			view display posts
//			posts.foreach(p => console write p.user_name + " -> " + p.message)
		}
	}

}
