package examples.specification.program.implementation.rec.list.aggregatable.effectful

import psbp.external.specifcation.program.Program

import psbp.implementation.rec.list.{ List }


// import psbp.implementation.rec.list.RecListAggregatable

import psbp.external.specifcation.aggregatable.rec.RecAggregatable

import examples.specification.program.implementation.rec.list.aggregatable.areAllPositive

// def mainAreAllPositive[>-->[- _, + _]: Program: RecAggregatable[List]]: Unit >--> Unit =
def mainAreAllPositive[>-->[- _, + _]: Program: [>-->[- _, + _]] =>> RecAggregatable[List, >-->]]: Unit >--> Unit =
  areAllPositive toMainWith (
    producer = intRecListProducer,
    consumer = areAllPositiveConsumer
  )
