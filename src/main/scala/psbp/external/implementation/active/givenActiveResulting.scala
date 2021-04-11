package psbp.external.implementation.active

import psbp.internal.specification.computation.Resulting

given givenActiveResulting: Resulting[Active] with

  private[psbp] def result[Z]: Z => Active[Z] =
    z =>
      z