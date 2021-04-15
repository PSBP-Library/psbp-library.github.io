package psbp.external.implementation.readingWithWritingActive

import psbp.external.specification.program.reading.{
  Readable
  , Reading
}

import psbp.external.specification.program.writing.Writable

import psbp.external.implementation.writingActive.WritingActive

import psbp.internal.implementation.computation.transformation.readingTransformedReading

import psbp.external.implementation.writingActive.givenWritingActiveComputation

given givenReadingWithWritingActiveReading[
  R: Readable
  , W: Writable
]: Reading[R, `=>RWA`[R, W]] = 
  readingTransformedReading[R, WritingActive[W]]
