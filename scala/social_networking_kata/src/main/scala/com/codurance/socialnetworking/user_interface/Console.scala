package com.codurance.socialnetworking.user_interface

class Console {

	def readline(): String = {
		scala.Console.readLine
	}

	def write(message: String) = {
		scala.Console println message
	}

	def print(message: String) = {
		scala.Console print message
	}

}
