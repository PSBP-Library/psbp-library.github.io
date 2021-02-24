package examples.implementation.active.program.effectful

import psbp.implementation.active.given

import examples.specification.program.effectful.mainOptimizedFactorial

@main def optimizedFactorial(args: String*): Unit =
  mainOptimizedFactorial materialized ()
