// package psbp.implementation.rec.list

// import psbp.internal.specification.lifting.{ Function1LiftingAtRight, Function1Lifting }

// import psbp.internal.implementation.aggregatable.rec.{ None, Cons, noneFunction1LiftingAtRight, consFunction1LiftingAtRight, sumFunction1LiftingAtRight }

// private[psbp] given listFunction1LiftingAtRight: Function1LiftingAtRight[List] = sumFunction1LiftingAtRight[None, Cons]

// private[psbp] given listFunction1Lifting[Z]: Function1Lifting[[Y] =>> List[Z, Y]] = listFunction1LiftingAtRight.rightFunction1Lifting