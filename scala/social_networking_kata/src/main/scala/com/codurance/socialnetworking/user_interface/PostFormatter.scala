package com.codurance.socialnetworking.user_interface

import com.codurance.socialnetworking.domain.Post

class PostFormatter {

	def format(posts: List[Post]): List[String] = {
		posts.map(p => p.user_name + " - " + p.message)
	}

}
