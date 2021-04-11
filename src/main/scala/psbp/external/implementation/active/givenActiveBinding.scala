package psbp.external.implementation.active

import psbp.internal.specification.computation.Binding

given givenActiveBinding: Binding[Active] with

  private[psbp] def bind[Z, Y](
    cz: Active[Z]
    , `z=>cy`: => Z => Active[Y]
  ): Active[Y] =
    `z=>cy`(cz)