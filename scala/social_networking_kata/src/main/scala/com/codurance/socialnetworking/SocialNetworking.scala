package com.codurance.socialnetworking

import com.codurance.socialnetworking.user_interface.{UserCommands, Console}

class SocialNetworking(console: Console, val userCommands: UserCommands) {

	val QUIT = "quit"

	def start() = {
		var userCommand: String = ""
		while ( {userCommand = console.readline(); userCommand != QUIT} ) {
			val posts = userCommands.execute(userCommand).getOrElse(List())
			posts.foreach(p => console write p.message)
		}
	}

}
