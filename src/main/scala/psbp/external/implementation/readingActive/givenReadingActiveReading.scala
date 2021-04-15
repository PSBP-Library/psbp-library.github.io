package psbp.external.implementation.readingActive

import psbp.external.specification.program.reading.{
  Readable
  , Reading
}

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.givenActiveComputation

import psbp.internal.implementation.computation.transformation.readingTransformedReading

given givenReadingActiveReading[R: Readable]: Reading[R, `=>RA`[R]] = 
  readingTransformedReading[R, Active]

