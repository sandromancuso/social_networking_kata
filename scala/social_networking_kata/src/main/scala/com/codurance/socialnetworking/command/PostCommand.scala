package com.codurance.socialnetworking.command

import com.codurance.socialnetworking.domain.{Post, Users}

class PostCommand(userCommand: String, users: Users) extends Command(userCommand: String) {

	val POST_COMMAND_ID = " -> "

	override def execute(): Option[List[Post]] = {
		val command_parts = userCommand.split(POST_COMMAND_ID)
		val user = command_parts(0)
		val post = command_parts(1)
		users newPost(user, post)
		None
	}

}
