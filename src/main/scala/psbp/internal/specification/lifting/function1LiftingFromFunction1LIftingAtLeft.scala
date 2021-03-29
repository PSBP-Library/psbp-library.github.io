package psbp.internal.specification.lifting

private[psbp] given function1LiftingFromFunction1LiftingAtLeft[X, A[+ _, + _]: Function1LiftingAtLeft]: Function1Lifting[[Z] =>> A[Z, X]] with

  private val function1LiftingAtLeft: Function1LiftingAtLeft[A] = summon[Function1LiftingAtLeft[A]]
  import function1LiftingAtLeft.leftFunction1Lifting
  
  private[psbp] def lift1[Z, Y]: (Z => Y) => A[Z, X] => A[Y, X] =
    leftFunction1Lifting.lift1