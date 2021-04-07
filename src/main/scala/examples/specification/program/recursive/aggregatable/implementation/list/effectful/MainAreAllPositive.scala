package examples.specification.program.implementation.rec.list.aggregatable.effectful

import scala.language.postfixOps

import scala.collection.immutable.Seq

import psbp.external.specification.program.Program 

import psbp.external.specification.recursion.Recursion

import psbp.external.implementation.list.List

import psbp.external.specification.aggregatable.recursive.{ 
  Recursive
  , RecursivelyAggregatable 
}

import examples.specification.program.recursive.aggregatable.implementation.list.areAllPositive

def mainAreAllPositive[
  R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Program
                  : [>-->[- _, + _]] =>> RecursivelyAggregatable[List, R, >-->]
]: Unit >--> Unit =
  areAllPositive toMainWith (
    producer = recursiveIntListProducer,
    consumer = areAllPositiveConsumer
  )

