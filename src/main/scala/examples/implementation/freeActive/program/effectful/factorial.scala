package examples.implementation.freeActive.program.effectful

import psbp.external.implementation.freeActive.{
  freeActiveProgram
  , freeActiveMaterialization
}

import examples.specification.program.effectful.mainFactorial

@main def factorial(args: String*): Unit =
  mainFactorial materialized ()

