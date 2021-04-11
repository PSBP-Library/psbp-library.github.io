package examples.specification.programWithState.effectful

import psbp.external.specification.programWithState.ProgramWithState

import psbp.external.specification.materialization.Materialization

import examples.specification.programWithState.Seed

def materializedMainTwoRandoms[
  Z, Y
  , >-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithState[Seed, >-->]
                  : [>-->[- _, + _]] =>> Materialization[>-->, Z, Y]
]: Z ?=> Y =
  
  val materialization: Materialization[>-->, Z, Y] = 
    summon[Materialization[>-->, Z, Y]]
  import materialization.materialize

  materialize(mainTwoRandoms)