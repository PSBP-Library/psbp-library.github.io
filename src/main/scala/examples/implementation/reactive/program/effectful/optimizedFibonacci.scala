package examples.implementation.reactive.program.effectful

import psbp.external.implementation.reactive.{ 
  givenReactiveProgram
  , givenReactiveMaterialization
}

import examples.specification.program.effectful.materializedMainOptimizedFibonacci

import examples.implementation.givenUnit

@main def optimizedFibonacci(args: String*): Unit =
  materializedMainOptimizedFibonacci
