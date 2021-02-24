package examples.implementation.active.program.effectful

import psbp.implementation.active.given

import examples.specification.program.effectful.mainFactorial

@main def factorial(args: String*): Unit =
  mainFactorial materialized ()

