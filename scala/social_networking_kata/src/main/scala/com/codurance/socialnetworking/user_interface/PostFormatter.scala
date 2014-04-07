package com.codurance.socialnetworking.user_interface

import com.codurance.socialnetworking.domain.Post
import java.time.LocalDateTime
import com.codurance.socialnetworking.infrastructure.Clock
import java.time.temporal.ChronoUnit

class PostFormatter(clock: Clock) {

	def format(posts: List[Post]): List[String] = {
		posts.map(p => {
			s"${p.user_name} - ${p.message} (${calculate_elapsed_time(p.date)})"
		})
	}

	private def calculate_elapsed_time(post_date: LocalDateTime): String = {
		val now = clock current_time()
		time_units.find(_._1.between(post_date, now) > 0) match {
			case Some(time_unit) => elapsed_time(time_unit, post_date, now)
			case _ => "just now"
		}
	}

	private def elapsed_time(time_unit: (ChronoUnit, String), post_date: LocalDateTime, now: LocalDateTime) = {
		var pretty_time = ""
		val amount = time_unit._1.between(post_date, now)
		if (amount > 0) {
			pretty_time = s"$amount ${time_unit._2}"
			if (amount > 1) {
				pretty_time += "s"
			}
			pretty_time += " ago"
		}
		pretty_time
	}

	val time_units = List(
		(ChronoUnit.DAYS, "day"),
		(ChronoUnit.HOURS, "hour"),
		(ChronoUnit.MINUTES, "minute"),
		(ChronoUnit.SECONDS, "second")
	)

}
