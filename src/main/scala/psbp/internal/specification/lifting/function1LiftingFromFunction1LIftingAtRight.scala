package psbp.internal.specification.lifting

private[psbp] given function1LiftingFromFunction1LiftingAtRight[X, A[+ _, + _]: Function1LiftingAtRight]: Function1Lifting[[Z] =>> A[X, Z]] with

  private val function1LiftingAtRight: Function1LiftingAtRight[A] = summon[Function1LiftingAtRight[A]]
  import function1LiftingAtRight.rightFunction1Lifting
  
  private[psbp] def lift1[Z, Y]: (Z => Y) => A[X, Z] => A[X, Y] =
    rightFunction1Lifting.lift1 