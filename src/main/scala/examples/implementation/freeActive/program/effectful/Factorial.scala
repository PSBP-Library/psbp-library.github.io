package examples.implementation.freeActive.program.effectful

import psbp.implementation.freeActive.given

import examples.specification.program.effectful.mainFactorial

@main def factorial(args: String*): Unit =
  mainFactorial materialized ()

