package psbp.external.implementation.stateActive

import psbp.external.specification.types.&&

import psbp.external.specification.program.state.Initial

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  givenActiveComputation
  , givenActiveMaterialization 
}

import psbp.internal.implementation.materialization.stateTransformedMaterialization

given givenStateActiveMaterialization[S: Initial]: Materialization[`=>SA`[S], Unit, Unit] =
  stateTransformedMaterialization[S, Active, Unit, Unit]

