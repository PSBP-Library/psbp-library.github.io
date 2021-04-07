package examples.implementation.freeActive.program.effectful

import psbp.external.implementation.freeActive.{
  freeActiveProgram
  , freeActiveMaterialization
}
import examples.specification.program.effectful.mainOptimizedFibonacci

@main def optimizedFibonacci(args: String*): Unit =
  mainOptimizedFibonacci materialized ()
