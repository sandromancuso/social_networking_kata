package com.codurance.socialnetworking

import com.codurance.socialnetworking.user_interface.{UserCommands, Console}

class SocialNetworking(console: Console, val userCommands: UserCommands) {

	val QUIT = "quit"

	def start() = {
		var userCommand: String = ""
		while ( {userCommand = console.readline(); userCommand != QUIT} ) {
			val messages = userCommands.execute(userCommand)
			messages.getOrElse(List()).foreach(console write)
		}
	}

}
