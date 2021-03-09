package examples.implementation.reactive.programWithParallel.effectful

import psbp.specification.programWithParallel.given

import psbp.implementation.reactive.given

import examples.specification.programWithParallel.effectful.mainParallelFibonacci

@main def parallelFibonacci(args: String*): Unit =
  mainParallelFibonacci materialized ()
