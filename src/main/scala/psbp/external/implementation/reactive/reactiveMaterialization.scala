package psbp.external.implementation.reactive

import psbp.external.specification.materialization.Materialization

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.implementation.computation.transformation.reactiveTransformedMaterialization

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ activeResulting, activeBinding, activeMaterialization, activeCoResulting }

given reactiveMaterialization: Materialization[`=>R`, Unit, Unit] =
  reactiveTransformedMaterialization[Active, Unit, Unit]
