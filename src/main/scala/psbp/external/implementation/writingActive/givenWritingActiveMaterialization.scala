package psbp.external.implementation.writingActive

import psbp.external.specification.program.writing.Writable

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  givenActiveComputation
  , givenActiveMaterialization 
}

import psbp.internal.implementation.materialization.writingTransformedMaterialization

given givenWritingActiveMaterialization[W: Writable]: Materialization[`=>WA`[W], Unit, (W, Unit)] =
  writingTransformedMaterialization[W, Active, Unit, Unit]

