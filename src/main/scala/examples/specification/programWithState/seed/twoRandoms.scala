package examples.specification.programWithState.seed

import psbp.external.specification.types.&&

import psbp.external.specification.programWithState.ProgramWithState

def twoRandoms[
  >-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithState[Seed, >-->]
]: Unit >--> (BigInt && BigInt) =  
  random && random