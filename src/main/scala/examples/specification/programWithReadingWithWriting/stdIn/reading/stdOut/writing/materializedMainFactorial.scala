package examples.specification.programWithReadingWithWriting.stdIn.reading.stdOut.writing

import psbp.external.specification.programWithReadingWithWriting.ProgramWithReadingWithWriting

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.stdIn.StdIn

import psbp.external.implementation.stdOut.StdOut

def materializedMainFactorial[
  Z, Y
  , >-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithReadingWithWriting[StdIn[BigInt], StdOut, >-->]
                  : [>-->[- _, + _]] =>> Materialization[>-->, Z, Y]
]: Z ?=> Y =
  
  val materialization: Materialization[>-->, Z, Y] = 
    summon[Materialization[>-->, Z, Y]]
  import materialization.materialize

  materialize(mainFactorial)



