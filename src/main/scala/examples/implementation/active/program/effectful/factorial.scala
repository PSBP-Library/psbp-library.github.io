package examples.implementation.active.program.effectful

import psbp.external.implementation.active.{ 
  givenActiveProgram
  , givenActiveMaterialization
}

import examples.implementation.givenUnit

import examples.specification.program.effectful.materializedMainFactorial

@main def factorial(args: String*): Unit =
  materializedMainFactorial

