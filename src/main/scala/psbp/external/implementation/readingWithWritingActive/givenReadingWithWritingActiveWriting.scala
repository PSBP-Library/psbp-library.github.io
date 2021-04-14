package psbp.external.implementation.readingWithWritingActive

import psbp.external.specification.program.writing.{
  Writable
  , Writing
}

import psbp.external.implementation.writingActive.WritingActive

import psbp.external.implementation.writingActive.givenWritingActiveWriting

import psbp.internal.implementation.computation.transformation.writing.readingTransformedWriting

given givenReadingWithWritingActiveWriting[R, W: Writable]: Writing[W, `=>RWA`[R, W]] = 
  readingTransformedWriting[R, W, WritingActive[W]]


