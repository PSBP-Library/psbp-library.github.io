package psbp.external.implementation.readingWithWritingActive

import psbp.external.specification.program.Program

import psbp.external.specification.program.reading.Readable

import psbp.external.specification.program.writing.Writable

import psbp.external.implementation.computation.givenProgramFromComputation

import psbp.external.implementation.writingActive.WritingActive

import psbp.external.implementation.writingActive.givenWritingActiveComputation

import psbp.internal.specification.computation.Computation

import psbp.internal.implementation.computation.transformation.readingTransformedComputation

private[psbp] given givenReadingWithWritingActiveComputation[
  R: Readable
  , W: Writable
]: Computation[ReadingWithWritingActive[R, W]] = 
  readingTransformedComputation[R, WritingActive[W]]

given givenReadingWithWritingActiveProgram[
  R: Readable
  , W: Writable
]: Program[`=>RWA`[R, W]] = 
  givenProgramFromComputation[ReadingWithWritingActive[R, W]]
