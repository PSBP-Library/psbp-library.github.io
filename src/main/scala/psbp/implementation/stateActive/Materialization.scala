package psbp.implementation.stateActive

import psbp.specification.program.&&

import psbp.specification.state.Initial

import psbp.specification.materialization.Materialization

import psbp.internalSpecification.materialization.stateTransformedMaterialization

import psbp.implementation.active.Active

import psbp.implementation.active.given

given stateActiveMaterialization[S: Initial]: Materialization[`=>SA`[S], Unit, Unit] =
  stateTransformedMaterialization[S, Active, Unit, Unit]

