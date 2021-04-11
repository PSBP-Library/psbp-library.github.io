package examples.implementation.reactive.programWithParallel.effectful

import psbp.external.specification.programWithParallel.givenProgramWithParallel

import psbp.external.implementation.reactive.{ 
  givenReactiveProgram
  , givenReactiveParallel
  , givenReactiveMaterialization
}

import examples.implementation.givenUnit

import examples.specification.programWithParallel.effectful.materializedMainParallelFibonacci

@main def parallelFibonacci(args: String*): Unit =
  materializedMainParallelFibonacci

