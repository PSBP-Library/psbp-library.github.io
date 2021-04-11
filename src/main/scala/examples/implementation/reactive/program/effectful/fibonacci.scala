package examples.implementation.reactive.program.effectful

import psbp.external.implementation.reactive.{ 
  givenReactiveProgram
  , givenReactiveMaterialization
}

import examples.specification.program.effectful.materializedMainFibonacci

import examples.implementation.givenUnit

@main def fibonacci(args: String*): Unit =
  materializedMainFibonacci

