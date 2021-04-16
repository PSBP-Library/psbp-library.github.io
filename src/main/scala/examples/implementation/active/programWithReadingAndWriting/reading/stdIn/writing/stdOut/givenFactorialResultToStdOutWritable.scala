package examples.implementation.active.programWithReadingAndWriting.reading.stdIn.writing.stdOut

import psbp.external.specification.types.&&

import psbp.external.specification.program.Program

import psbp.external.specification.program.writing.ToWritable

import psbp.external.implementation.stdOut.{
  StdOut
  , givenResultToStdOutWritable
}

given String = "factorial"

given givenFactorialResultToStdOutWritable[
  >-->[- _, + _]: Program 
]: ToWritable[(BigInt && BigInt), StdOut, >-->] =
  givenResultToStdOutWritable


