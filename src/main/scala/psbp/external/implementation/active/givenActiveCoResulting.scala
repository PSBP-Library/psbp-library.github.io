package psbp.external.implementation.active

import psbp.internal.specification.computation.CoResulting

given givenActiveCoResulting: CoResulting[Active] with
  override def coResult[Z]: Active[Z] => Z =
    identity
