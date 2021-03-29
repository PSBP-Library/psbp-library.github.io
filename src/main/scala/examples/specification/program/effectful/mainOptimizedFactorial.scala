package examples.specification.program.effectful

import psbp.external.specification.program.Program

import examples.specification.program.optimizedFactorial

def mainOptimizedFactorial[
  >-->[- _, + _]: Program
  ]: Unit >--> Unit =
  optimizedFactorial toMainWith (
    producer = intProducer,
    consumer = factorialConsumer
  )
  
