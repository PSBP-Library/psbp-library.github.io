package examples.implementation.freeActive.program.effectful

import psbp.implementation.freeActive.given

import examples.specification.program.effectful.mainOptimizedFibonacci

@main def optimizedFibonacci(args: String*): Unit =
  mainOptimizedFibonacci materialized ()
