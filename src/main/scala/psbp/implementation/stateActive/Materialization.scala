package psbp.implementation.stateActive

import psbp.specification.program.&&

import psbp.specification.state.Initial

import psbp.specification.materialization.Materialization

import psbp.internalImplementation.materialization.stateTransformedMaterialization

import psbp.implementation.active.Active

import psbp.implementation.active.{ activeComputation, activeMaterialization }

given stateActiveMaterialization[S: Initial]: Materialization[`=>SA`[S], Unit, Unit] =
  stateTransformedMaterialization[S, Active, Unit, Unit]

