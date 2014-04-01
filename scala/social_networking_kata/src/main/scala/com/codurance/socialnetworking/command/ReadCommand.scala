package com.codurance.socialnetworking.command

import com.codurance.socialnetworking.domain.{Post, Users}

class ReadCommand(user: String, users: Users) extends Command(user) {

	override def execute(): Option[List[Post]] = {
		users postsBy user
	}

}
