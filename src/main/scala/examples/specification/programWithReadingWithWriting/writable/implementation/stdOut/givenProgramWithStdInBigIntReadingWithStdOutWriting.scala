package examples.specification.programWithReadingWithWriting.writable.implementation.stdOut

import psbp.external.specification.program.Program

import psbp.external.specification.program.reading.Reading

import psbp.external.specification.program.writing.Writing

import psbp.external.specification.programWithReadingWithWriting.{
  ProgramWithReadingWithWriting
  , givenProgramWithReadingWithWriting
}  

import psbp.external.implementation.stdIn.StdIn

import psbp.external.implementation.stdOut.{
  StdOut
  , givenStdOutWritable
} 

given givenProgramWithStdInBigIntReadingWithStdOutWriting[
  >-->[- _, + _]: Program
                : [>-->[- _, + _]] =>> Reading[StdIn[BigInt], >-->]
                : [>-->[- _, + _]] =>> Writing[StdOut, >-->]
]: ProgramWithReadingWithWriting[StdIn[BigInt], StdOut, >-->] = givenProgramWithReadingWithWriting[StdIn[BigInt], StdOut, >-->]