package psbp.external.implementation.readingActive

import psbp.external.specification.types.&&

import psbp.external.specification.program.reading.Readable

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  givenActiveComputation
  , givenActiveMaterialization 
}

import psbp.internal.implementation.materialization.readingTransformedMaterialization

// given givenReadingActiveMaterialization[R: Readable]: Materialization[`=>RA`[R], Unit, R ?=> Unit] =
given givenReadingActiveMaterialization[R: Readable]: Materialization[`=>RA`[R], Unit, Unit] =
    readingTransformedMaterialization[R, Active, Unit, Unit]

