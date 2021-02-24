package psbp.implementation.active

import psbp.internalSpecification.computation.CoResulting

given CoResulting[Active] with
  override def coResult[Z]: Active[Z] => Z =
    identity
