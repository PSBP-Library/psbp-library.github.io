package examples.implementation.active.program.effectful

import psbp.external.implementation.active.{ 
  activeProgram
  , activeMaterialization
}

import examples.specification.program.effectful.mainOptimizedFactorial

@main def optimizedFactorial(args: String*): Unit =
  mainOptimizedFactorial materialized ()
