package examples.implementation.active.program.implementation.rec.list.aggregatable.effectful

import psbp.implementation.active.given

import psbp.external.implementation.rec.list.given

import psbp.external.implementation.rec.recRecursive

import psbp.external.implementation.list.listRecursiveAggregatable


import examples.specification.program.implementation.rec.list.aggregatable.effectful.mainAreAllPositive

@main def areAllPositive(args: String*): Unit =
  mainAreAllPositive materialized ()

