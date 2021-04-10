package examples.implementation.freeActive.program.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.freeActive.{ 
  given Program
  , given Materialization
}

import examples.specification.program.effectful.mainFactorial

@main def factorial(args: String*): Unit =
  mainFactorial materialized ()

