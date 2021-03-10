package psbp.internal.specification.computation

private[psbp] trait ValueLifting[C[+ _]]:

  // declared

  private[psbp] def liftValue[Z]: Z => C[Z]