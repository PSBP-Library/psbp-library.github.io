package examples.implementation.active.programWithReadingAndWriting.reading.stdIn.writing.stdOut

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.readingWithWritingActive.{
  `=>RWA`
  , givenReadingWithWritingActiveMaterialization
} 

import psbp.external.implementation.stdIn.StdIn

import psbp.external.implementation.stdOut.StdOut

import psbp.external.implementation.stdIn.givenStdInReadable

import psbp.external.implementation.stdOut.givenStdOutWritable

given givenStdInBigIntReadingWithStdOutWritingActiveMaterialization: Materialization[
  `=>RWA`[StdIn[BigInt], StdOut]
  , Unit
  , StdIn[BigInt] ?=> (StdOut, (StdOut, Unit))
] = givenReadingWithWritingActiveMaterialization[StdIn[BigInt], StdOut]
