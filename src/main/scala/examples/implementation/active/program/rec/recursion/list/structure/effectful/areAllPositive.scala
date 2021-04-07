package examples.implementation.active.program.rec.recursion.list.structure.effectful

import psbp.external.implementation.active.{
  activeProgram
  , activeMaterialization
}

import psbp.external.implementation.rec.recRecursion

import psbp.external.implementation.list.listRecursiveStructure

import examples.specification.program.recursive.structure.implementation.list.effectful.mainAreAllPositive

@main def areAllPositive(args: String*): Unit =
  mainAreAllPositive materialized ()

