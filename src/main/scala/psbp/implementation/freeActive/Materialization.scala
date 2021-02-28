package psbp.implementation.freeActive

import psbp.specification.materialization.Materialization

import psbp.internalSpecification.materialization.freeTransformedMaterialization

import psbp.implementation.active.Active

import psbp.implementation.active.given

given freeMaterialization: Materialization[`=>FA`, Unit, Unit] = freeTransformedMaterialization[Active, Unit, Unit]