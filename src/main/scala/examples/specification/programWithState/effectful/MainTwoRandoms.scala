package examples.specification.programWithState.effectful

import psbp.specification.program.&&

import psbp.specification.programWithState.ProgramWithState

import examples.specification.programWithState.{ Seed, twoRandoms }  

def mainTwoRandoms[>-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithState[Seed, >-->]]: Unit >--> Unit =
  twoRandoms toMainWith (
    producer = unitProducer,
    consumer = twoRandomsConsumer
  )
