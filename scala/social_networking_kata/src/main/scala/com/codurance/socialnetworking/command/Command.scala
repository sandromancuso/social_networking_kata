package com.codurance.socialnetworking.command

trait Command {

	def execute(userCommand: String): Option[List[String]]

}
