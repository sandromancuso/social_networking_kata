package com.codurance.socialnetworking.infrastructure

import java.time.LocalDateTime

class Clock {

	def current_time(): LocalDateTime = {
		LocalDateTime now()
	}

}

object Clock {

	def now(): LocalDateTime = {
		LocalDateTime now
	}

}
