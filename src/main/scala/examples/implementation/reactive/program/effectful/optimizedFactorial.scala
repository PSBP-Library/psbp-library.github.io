package examples.implementation.reactive.program.effectful

import psbp.external.implementation.reactive.{ 
  givenReactiveProgram
  , givenReactiveMaterialization
}

import examples.specification.program.effectful.materializedMainOptimizedFactorial

import examples.implementation.givenUnit

@main def optimizedFactorial(args: String*): Unit =
  materializedMainOptimizedFactorial
