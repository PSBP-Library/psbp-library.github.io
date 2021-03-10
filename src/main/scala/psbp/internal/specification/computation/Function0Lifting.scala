package psbp.internal.specification.computation

private[psbp] trait Function0Lifting[C[+ _]]:

  // declared

  private[psbp] def lift0[Z]: Z => C[Z]
