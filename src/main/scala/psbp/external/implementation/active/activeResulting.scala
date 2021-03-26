package psbp.implementation.active

import psbp.internal.specification.computation.Resulting

given activeResulting: Resulting[Active] with

  private[psbp] def result[Z]: Z => Active[Z] =
    z =>
      z