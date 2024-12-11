package com.example.mycompany.moduleb

import org.scalatest.funsuite.AnyFunSuiteLike

import org.scalatest.matchers.should.Matchers.*

class BarTest extends AnyFunSuiteLike {

  test("testReallyHappy") {
    val result = Bar.reallyHappy(2)
    val happies: List[String] = result.splitAt(result.lastIndexOf("happy")).toList
    assertResult("happy".length * 2)(result.length)
    assertResult(List("happy", "happy"))(happies)
  }

}
