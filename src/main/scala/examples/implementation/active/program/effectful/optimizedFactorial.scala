package examples.implementation.active.program.effectful

import psbp.external.implementation.active.{ 
  givenActiveProgram
  , givenActiveMaterialization
}

import examples.specification.program.effectful.materializedMainOptimizedFactorial

import examples.implementation.givenUnit

@main def optimizedFactorial(args: String*): Unit =
  materializedMainOptimizedFactorial
