package psbp.internal.specification.lifting

private[psbp] trait Function0Lifting[C[+ _]]:

  // declared

  private[psbp] def lift0[Z]: Z => C[Z]
