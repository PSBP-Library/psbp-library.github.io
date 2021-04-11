package examples.implementation.active.program.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.active.{ 
  given Program
  , given Materialization
}

import examples.specification.program.effectful.mainOptimizedFibonacci

import scala.language.postfixOps

import examples.implementation.givenUnit

@main def optimizedFibonacci(args: String*): Unit =
  mainOptimizedFibonacci materialized // ()

