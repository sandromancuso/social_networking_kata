package com.codurance.socialnetworking.command

import com.codurance.socialnetworking.domain.Post

abstract class Command(userCommand: String) {

	def execute(): Option[List[Post]]

}
