package examples.specification.programWithReadingWithWriting.stdIn.reading.stdOut.writing

import psbp.external.specification.programWithReadingWithWriting.{
  ProgramWithReadingWithWriting
  , toMain
  } 

import psbp.external.implementation.stdIn.{
  StdIn
  , givenStdInReadable
}

import psbp.external.implementation.stdOut.{
  StdOut
  , givenStdOutWritable
}  

import examples.specification.programWithReading.stdIn.reading.givenBigIntFromStdInBigInt

import examples.specification.programWithWriting.stdOut.writing.factorialOfBigIntToStdOut

import examples.specification.program.factorial

def mainFactorial[
  >-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithReadingWithWriting[StdIn[BigInt], StdOut, >-->]
]: Unit >--> Unit =
  toMain[
    BigInt, BigInt
    , StdIn[BigInt], StdOut
    , >-->
  ](factorial)






