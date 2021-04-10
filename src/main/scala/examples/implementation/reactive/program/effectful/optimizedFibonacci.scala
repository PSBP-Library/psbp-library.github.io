package examples.implementation.reactive.program.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.reactive.{ 
  given Program
  , given Materialization
}

import examples.specification.program.effectful.mainOptimizedFibonacci

@main def optimizedFibonacci(args: String*): Unit =
  mainOptimizedFibonacci materialized ()
