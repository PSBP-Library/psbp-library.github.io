package psbp.external.implementation.readingActive

import psbp.external.specification.program.Program

import psbp.external.implementation.computation.givenProgramFromComputation

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  givenActiveResulting
  , givenActiveBinding
}

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.computation.transformation.Transformation

import psbp.internal.specification.computation.givenComputationFromResultingAndBinding

import psbp.internal.specification.computation.transformation.givenResultingFromTransformation

import psbp.internal.implementation.computation.transformation.readingTransformedTransformation

import psbp.internal.implementation.computation.transformation.readingTransformedBinding

given givenReadingTransformation[R]: Transformation[Active, ReadingActive[R]] = readingTransformedTransformation[R, Active]

given givenReadingActiveBinding[R]: Binding[ReadingActive[R]] = readingTransformedBinding[R, Active]

given givenReadingActiveProgram[R]: Program[`=>RA`[R]] = givenProgramFromComputation[ReadingActive[R]]
