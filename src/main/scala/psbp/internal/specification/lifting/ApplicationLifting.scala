package psbp.internal.specification.lifting

import psbp.external.specification.types.&&

private[psbp] trait ApplicationLifting[C[+ _]]:

  // declared

  private[psbp] def liftApply[Z, Y]: (C[Z => Y] && C[Z]) => C[Y]