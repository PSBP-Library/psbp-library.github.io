package examples.specification.programWithParallel.effectful

import psbp.external.specification.programWithParallel.ProgramWithParallel

import psbp.external.specification.materialization.Materialization

def materializedMainParallelFibonacci[
  Z, Y
  , >-->[- _, + _]: ProgramWithParallel
                  : [>-->[- _, + _]] =>> Materialization[>-->, Z, Y]
]: Z ?=> Y =
  
  val materialization: Materialization[>-->, Z, Y] = 
    summon[Materialization[>-->, Z, Y]]
  import materialization.materialize

  materialize(mainParallelFibonacci)