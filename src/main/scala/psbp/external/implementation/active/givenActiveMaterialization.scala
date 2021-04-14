package psbp.external.implementation.active

import psbp.external.specification.materialization.Materialization

given givenActiveMaterialization: Materialization[`=>A`, Unit, Unit] with
  val materialize: (Unit `=>A` Unit) => Unit ?=> Unit =
    `u>-->u` =>
      // println(s">>> in materialize of givenActiveMaterialization")
      val argument: Unit = summon[Unit]
      // println(s">>> given $argument in materialize of givenActiveMaterialization")
      val result = `u>-->u`(argument)
      // println(s">>> $result in materialize of givenActiveMaterialization")
      result
