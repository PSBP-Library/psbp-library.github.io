package psbp.external.implementation.readingActive

import psbp.external.specification.types.&&

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  givenActiveComputation
  , givenActiveMaterialization 
}

import psbp.internal.implementation.materialization.readingTransformedMaterialization

given givenReadingActiveMaterialization[R]: Materialization[`=>RA`[R], Unit, R ?=> Unit] =
  readingTransformedMaterialization[R, Active, Unit, Unit]

