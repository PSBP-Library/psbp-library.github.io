package psbp.internal.specification.lifting

import psbp.external.specifcation.types.&&

private[psbp] trait Function2Lifting[C[+ _]]:

  // declared

  private[psbp] def lift2[Z, Y, X]: ((Z && Y) => X) => (C[Z] && C[Y]) => C[X]

