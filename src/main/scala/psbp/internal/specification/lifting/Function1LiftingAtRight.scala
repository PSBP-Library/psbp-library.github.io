package psbp.internal.specification.lifting

private[psbp] trait Function1LiftingAtRight[A[+ _, + _]]:

  private[psbp] def rightFunction1Lifting[Y]: 
    Function1Lifting[[X] =>> A[Y, X]]   
