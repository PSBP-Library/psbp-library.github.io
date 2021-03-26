package examples.specification.program.implementation.rec.list.aggregatable.effectful

import psbp.external.specifcation.program.Program

import psbp.implementation.rec.list.RecListAggregatable

import examples.specification.program.implementation.rec.list.aggregatable.areAllPositive

def mainAreAllPositive[>-->[- _, + _]: Program: RecListAggregatable]: Unit >--> Unit =
  areAllPositive toMainWith (
    producer = intRecListProducer,
    consumer = areAllPositiveConsumer
  )
