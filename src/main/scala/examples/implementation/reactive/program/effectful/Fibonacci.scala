package examples.implementation.reactive.program.effectful

import psbp.implementation.reactive.given

import examples.specification.program.effectful.mainFibonacci

@main def fibonacci(args: String*): Unit =
  mainFibonacci materialized ()

