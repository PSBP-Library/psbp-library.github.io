package psbp.external.implementation.writingActive

import psbp.external.specification.program.Program

import psbp.external.implementation.active.Active

import psbp.external.specification.program.writing.Writable

import psbp.external.implementation.active.{ 
  givenActiveComputation
}

import psbp.internal.specification.computation.Computation

import psbp.external.implementation.computation.givenProgramFromComputation

import psbp.internal.implementation.computation.transformation.writingTransformedComputation

given givenWritingActiveComputation[W: Writable]: Computation[WritingActive[W]] = writingTransformedComputation[W, Active]

given givenWritingActiveProgram[W: Writable]: Program[`=>WA`[W]] = givenProgramFromComputation[WritingActive[W]]
