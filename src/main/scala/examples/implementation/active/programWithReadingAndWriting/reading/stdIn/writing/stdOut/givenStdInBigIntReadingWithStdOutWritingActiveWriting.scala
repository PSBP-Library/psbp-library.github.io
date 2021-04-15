package examples.implementation.active.programWithReadingAndWriting.reading.stdIn.writing.stdOut

import psbp.external.specification.program.writing.Writing

import psbp.external.implementation.readingWithWritingActive.{
  `=>RWA`
  , givenReadingWithWritingActiveWriting
} 

import psbp.external.implementation.stdIn.StdIn

import psbp.external.implementation.stdOut.StdOut

import psbp.external.implementation.stdIn.givenStdInReadable

import psbp.external.implementation.stdOut.givenStdOutWritable

given givenStdInBigIntReadingWithStdOutWritingActiveWriting: Writing[StdOut, `=>RWA`[StdIn[BigInt], StdOut]] = 
  givenReadingWithWritingActiveWriting[StdIn[BigInt], StdOut]
