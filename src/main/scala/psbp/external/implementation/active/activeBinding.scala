package psbp.implementation.active

import psbp.internal.specification.computation.Binding

given activeBinding: Binding[Active] with

  private[psbp] def bind[Z, Y] (cz: Active[Z], `z=>cy`: => Z => Active[Y]): Active[Y] =
    `z=>cy`(cz)