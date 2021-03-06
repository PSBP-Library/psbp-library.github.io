package psbp.implementation.stateActive

import psbp.specification.types.&&

import psbp.specification.program.state.Initial

import psbp.specification.materialization.Materialization

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.implementation.materialization.stateTransformedMaterialization

import psbp.implementation.active.Active

import psbp.implementation.active.{ activeResulting, activeBinding, activeMaterialization }

given stateActiveMaterialization[S: Initial]: Materialization[`=>SA`[S], Unit, Unit] =
  stateTransformedMaterialization[S, Active, Unit, Unit]

