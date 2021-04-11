package psbp.external.implementation.freeActive

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  givenActiveResulting
  , givenActiveBinding
  , givenActiveMaterialization 
}

import psbp.internal.specification.computation.givenComputationFromResultingAndBinding

import psbp.internal.implementation.materialization.freeTransformedMaterialization

given givenFreeActiveMaterialization: Materialization[`=>FA`, Unit, Unit] =
  freeTransformedMaterialization[Active, Unit, Unit]