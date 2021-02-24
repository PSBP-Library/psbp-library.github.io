package psbp.internalSpecification.computation

private[psbp] trait CoResulting[C[+ _]]:

  // declared

  private[psbp] def coResult[Z]: C[Z] => Z
