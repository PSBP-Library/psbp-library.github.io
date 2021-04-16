package examples.implementation.active.programWithReadingAndWriting.reading.stdIn.writing.stdOut

import psbp.external.specification.program.Program

import psbp.external.implementation.readingWithWritingActive.{
  `=>RWA`
  , givenReadingWithWritingActiveProgram
} 

import psbp.external.implementation.stdIn.StdIn

import psbp.external.implementation.stdOut.StdOut

import psbp.external.implementation.stdOut.givenStdOutWritable

given givenStdInBigIntReadingWithStdOutWritingActiveProgram: Program[`=>RWA`[StdIn[BigInt], StdOut]] = 
  givenReadingWithWritingActiveProgram[StdIn[BigInt], StdOut]
