package examples.specification.programWithState

import psbp.external.specifcation.types.&&

import psbp.external.specifcation.programWithState.ProgramWithState

def twoRandoms[
  >-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithState[Seed, >-->]
  , Z
  ]: Z >--> (BigInt && BigInt) =
  random && random