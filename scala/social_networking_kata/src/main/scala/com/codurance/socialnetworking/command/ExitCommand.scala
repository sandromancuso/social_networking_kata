package com.codurance.socialnetworking.command

import com.codurance.socialnetworking.domain.Post
import com.codurance.socialnetworking.infrastructure.System

class ExitCommand(userCommand: String, system: System) extends Command(userCommand: String) {

	def this(system: System) = this("", system)

	override def execute(): Option[List[Post]] = {
		print("bye!")
		system.exit()
		None
	}
}
