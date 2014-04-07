package com.codurance.socialnetworking.domain

import java.time.LocalDateTime

case class Post(user_name: String,
                message: String,
                date: LocalDateTime = LocalDateTime.now()) {

}
