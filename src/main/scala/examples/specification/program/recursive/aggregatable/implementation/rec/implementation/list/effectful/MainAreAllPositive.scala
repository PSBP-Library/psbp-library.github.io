package examples.specification.program.recursive.aggregatable.implementation.rec.implementation.list.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.aggregatable.recursive.RecursivelyAggregatable

import psbp.external.implementation.rec.{
  Rec
  , recRecursive
}  

import psbp.external.implementation.list.List

import examples.specification.program.recursive.aggregatable.implementation.list.areAllPositive

import examples.specification.program.implementation.rec.list.aggregatable.effectful.areAllPositiveConsumer

def mainAreAllPositive[
  >-->[- _, + _]: Program
                : [>-->[- _, + _]] =>> RecursivelyAggregatable[List, Rec, >-->]
]: Unit >--> Unit =
  areAllPositive toMainWith (
    producer = recursiveIntListProducer,
    consumer = areAllPositiveConsumer
  )
