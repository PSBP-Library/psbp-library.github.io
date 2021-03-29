package examples.specification.programWithState

import psbp.external.specification.types.&&

import psbp.external.specification.programWithState.ProgramWithState

def twoRandoms[
  >-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithState[Seed, >-->]
  , Z
  ]: Z >--> (BigInt && BigInt) =
  random && random