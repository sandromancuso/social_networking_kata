package com.codurance.socialnetworking.command

import com.codurance.socialnetworking.domain.Users

class WallCommand(userCommand: String, users: Users) extends Command(userCommand: String) {

	val WALL_COMMAND_ID = " wall"

	override def execute(): Option[List[String]] = {
		val user_name = userCommand.split(WALL_COMMAND_ID)(0)
		users wall(user_name)
	}
}
