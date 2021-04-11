package examples.implementation.active.program.effectful

import psbp.external.implementation.active.{ 
  givenActiveProgram
  , givenActiveMaterialization
}

import examples.specification.program.effectful.materializedMainFactorial

import examples.implementation.givenUnit

@main def factorial(args: String*): Unit =
  materializedMainFactorial

