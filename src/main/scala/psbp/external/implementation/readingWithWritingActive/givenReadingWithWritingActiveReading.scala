package psbp.external.implementation.readingWithWritingActive

import psbp.external.specification.program.reading.Reading

import psbp.external.specification.program.writing.Writable

import psbp.external.implementation.writingActive.WritingActive

import psbp.external.implementation.writingActive.givenWritingActiveComputation

import psbp.internal.implementation.computation.transformation.readingTransformedReading

given givenReadingWithWritingActiveReading[R, W: Writable]: Reading[R, `=>RWA`[R, W]] = 
  readingTransformedReading[R, WritingActive[W]]
