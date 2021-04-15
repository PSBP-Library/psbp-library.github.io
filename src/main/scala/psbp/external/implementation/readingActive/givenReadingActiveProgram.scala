package psbp.external.implementation.readingActive

import psbp.external.specification.program.Program

import psbp.external.specification.program.reading.Readable

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  givenActiveComputation
}

import psbp.internal.specification.computation.Computation

import psbp.external.implementation.computation.givenProgramFromComputation

import psbp.internal.implementation.computation.transformation.readingTransformedComputation

given givenReadingActiveComputation[R: Readable]: Computation[ReadingActive[R]] = 
  readingTransformedComputation[R, Active]

given givenReadingActiveProgram[R: Readable]: Program[`=>RA`[R]] =
  givenProgramFromComputation[ReadingActive[R]]
