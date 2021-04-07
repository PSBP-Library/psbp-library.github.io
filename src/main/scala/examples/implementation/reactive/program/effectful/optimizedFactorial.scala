package examples.implementation.reactive.program.effectful

import psbp.external.implementation.reactive.{
  reactiveProgram
  , reactiveMaterialization
}
import examples.specification.program.effectful.mainOptimizedFactorial

@main def optimizedFactorial(args: String*): Unit =
  mainOptimizedFactorial materialized ()
