package examples.implementation.active.program.rec.recursion.list.structure.effectful

import psbp.external.implementation.active.{ 
  givenActiveProgram
  , givenActiveMaterialization
}
import psbp.external.implementation.rec.givenRecRecursion

import psbp.external.implementation.list.givenListRecursiveStructure

import examples.specification.program.list.recursive.structure.effectful.materializedMainAreAllPositive

import examples.implementation.givenUnit

@main def areAllPositive(args: String*): Unit =
  materializedMainAreAllPositive


