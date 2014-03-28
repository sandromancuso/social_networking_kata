package com.codurance.socialnetworking.command

import com.codurance.socialnetworking.domain.Users

class FollowCommand(userCommand: String, users: Users) extends Command(userCommand: String) {

	override def execute(): Option[List[String]] = {
		None
	}

}
