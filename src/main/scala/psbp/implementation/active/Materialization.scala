package psbp.implementation.active

import psbp.specification.materialization.Materialization

given Materialization[`=>A`, Unit, Unit] with

  val materialize: (Unit `=>A` Unit) => Unit => Unit =
    identity
