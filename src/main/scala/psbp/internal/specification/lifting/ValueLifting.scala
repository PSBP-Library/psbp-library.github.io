package psbp.internal.specification.lifting

private[psbp] trait ValueLifting[C[+ _]]:

  // declared

  private[psbp] def liftValue[Z]: Z => C[Z]