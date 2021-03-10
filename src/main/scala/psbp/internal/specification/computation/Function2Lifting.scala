package psbp.internal.specification.computation

import psbp.specification.types.&&

private[psbp] trait Function2Lifting[C[+ _]]:

  // declared

  private[psbp] def lift2[Z, Y, X]: ((Z && Y) => X) => (C[Z] && C[Y]) => C[X]

