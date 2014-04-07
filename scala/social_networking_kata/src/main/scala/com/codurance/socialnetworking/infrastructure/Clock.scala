package com.codurance.socialnetworking.infrastructure

import java.time.LocalDateTime
import Clock._

class Clock {

	def current_time(): LocalDateTime = {
		now()
	}

}

object Clock {

	def now(): LocalDateTime = {
		LocalDateTime now
	}

}
