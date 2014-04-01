package com.codurance.socialnetworking.domain

import scala.collection.mutable.MutableList

case class User(user_name: String,
                posts: MutableList[Post] = new MutableList[Post],
                followed_users: MutableList[User] = new MutableList[User]) {

	def add(post: Post) = {
		post +=: posts
	}

	def allPosts(): List[Post] = {
		posts.toList
	}

	def follows(another_user: User) = {
		followed_users += another_user
	}

	def followedUsers() = {
		followed_users
	}
}