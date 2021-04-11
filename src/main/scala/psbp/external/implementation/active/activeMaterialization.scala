package psbp.external.implementation.active

import psbp.external.specification.materialization.Materialization

given givenActiveMaterialization: Materialization[`=>A`, Unit, Unit] with
  val materialize: (Unit `=>A` Unit) => Unit ?=> Unit =
    `u>-->u` =>
      val u: Unit = summon[Unit]
      `u>-->u`(u)
