package com.codurance.socialnetworking.user_interface

import com.codurance.socialnetworking.domain.Post

class View(console: Console) {
	def userCommand(): String = {
		console readline()
	}


	def display(posts: List[Post]) = {

	}

}
