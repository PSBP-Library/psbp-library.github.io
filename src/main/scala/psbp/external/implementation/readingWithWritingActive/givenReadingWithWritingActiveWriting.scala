package psbp.external.implementation.readingWithWritingActive

import psbp.external.specification.program.writing.{
  Writable
  , Writing
}

import psbp.external.specification.program.reading.Readable

import psbp.external.implementation.writingActive.WritingActive

import psbp.internal.implementation.computation.transformation.writing.readingTransformedWriting

import psbp.external.implementation.writingActive.givenWritingActiveWriting

given givenReadingWithWritingActiveWriting[
  R: Readable
  , W: Writable
]: Writing[W, `=>RWA`[R, W]] = 
  readingTransformedWriting[R, W, WritingActive[W]]


