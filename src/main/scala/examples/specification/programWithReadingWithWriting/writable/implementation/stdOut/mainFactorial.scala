package examples.specification.programWithReadingWithWriting.writable.implementation.stdOut

import psbp.external.specification.programWithReadingWithWriting.{
  ProgramWithReadingWithWriting
  , toMain
} 

import psbp.external.implementation.stdIn.StdIn

import psbp.external.implementation.stdOut.{
  StdOut
  , givenStdOutWritable
}  

import examples.specification.program.reading.{
  givenStdInBigIntReading
  , givenBigIntFromStdInBigInt
}  

import examples.specification.program.writing.writable.implementation.stdOut.{
  givenStdOutWriting
  , factorialOfBigIntToStdOut
}

import examples.specification.program.factorial

def mainFactorial[
  >-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithReadingWithWriting[StdIn[BigInt], StdOut, >-->]
]: Unit >--> Unit =
  toMain[
    BigInt, BigInt
    , StdIn[BigInt], StdOut
    , >-->
  ]{
    println(">>> in mainFactorial")
    Thread.sleep(3000)
    factorial
  }

// import psbp.external.implementation.active.{ 
//   givenActiveProgram
//   , givenActiveMaterialization
// }

