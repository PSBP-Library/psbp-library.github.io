
package examples.implementation.active.programWithReadingAndWriting.reading.stdIn.writing.stdOut

import psbp.external.specification.programWithReadingWithWriting.givenProgramWithReadingWithWriting

// import psbp.external.implementation.readingWithWritingActive.{
//   givenReadingWithWritingActiveProgram
//   , givenReadingWithWritingActiveReading
//   , givenReadingWithWritingActiveWriting
// } 

import psbp.external.implementation.stdIn.givenStdInReadable

import psbp.external.implementation.stdOut.givenStdOutWritable

import examples.implementation.active.programWithReadingAndWriting.reading.stdIn.writing.stdOut.{
  givenStdInBigIntReadingWithStdOutWritingActiveProgram
  , givenStdInBigIntReadingWithStdOutWritingActiveReading
  , givenStdInBigIntReadingWithStdOutWritingActiveWriting
  , givenStdInBigIntReadingWithStdOutWritingActiveMaterialization
}

import examples.specification.programWithReadingWithWriting.stdIn.reading.stdOut.writing.materializedMainFactorial

import examples.implementation.givenUnit

// @main def factorial(args: String*): Unit =
//   materializedMainFactorial

val foo = ???

// package examples.implementation.active.programWithReadingWithWriting.stdOut.writable

// import psbp.external.specification.program.Program

// import psbp.external.specification.program.reading.Reading

// import psbp.external.specification.program.writing.Writing

// import psbp.external.specification.programWithReadingWithWriting.ProgramWithReadingWithWriting

// import psbp.external.specification.materialization.Materialization

// import psbp.external.implementation.active.{ 
//   `=>A`
//   , givenActiveProgram
//   , givenActiveMaterialization
// }

// import psbp.external.implementation.readingWithWritingActive.{ 
//   `=>RWA`
//   , givenReadingWithWritingActiveProgram
//   , givenReadingWithWritingActiveReading
//   , givenReadingWithWritingActiveWriting
//   // , givenReadingWithWritingActiveProgramWithReadingWithWriting
//   , givenReadingWithWritingActiveMaterialization
// }

// import psbp.external.implementation.stdIn.StdIn

// import psbp.external.implementation.stdOut.StdOut

// import psbp.external.implementation.stdOut.givenStdOutWritable

// import psbp.external.specification.programWithReadingWithWriting.givenProgramWithReadingWithWriting

// import examples.implementation.givenUnit

// import examples.specification.programWithReadingWithWriting.writable.implementation.stdOut.materializedMainFactorial

// given givenStdInBigIntReadingWithStdOutWritingActiveProgram: Program[`=>RWA`[StdIn[BigInt], StdOut]] =
//   givenReadingWithWritingActiveProgram[StdIn[BigInt], StdOut]

// given givenStdInBigIntReadingWithStdOutWritingActiveReading: Reading[StdIn[BigInt], `=>RWA`[StdIn[BigInt], StdOut]] =
//   givenReadingWithWritingActiveReading[StdIn[BigInt], StdOut]

// given givenStdInBigIntReadingWithStdOutWritingActiveWriting: Writing[StdOut, `=>RWA`[StdIn[BigInt], StdOut]] =
//   givenReadingWithWritingActiveWriting[StdIn[BigInt], StdOut]

// // given givenStdInBigIntReadingWithStdOutWritingActiveProgramWithReadingWithWriting: ProgramWithReadingWithWriting[StdIn[BigInt], StdOut, `=>RWA`[StdIn[BigInt], StdOut]] =
// //   givenReadingWithWritingActiveProgramWithReadingWithWriting[StdIn[BigInt], StdOut]

// given givenStdBigIntInReadingWithStdOuWritingActiveMaterialization: Materialization[`=>RWA`[StdIn[BigInt], StdOut], Unit, StdIn[BigInt] ?=> (StdOut, (StdOut, Unit))] =
//   givenReadingWithWritingActiveMaterialization[StdIn[BigInt], StdOut] 
  
// given givenStdInBigInt:StdIn[BigInt] = {
//   println("Please type an integer")
//   StdIn(BigInt(scala.io.StdIn.readInt))
// }

// @main def factorial(args: String*): Unit =
  
//   // materializedMainFactorial // [Unit, (StdOut, (StdOut, Unit)), `=>RWA`]

//   val foo: (StdOut, (StdOut, Unit)) = materializedMainFactorial[Unit, (StdOut, (StdOut, Unit)), `=>RWA`]

