package examples.specification.programWithState.effectful

import psbp.external.specification.programWithState.ProgramWithState

import examples.specification.programWithState.{ 
  Seed
  , twoRandoms
}  

def mainTwoRandoms[
  >-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithState[Seed, >-->]
]: Unit >--> Unit =
  twoRandoms toMainWith (
    producer = unitProducer,
    consumer = twoRandomsConsumer
  )
