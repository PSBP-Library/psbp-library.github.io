package examples.implementation.active.program.effectful

import psbp.external.implementation.active.{ 
  givenActiveProgram
  , givenActiveMaterialization
}

import examples.implementation.givenUnit

import examples.specification.program.effectful.materializedMainFibonacci

@main def fibonacci(args: String*): Unit =
  materializedMainFibonacci
