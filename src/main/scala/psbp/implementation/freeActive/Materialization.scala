package psbp.implementation.freeActive

import psbp.specification.materialization.Materialization

import psbp.implementation.active.Active

import psbp.implementation.active.given

import psbp.internalSpecification.materialization.given // freeTransformedMaterialization

given Materialization[`=>FA`, Unit, Unit] = freeTransformedMaterialization[Active, Unit, Unit]