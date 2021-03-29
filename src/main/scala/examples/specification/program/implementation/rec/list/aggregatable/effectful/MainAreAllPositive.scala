package examples.specification.program.implementation.rec.list.aggregatable.effectful

import psbp.external.specification.program.Program

import psbp.external.implementation.list.{ List }

import psbp.external.specification.aggregatable.recursive.{ RecursiveAggregatable }

import psbp.external.implementation.rec.Rec

import psbp.external.implementation.rec.recRecursive

import examples.specification.program.implementation.recursive.aggregatable.implementation.list.areAllPositive

def mainAreAllPositive[
  >-->[- _, + _]: Program
                : [>-->[- _, + _]] =>> RecursiveAggregatable[List, Rec, >-->]]: Unit >--> Unit =
  areAllPositive toMainWith (
    producer = intRecListProducer,
    consumer = areAllPositiveConsumer
  )
