package examples.implementation.reactive.programWithParallel.effectful

import psbp.implementation.reactive.given

import psbp.internalImplementation.programWithParallel.given

import examples.specification.programWithParallel.effectful.mainParallelFibonacci

@main def fibonacci(args: String*): Unit =
  mainParallelFibonacci materialized ()
