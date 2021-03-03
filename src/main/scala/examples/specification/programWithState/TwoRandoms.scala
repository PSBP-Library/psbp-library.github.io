package examples.specification.programWithState

import psbp.specification.types.&&

import psbp.specification.programWithState.ProgramWithState

def twoRandoms[Z, >-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithState[Seed, >-->]]: Z >--> (BigInt && BigInt) =
  random && random