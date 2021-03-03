package examples.specification.programWithParallel.effectful

import psbp.specification.types.&&

import psbp.specification.programWithParallel.ProgramWithParallel

import examples.specification.programWithParallel.parallelFibonacci 

import examples.specification.program.effectful.intProducer

def mainParallelFibonacci[>-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithParallel[>-->]]: Unit >--> Unit =
  parallelFibonacci toMainWith (
    producer = intProducer,
    consumer = parallelFibonacciConsumer
  )
