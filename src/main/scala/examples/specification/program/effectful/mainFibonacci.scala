package examples.specification.program.effectful

import psbp.external.specifcation.program.Program

import examples.specification.program.fibonacci

def mainFibonacci[
  >-->[- _, + _]: Program
  ]: Unit >--> Unit =
  fibonacci toMainWith (
    producer = intProducer,
    consumer = fibonacciConsumer
  )
