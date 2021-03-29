package examples.specification.program.effectful

import psbp.external.specification.program.Program

import examples.specification.program.optimizedFibonacci

def mainOptimizedFibonacci[
  >-->[- _, + _]: Program
  ]: Unit >--> Unit =
  optimizedFibonacci toMainWith (
    producer = intProducer,
    consumer = fibonacciConsumer
  )
