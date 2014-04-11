package com.codurance.socialnetworking.domain

import java.time.LocalDateTime

case class Post(user_name: String,
                message: String,
                date: LocalDateTime = LocalDateTime.now()) {


	def isFrom(user: User): Boolean = user_name == user.user_name

	def isFromOneOf(users: User*): Boolean = users.exists(_.user_name == user_name)

}
