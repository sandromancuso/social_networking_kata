package com.codurance.socialnetworking.user_interface

class Console {

	val PROMPT = "> "

	def readline(): String = {
		print(PROMPT)
		scala.Console.readLine
	}

	def write(message: String) = {
		println(PROMPT + message)
	}

}
