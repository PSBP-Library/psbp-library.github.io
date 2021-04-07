package examples.implementation.active.program.effectful

import psbp.external.implementation.active.{ 
  activeProgram
  , activeMaterialization
}
import examples.specification.program.effectful.mainFibonacci

@main def fibonacci(args: String*): Unit =
  mainFibonacci materialized ()
