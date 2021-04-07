package examples.implementation.active.program.effectful

import psbp.external.implementation.active.{ 
  activeProgram
  , activeMaterialization
}

import examples.specification.program.effectful.mainFactorial

@main def factorial(args: String*): Unit =
  mainFactorial materialized ()

