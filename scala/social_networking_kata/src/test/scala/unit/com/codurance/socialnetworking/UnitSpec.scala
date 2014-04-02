package unit.com.codurance.socialnetworking

import org.scalatest._
import org.scalatest.mock.MockitoSugar
import org.scalamock.scalatest.MockFactory


abstract class UnitSpec extends FlatSpec
						with Matchers
						with OptionValues
						with Inside
						with Inspectors
						with MockitoSugar
