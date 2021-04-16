package examples.implementation.active.programWithReadingAndWriting.reading.stdIn.writing.stdOut

import psbp.external.specification.program.reading.Reading

import psbp.external.implementation.readingWithWritingActive.{
  `=>RWA`
  , givenReadingWithWritingActiveReading
} 

import psbp.external.implementation.stdIn.StdIn

import psbp.external.implementation.stdOut.StdOut

import psbp.external.implementation.stdOut.givenStdOutWritable

given givenStdInBigIntReadingWithStdOutWritingActiveReading: Reading[StdIn[BigInt], `=>RWA`[StdIn[BigInt], StdOut]] = 
  givenReadingWithWritingActiveReading[StdIn[BigInt], StdOut]
