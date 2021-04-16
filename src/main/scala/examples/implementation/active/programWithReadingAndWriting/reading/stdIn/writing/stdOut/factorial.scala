package examples.implementation.active.programWithReadingAndWriting.reading.stdIn.writing.stdOut

import psbp.external.specification.programWithReadingWithWriting.givenProgramWithReadingWithWriting

import psbp.external.implementation.stdOut.givenStdOutWritable

import examples.implementation.givenUnit

import psbp.external.implementation.readingWithWritingActive.givenReadingWithWritingActiveMaterialization

@main def factorial(args: String*): Unit =
  materializedMainFactorial

