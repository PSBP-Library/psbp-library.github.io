package psbp.internal.specification.computation

private[psbp] trait Function1Lifting[C[+ _]]:

  // declared

  private[psbp] def lift1[Z, Y]: (Z => Y) => C[Z] => C[Y]
