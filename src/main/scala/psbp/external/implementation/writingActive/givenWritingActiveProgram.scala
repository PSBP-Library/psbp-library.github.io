package psbp.external.implementation.writingActive

import psbp.external.specification.program.Program

import psbp.external.specification.program.writing.Writable

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

import psbp.internal.implementation.computation.transformation.writingTransformedTransformation

import psbp.internal.implementation.computation.transformation.writingTransformedBinding

given givenWritingActiveTransformation[W: Writable]: Transformation[Active, WritingActive[W]] = writingTransformedTransformation[W, Active]

given givenWritingActiveBinding[W: Writable]: Binding[WritingActive[W]] = writingTransformedBinding[W, Active]

given givenWritingActiveProgram[W: Writable]: Program[`=>WA`[W]] = givenProgramFromComputation[WritingActive[W]]
