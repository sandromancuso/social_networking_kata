package com.codurance.socialnetworking

import com.codurance.socialnetworking.user_interface.{UserCommands, Console}

class SocialNetworking(console: Console,
                       userCommands: UserCommands) {

	val QUIT = "quit"

	def start() = {
		var userCommand: String = ""
		while ( {userCommand = console.readline(); userCommand != QUIT} ) {
			val posts = userCommands.execute(userCommand).getOrElse(List())
			console display posts
//			posts.foreach(p => console write p.user_name + " -> " + p.message)
		}
	}

}
