package examples.implementation.active.program.effectful

import psbp.external.implementation.active.{ 
  activeProgram
  , activeMaterialization
}

import examples.specification.program.effectful.mainOptimizedFibonacci

@main def optimizedFibonacci(args: String*): Unit =
  mainOptimizedFibonacci materialized ()

