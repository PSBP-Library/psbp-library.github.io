package psbp.internal.specification.lifting

import psbp.external.specifcation.types.&&

private[psbp] trait Function3Lifting[C[+ _]]:

  // declared

  private[psbp] def lift3[Z, Y, X, W]: ((Z && Y && X) => W) => (C[Z] && C[Y] && C[X]) => C[W] 