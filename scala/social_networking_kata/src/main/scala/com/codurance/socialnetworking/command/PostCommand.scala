package com.codurance.socialnetworking.command

import com.codurance.socialnetworking.domain.Users

class PostCommand(userCommand: String, users: Users) extends Command(userCommand: String) {

	val POST_COMMAND_ID = " -> "

	override def execute(): Option[List[String]] = {
		val command_parts = userCommand.split(POST_COMMAND_ID)
		val user = command_parts(0)
		val post = command_parts(1)
		users add(user, post)
		None
	}

}
