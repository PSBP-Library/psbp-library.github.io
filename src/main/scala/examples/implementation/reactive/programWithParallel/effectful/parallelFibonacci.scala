package examples.implementation.reactive.programWithParallel.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.program.parallel.Parallel

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.reactive.{
  given Program
  , given Parallel
  , given Materialization
}

// given
import psbp.external.specification.programWithParallel.programWithParallel

import examples.specification.programWithParallel.effectful.mainParallelFibonacci

@main def parallelFibonacci(args: String*): Unit =
  mainParallelFibonacci materialized ()
