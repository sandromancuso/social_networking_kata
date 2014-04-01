package com.codurance.socialnetworking.infrastructure

import java.util.{Date, Calendar}

object Clock {

	def now(): Date = {
		Calendar getInstance() getTime
	}

}
