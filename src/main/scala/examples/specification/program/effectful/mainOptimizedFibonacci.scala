package examples.specification.program.effectful

import psbp.external.specifcation.program.Program

import examples.specification.program.optimizedFibonacci

def mainOptimizedFibonacci[
  >-->[- _, + _]: Program
  ]: Unit >--> Unit =
  optimizedFibonacci toMainWith (
    producer = intProducer,
    consumer = fibonacciConsumer
  )
