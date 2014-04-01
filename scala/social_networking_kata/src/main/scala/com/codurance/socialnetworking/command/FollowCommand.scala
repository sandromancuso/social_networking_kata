package com.codurance.socialnetworking.command

import com.codurance.socialnetworking.domain.{Post, Users}

class FollowCommand(userCommand: String, users: Users) extends Command(userCommand: String) {

	val FOLLOWS_COMMAND = " follows "

	override def execute(): Option[List[Post]] = {
		val command_parts = userCommand split FOLLOWS_COMMAND
		val user = command_parts(0)
		val user_being_followed = command_parts(1)
		users follow(user, user_being_followed)
		None
	}

}
