package com.codurance.socialnetworking.command

abstract class Command(userCommand: String) {

	def execute(): Option[List[String]]

}
