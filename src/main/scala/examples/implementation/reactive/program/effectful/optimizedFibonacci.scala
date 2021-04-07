package examples.implementation.reactive.program.effectful

import psbp.external.implementation.reactive.{
  reactiveProgram
  , reactiveMaterialization
}
import examples.specification.program.effectful.mainOptimizedFibonacci

@main def optimizedFibonacci(args: String*): Unit =
  mainOptimizedFibonacci materialized ()
