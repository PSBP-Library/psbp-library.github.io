package examples.specification.program.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.materialization.Materialization

def materializedMainFibonacci[
  Z, Y
  , >-->[- _, + _]: Program
                  : [>-->[- _, + _]] =>> Materialization[>-->, Z, Y]
]: Z ?=> Y =
  
  val materialization: Materialization[>-->, Z, Y] = 
    summon[Materialization[>-->, Z, Y]]
  import materialization.materialize

  materialize(mainFibonacci)