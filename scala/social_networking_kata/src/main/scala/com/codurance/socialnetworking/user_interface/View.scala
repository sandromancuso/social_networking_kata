package com.codurance.socialnetworking.user_interface

import com.codurance.socialnetworking.domain.Post

class View(console: Console, postFormatter: PostFormatter) {

	val PROMPT = "> "

	def userCommand(): String = {
		console print(PROMPT)
		console readline()
	}

	def display(posts: List[Post]) = {
		postFormatter.format(posts) foreach(console write)
	}

}
