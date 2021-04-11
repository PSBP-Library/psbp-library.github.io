package psbp.external.implementation.readingWithWritingActive

import psbp.external.specification.program.Program

import psbp.external.specification.program.writing.Writable

import psbp.external.implementation.computation.givenProgramFromComputation

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  givenActiveResulting
  , givenActiveBinding
}

// import psbp.external.implementation.writingActive.WritingActive

// import psbp.external.implementation.writingActive.{ 
//   givenWritingActiveTransformation
//   // givenWritingActiveBinding
// }

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.computation.transformation.Transformation

import psbp.internal.specification.computation.givenComputationFromResultingAndBinding

import psbp.internal.specification.computation.transformation.givenResultingFromTransformation

import psbp.internal.implementation.computation.transformation.readingTransformedTransformation

import psbp.internal.implementation.computation.transformation.readingTransformedBinding

// given givenReadingWithWritingTransformation[R, W: Writable]: Transformation[Active, ReadingWithWritingActive[R, W]] = 

//   import psbp.external.implementation.writingActive.givenWritingActiveBinding
  
//   readingTransformedTransformation[R, WritingActive[W]]

// given givenReadingWithWritingActiveBinding[R, W: Writable]: Binding[ReadingWithWritingActive[R, W]] = 
  
//   readingTransformedBinding[R, WritingActive[W]]

// given givenReadingWithWritingActiveProgram[R, W]: Program[`=>RA`[R]] = givenProgramFromComputation[ReadingWithWritingActive[R]]


val foo = ???