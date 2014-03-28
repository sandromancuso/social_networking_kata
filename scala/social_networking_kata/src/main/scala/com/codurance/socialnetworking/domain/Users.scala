package com.codurance.socialnetworking.domain

import scala.collection.mutable

class Users {

	val users: mutable.HashMap[String, mutable.MutableList[String]] = mutable.HashMap()

	def postsBy(user_name: String): Option[List[String]] = {
		users.get(user_name) match {
			case Some(posts) => Some(posts.toList)
			case _ => None
		}
	}

	def add(user_name: String, post: String) = {
		users.get(user_name) match {
			case Some(posts) => posts.+=:(post)
			case _ => users += (user_name -> mutable.MutableList(post))
		}
	}

	def follow(user: String, follows: String) = {

	}

	def wall(user_name: String): Option[List[String]] = {
		None
	}

}
