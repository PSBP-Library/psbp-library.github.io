package examples.implementation.active.program.rec.recursion.list.structure.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.active.{
  given Program
  , given Materialization
}

// given
import psbp.external.implementation.rec.recRecursion

// given
import psbp.external.implementation.list.listRecursiveStructure

import examples.specification.program.recursive.structure.implementation.list.effectful.mainAreAllPositive

@main def areAllPositive(args: String*): Unit =
  mainAreAllPositive materialized ()

