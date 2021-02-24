package examples.implementation.reactive.program.effectful

import psbp.implementation.reactive.given

import examples.specification.program.effectful.mainFactorial

@main def factorial(args: String*): Unit =
  mainFactorial materialized ()
