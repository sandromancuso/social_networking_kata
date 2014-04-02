package com.codurance.socialnetworking.infrastructure

import java.util.{Date, Calendar}

class Clock {

	def current_time(): Date = {
		Clock now
	}

}

object Clock {

	def now(): Date = {
		Calendar getInstance() getTime
	}

}
