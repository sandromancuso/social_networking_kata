package com.codurance.socialnetworking.domain

import scala.collection.mutable

class Users {

	val users: mutable.HashMap[String, User] = mutable.HashMap()

	def postsBy(user_name: String): Option[List[Post]] = {
		users.get(user_name) match {
			case Some(user) => Some(user allPosts)
			case _ => None
		}
	}

	def newPost(user_name: String, post_message: String) = {
		val user = userBy(user_name)
		val post = Post(post_message)

		user add post
	}

	def follow(user_name: String, user_name_to_be_followed: String) = {
		userBy(user_name) follows userBy(user_name_to_be_followed)
	}

	def wall(user_name: String): Option[List[Post]] = {
		users.get(user_name) match {
			case Some(user) => Some(wallFor(user))
			case _ => None
		}
	}

	private def userBy(user_name: String): User = {
		users getOrElse(user_name, newUserFor(user_name))
	}

	private def newUserFor(user_name: String) = {
		val new_user = new User(user_name)
		users += (user_name -> new_user)
		new_user
	}
	
	private def wallFor(user: User): List[Post] = {
		val posts_from_friends = user.followedUsers().map(u => u.allPosts()).get(0).get
		user.allPosts() ++ posts_from_friends
	}

}
