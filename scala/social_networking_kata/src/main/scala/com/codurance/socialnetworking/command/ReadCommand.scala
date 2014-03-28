package com.codurance.socialnetworking.command

import com.codurance.socialnetworking.domain.Users

class ReadCommand(user: String, users: Users) extends Command(user) {

	override def execute(): Option[List[String]] = {
		users postsBy user
	}

}
