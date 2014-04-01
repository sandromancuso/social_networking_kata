package com.codurance.socialnetworking.infrastructure

import java.util.{Date, Calendar}

class Clock {

	def now(): Date = {
		Calendar getInstance() getTime
	}

}
