package psbp.internal.specification.aggregatable.rec

import psbp.external.specifcation.aggregatable.rec.RecReducerType

import psbp.internal.specification.lifting.Function1Lifting

import psbp.internal.specification.aggregatable.ReducerLifting

private[psbp] trait RecReducerLifting[A[+ _, + _], C[+ _]: Function1Lifting] extends RecReducerType[A] with ReducerLifting[C]:

  // declared

  private[psbp] def swap[Y, X]: A[C[Y], C[X]] => C[A[Y, X]]

  // defined

  private val function1Lifting = summon[Function1Lifting[C]]
  import function1Lifting.lift1
  
  override private[psbp] def liftReducer[Y, X]: Reducer[Y, X] => Reducer[C[Y], C[X]] =
    
    reducer =>
      swap andThen lift1(reducer)