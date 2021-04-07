package psbp.external.implementation.stateActive

import psbp.external.specification.types.&&

import psbp.external.specification.program.state.Initial

import psbp.external.specification.materialization.Materialization

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.implementation.materialization.stateTransformedMaterialization

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ activeResulting, activeBinding, activeMaterialization }

given stateActiveMaterialization[S: Initial]: Materialization[`=>SA`[S], Unit, Unit] =
  stateTransformedMaterialization[S, Active, Unit, Unit]

