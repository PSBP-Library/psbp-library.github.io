package examples.implementation.reactive.program.effectful

import psbp.external.implementation.reactive.{
  reactiveProgram
  , reactiveMaterialization
}
import examples.specification.program.effectful.mainFibonacci

@main def fibonacci(args: String*): Unit =
  mainFibonacci materialized ()

