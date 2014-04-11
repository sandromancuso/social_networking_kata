package com.codurance.socialnetworking.domain

import scala.collection.mutable
import com.codurance.socialnetworking.infrastructure.Clock

class Users(clock: Clock) {

	val users: mutable.HashMap[String, User] = mutable.HashMap()
	val posts: mutable.MutableList[Post] = mutable.MutableList()

	def postsBy(user_name: String): Option[List[Post]] = {
		users.get(user_name) match {
			case Some(user) => Some(posts.filter(_.isFrom(user)).toList)
			case _ => None
		}
	}

	def newPost(user_name: String, post_message: String) = {
		val user = userBy(user_name)  // makes sure a user is created
		val post = Post(user.user_name, post_message, clock.current_time())

		post +=: posts
	}

	def follow(user_name: String, user_name_to_be_followed: String) = {
		userBy(user_name) follows userBy(user_name_to_be_followed)
	}

	def wall(user_name: String): Option[List[Post]] = {
		users.get(user_name) match {
			case Some(user) => Some(posts.filter(p => isWallPost(user, p)).toList)
			case _          => None
		}
	}

	private def isWallPost(user: User, post: Post): Boolean = {
		post.isFrom(user) || post.isFromOneOf(user.followedUsers():_*)
	}

	private def userBy(user_name: String): User = {
		users getOrElse(user_name, newUserFor(user_name))
	}

	private def newUserFor(user_name: String) = {
		val new_user = new User(user_name)
		users += (user_name -> new_user)
		new_user
	}
	
}
