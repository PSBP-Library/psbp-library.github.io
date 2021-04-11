package psbp.external.implementation.reactive

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  givenActiveResulting
  , givenActiveBinding
  , givenActiveMaterialization
  , givenActiveCoResulting
}

import psbp.internal.specification.computation.givenComputationFromResultingAndBinding

import psbp.internal.implementation.materialization.reactiveTransformedMaterialization

given givenReactiveMaterialization: Materialization[`=>R`, Unit, Unit] =
  reactiveTransformedMaterialization[Active, Unit, Unit]
