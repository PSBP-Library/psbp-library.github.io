package psbp.external.implementation.freeActive

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  activeResulting
  , activeBinding
  , activeMaterialization 
}

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.implementation.materialization.freeTransformedMaterialization

given freeActiveMaterialization: Materialization[`=>FA`, Unit, Unit] =
  freeTransformedMaterialization[Active, Unit, Unit]