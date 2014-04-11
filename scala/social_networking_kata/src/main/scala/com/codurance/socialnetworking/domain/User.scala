package com.codurance.socialnetworking.domain

import scala.collection.mutable.MutableList

case class User(user_name: String,
                followed_users: MutableList[User] = new MutableList[User]) {

	def follows(another_user: User) = {
		followed_users += another_user
	}

	def followedUsers() = {
		followed_users
	}
}