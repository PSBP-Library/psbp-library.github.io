package examples.specification.program.implementation.rec.list.aggregatable

import psbp.external.specifcation.program.Program

import psbp.implementation.rec.list.{ List, RecList }

import psbp.implementation.rec.list.RecListAggregatable

import psbp.external.specifcation.aggregatable.rec.RecAggregatable

import examples.specification.function.{ constantTrue, and }

import examples.specification.functional.isPositive

def areAllPositive[>-->[- _, + _]: Program: RecListAggregatable]: RecList[BigInt] >--> Boolean =
// def areAllPositive[>-->[- _, + _]: Program: [>-->[- _, + _]] =>> RecAggregatable[List, >-->]]: RecList[BigInt] >--> Boolean =
  
  val recListAggregatable: RecListAggregatable[>-->] = summon[RecListAggregatable[>-->]]
  import recListAggregatable.recAggregate 

  // val recListAggregatable: RecAggregatable[List, >-->] = summon[RecAggregatable[List, >-->]]
  // import recListAggregatable.recAggregate 

  // import psbp.implementation.rec.list.ListStructure

  // def foo: recListAggregatable.Structure[Boolean, Boolean] = (constantTrue[Unit], and)

  recAggregate(isPositive, (constantTrue[Unit], and))