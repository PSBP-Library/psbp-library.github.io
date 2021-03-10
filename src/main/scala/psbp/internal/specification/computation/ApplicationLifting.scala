package psbp.internal.specification.computation

import psbp.specification.types.&&

private[psbp] trait ApplicationLifting[C[+ _]]:

  // declared

  private[psbp] def liftApply[Z, Y]: (C[Z => Y] && C[Z]) => C[Y]