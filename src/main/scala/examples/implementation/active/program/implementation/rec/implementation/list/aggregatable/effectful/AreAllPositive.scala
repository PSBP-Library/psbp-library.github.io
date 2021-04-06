package examples.implementation.active.program.implementation.rec.implementation.list.aggregatable.effectful

import psbp.implementation.active.given

import psbp.external.implementation.rec.given 

import psbp.external.implementation.list.given 

import examples.specification.program.recursive.aggregatable.implementation.rec.implementation.list.effectful.mainAreAllPositive

@main def areAllPositive(args: String*): Unit =
  mainAreAllPositive materialized ()

