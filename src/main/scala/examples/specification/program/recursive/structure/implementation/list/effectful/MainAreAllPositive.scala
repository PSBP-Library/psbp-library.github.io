package examples.specification.program.recursive.structure.implementation.list.effectful

import psbp.external.specification.program.Program 

import psbp.external.specification.recursion.Recursion

import psbp.external.specification.structure.recursive.RecursiveStructure

import psbp.external.implementation.list.List

import examples.specification.program.recursive.structure.implementation.list.areAllPositive

def mainAreAllPositive[
  R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Program
                  : [>-->[- _, + _]] =>> RecursiveStructure[List, R, >-->]
]: Unit >--> Unit =
  areAllPositive toMainWith (
    producer = recursiveIntListProducer,
    consumer = areAllPositiveConsumer
  )

