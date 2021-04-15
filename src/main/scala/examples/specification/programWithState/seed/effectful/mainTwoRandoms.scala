package examples.specification.programWithState.seed.effectful

import psbp.external.specification.programWithState.ProgramWithState

import examples.specification.programWithState.seed.{ 
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
