package examples.specification.program.effectful

import psbp.external.specifcation.program.Program

import examples.specification.program.factorial

def mainFactorial[
  >-->[- _, + _]: Program
  ]: Unit >--> Unit =
  factorial toMainWith (
    producer = intProducer,
    consumer = factorialConsumer
  )
