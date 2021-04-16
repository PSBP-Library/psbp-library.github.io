package examples.implementation.active.programWithReadingAndWriting.reading.stdIn.writing.stdOut

import psbp.external.specification.program.Program

// import psbp.external.specification.programWithReadingWithWriting.ProgramWithReadingWithWriting

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.stdIn.StdIn

import psbp.external.implementation.stdOut.StdOut

import psbp.external.implementation.stdOut.givenStdOutProgramWithWriting

import psbp.external.specification.programWithReading.givenFromReadableProducer

import psbp.external.specification.programWithWriting.givenToWritableConsumer

import psbp.external.implementation.stdIn.givenArgumentFromStdInReadable

import psbp.external.implementation.stdIn.givenStdInProgramWithReading

import psbp.external.implementation.stdOut.givenStdOutWritable


def materializedMainFactorial[
  Z, Y
  , >-->[- _, + _]: Program 
                  : [>-->[- _, + _]] =>> Materialization[>-->, Z, Y]
]: Z ?=> Y =
  
  val materialization: Materialization[>-->, Z, Y] = 
    summon[Materialization[>-->, Z, Y]]
  import materialization.materialize

  materialize(mainFactorial)



