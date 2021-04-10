package examples.implementation.active.program.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.active.{ 
  given Program
  , given Materialization
}

import examples.specification.program.effectful.mainFactorial

@main def factorial(args: String*): Unit =
  mainFactorial materialized ()

