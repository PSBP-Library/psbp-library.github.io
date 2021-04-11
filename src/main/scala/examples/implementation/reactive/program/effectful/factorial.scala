package examples.implementation.reactive.program.effectful

import psbp.external.implementation.reactive.{ 
  givenReactiveProgram
  , givenReactiveMaterialization
}

import examples.specification.program.effectful.materializedMainFactorial

import examples.implementation.givenUnit

@main def factorial(args: String*): Unit =
  materializedMainFactorial
