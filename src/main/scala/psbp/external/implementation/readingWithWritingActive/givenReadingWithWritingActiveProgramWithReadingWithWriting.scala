package psbp.external.implementation.readingWithWritingActive

import psbp.external.specification.program.writing.Writable

import psbp.external.specification.programWithReadingWithWriting.ProgramWithReadingWithWriting

import psbp.external.specification.programWithReadingWithWriting.givenProgramWithReadingWithWriting

given givenReadingWithWritingActiveProgramWithReadingWithWriting[R, W: Writable]: ProgramWithReadingWithWriting[R, W, `=>RWA`[R, W]] with

  import psbp.external.specification.program.Program

  import psbp.external.specification.program.reading.Reading

  import psbp.external.specification.program.writing.{
    Writable
    , Writing
  }

  val readingWithWritingActiveProgram: Program[`=>RWA`[R, W]] =
    givenReadingWithWritingActiveProgram

  val readingWithWritingActiveReading: Reading[R, `=>RWA`[R, W]] =
    givenReadingWithWritingActiveReading

  val readingWithWritingActiveWriting: Writing[W, `=>RWA`[R, W]] =
    givenReadingWithWritingActiveWriting

  export readingWithWritingActiveProgram.`z>-->z`
  export readingWithWritingActiveProgram.toProgram
  export readingWithWritingActiveProgram.andThen
  export readingWithWritingActiveProgram.construct
  export readingWithWritingActiveProgram.conditionally
  export readingWithWritingActiveReading.read
  export readingWithWritingActiveWriting.write





