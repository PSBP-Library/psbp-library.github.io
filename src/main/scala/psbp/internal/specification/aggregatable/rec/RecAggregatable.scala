
package psbp.internal.specification.aggregatable.rec

import psbp.external.specifcation.types.&&

import psbp.external.specifcation.types.{ Rec, RightRec }

import psbp.external.specifcation.aggregatable.rec.RecReducerType

import psbp.external.specifcation.aggregatable.{ InitialTraverser, InitialReducer, Aggregatable }

import psbp.internal.specification.lifting.{ Function0Lifting, Function1Lifting, Function1LiftingAtRight , Function1LiftingAtLeft }

import psbp.internal.specification.lifting.function1LiftingFromFunction1LiftingAtRight

import psbp.internal.specification.lifting.function1LiftingFromFunction1LiftingAtLeft

import psbp.internal.specification.aggregatable.{ FunctionLevelFusing, FunctionLevelReducible }

import psbp.internal.specification.rec.{ rec }

private[psbp] trait RecInitialTraverser[C[+ _]: Function0Lifting] 
  extends InitialTraverser[[Z, Y] =>> Z => C[Y]]:

  private val function0Lifting = summon[Function0Lifting[C]]
  import function0Lifting.lift0

  override private[psbp] def initialTraverser[Y]: Y => C[Y] = lift0

private[psbp] trait RecInitialReducer[A[+ _, + _]: Function1LiftingAtRight] 
  extends RecReducerType[A]
  // with InitialReducer[[Y] =>> Rec[[X] =>> A[Y, X]]]:
  with InitialReducer[RightRec[A]]:

  // override private[psbp] def initialReducer[Y]: Reducer[Y, Rec[Left[A][Y]]] = 
  override private[psbp] def initialReducer[Y]: Reducer[Y, RightRec[A][Y]] = 
    Rec(_)

private[psbp] trait RecFunctionLevelFusing[A[+ _, + _]: Function1LiftingAtLeft] 
  extends RecReducerType[A]
  with FunctionLevelFusing:

  override private[psbp] def functionLevelFuse[Z, Y, X]: ((Z => Y) && Reducer[Y, X]) => Reducer[Z, X] =
    case (z2y, reducer) =>
      val foo: A[Z, X] => A[Y, X] = function1LiftingFromFunction1LiftingAtLeft.lift1(z2y)
      function1LiftingFromFunction1LiftingAtLeft.lift1(z2y) andThen reducer 

// private[psbp] type `=>`[C[+ _]] = [Z, Y] =>> Z => C[Y]

private[psbp] trait RecAggregatable[A[+ _, + _]: Function1LiftingAtLeft: Function1LiftingAtRight, C[+ _]: Function0Lifting: Function1Lifting]
  extends RecInitialTraverser[C]
  with RecInitialReducer[A]
  with RecFunctionLevelFusing[A]
  with FunctionLevelReducible[RightRec[A], C]
  with Aggregatable[RightRec[A], [Z, Y] =>> Z => C[Y]]:
  
  override private[psbp] def functionLevelReduce[Y, X]: Reducer[Y, X] => RightRec[A][Y] => X = 
    rec
