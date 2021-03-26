package psbp.implementation.rec.list

import psbp.external.specifcation.function.foldSum

import psbp.internal.specification.lifting.{ Function1LiftingAtLeft, FunctionLifting }

import psbp.internal.specification.aggregatable.rec.{ RecAggregatable, RecReducerLifting, RecInitialTraverser, RecInitialReducer, RecFunctionLevelFusing }

import psbp.internal.implementation.aggregatable.rec.{ None, Cons }

import psbp.internal.implementation.aggregatable.rec.{ 
  noneFunction1LiftingAtLeft, noneFunction1LiftingAtRight, noneRecAggregatable, 
  consFunction1LiftingAtLeft, consFunction1LiftingAtRight, consRecAggregatable, 
  sumFunction1LiftingAtLeft, sumRecAggregatable 
}

given listFunction1LiftingAtLeft: Function1LiftingAtLeft[List] = sumFunction1LiftingAtLeft[None, Cons]

given listRecAggregatable[C[+ _]: FunctionLifting]: RecAggregatable[List, C] 
  with RecReducerLifting[List, C]
  with RecInitialTraverser[C] 
  with RecInitialReducer[List] 
  with RecFunctionLevelFusing[List] with

  override private[psbp] def swap[Y, X]: List[C[Y], C[X]] => C[List[Y, X]] =
    sumRecAggregatable[None, Cons, C].swap
