package examples.implementation.active.program.effectful

import psbp.external.implementation.active.{ 
  givenActiveProgram
  , givenActiveMaterialization
}

import examples.specification.program.effectful.materializedMainOptimizedFibonacci

import examples.implementation.givenUnit

@main def optimizedFibonacci(args: String*): Unit =
  materializedMainOptimizedFibonacci

