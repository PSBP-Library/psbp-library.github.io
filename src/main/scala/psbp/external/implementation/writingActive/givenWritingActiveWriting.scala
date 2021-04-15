package psbp.external.implementation.writingActive

import psbp.external.specification.program.writing.{
  Writable
  , Writing
}

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.givenActiveComputation

import psbp.internal.implementation.computation.transformation.writingTransformedWriting

given givenWritingActiveWriting[W: Writable]: Writing[W, `=>WA`[W]] = 
  writingTransformedWriting[W, Active]
