package psbp.implementation.reactive

import psbp.specification.materialization.Materialization

import psbp.implementation.active.Active

import psbp.implementation.active.given 

import psbp.internalSpecification.materialization.given // reactiveTransformedMaterialization

given Materialization[`=>R`, Unit, Unit] = reactiveTransformedMaterialization[Active, Unit, Unit]

// given Materialization[`=>R`, Unit, Unit] with

//   val materialize: (Unit `=>R` Unit) => Unit => Unit =
//     `u>-->u` =>
//       u =>
//         `u>-->u`(u)(identity)