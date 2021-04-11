package examples.implementation.freeActive.program.effectful

import psbp.external.implementation.freeActive.{ 
  givenFreeActiveProgram
  , givenFreeActiveMaterialization
}

import examples.specification.program.effectful.materializedMainFactorial

import examples.implementation.givenUnit

@main def factorial(args: String*): Unit =
  materializedMainFactorial

