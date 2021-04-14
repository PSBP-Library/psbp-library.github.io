package psbp.external.implementation.readingActive

import psbp.external.specification.program.Program

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  givenActiveComputation
}

import psbp.internal.specification.computation.Computation

import psbp.external.implementation.computation.givenProgramFromComputation

import psbp.internal.implementation.computation.transformation.readingTransformedComputation

given givenReadingActiveComputation[R]: Computation[ReadingActive[R]] = readingTransformedComputation[R, Active]

given givenReadingActiveProgram[R]: Program[`=>RA`[R]] = givenProgramFromComputation[ReadingActive[R]]
