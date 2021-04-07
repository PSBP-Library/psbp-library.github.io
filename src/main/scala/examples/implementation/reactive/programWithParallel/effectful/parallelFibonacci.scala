package examples.implementation.reactive.programWithParallel.effectful

import psbp.external.implementation.reactive.{
  reactiveProgram
  , reactiveParallel
  , reactiveMaterialization
}

import psbp.external.specification.programWithParallel.programWithParallel

import examples.specification.programWithParallel.effectful.mainParallelFibonacci

@main def parallelFibonacci(args: String*): Unit =
  mainParallelFibonacci materialized ()
