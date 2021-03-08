package psbp.implementation.reactive

import psbp.specification.materialization.Materialization

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.implementation.computation.transformation.reactiveTransformedMaterialization

import psbp.implementation.active.Active

import psbp.implementation.active.{ activeResulting, activeBinding, activeMaterialization, activeCoResulting }

given reactiveMaterialization: Materialization[`=>R`, Unit, Unit] =
  reactiveTransformedMaterialization[Active, Unit, Unit]
