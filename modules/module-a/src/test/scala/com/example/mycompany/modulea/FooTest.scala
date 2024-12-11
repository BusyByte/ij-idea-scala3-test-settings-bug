package com.example.mycompany.modulea

import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.matchers.should.Matchers.*

class FooTest extends AnyFunSuiteLike {

  test("testAddOne") {
    val two = Foo.addOne(1)
    two should be > 0
    assertResult(2)(two)
  }

}
