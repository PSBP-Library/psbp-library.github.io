package psbp.implementation.freeActive

import psbp.specification.materialization.Materialization

import psbp.internalImplementation.materialization.freeTransformedMaterialization

import psbp.implementation.active.Active

import psbp.implementation.active.{ activeComputation, activeMaterialization }

given freeActiveMaterialization: Materialization[`=>FA`, Unit, Unit] =
  freeTransformedMaterialization[Active, Unit, Unit]