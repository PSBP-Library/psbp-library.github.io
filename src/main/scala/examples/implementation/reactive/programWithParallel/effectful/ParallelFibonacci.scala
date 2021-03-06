package examples.implementation.reactive.programWithParallel.effectful

import psbp.implementation.programWithParallel

import psbp.implementation.reactive.given

import examples.specification.programWithParallel.effectful.mainParallelFibonacci

@main def fibonacci(args: String*): Unit =
  mainParallelFibonacci materialized ()
