package examples.implementation.reactive.program.effectful

import psbp.external.implementation.reactive.{
  reactiveProgram
  , reactiveMaterialization
}

import examples.specification.program.effectful.mainFactorial

@main def factorial(args: String*): Unit =
  mainFactorial materialized ()
