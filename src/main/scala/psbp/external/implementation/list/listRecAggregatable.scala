package psbp.external.implementation.list

// import psbp.external.specifcation.function.foldSum

// import psbp.internal.specification.lifting.{ Function1LiftingAtLeft, FunctionLifting }

// import psbp.internal.specification.aggregatable.rec.{ RecAggregatable, RecReducerLifting, RecInitialTraverser, RecInitialReducer, RecFunctionLevelFusing }

// import psbp.internal.implementation.aggregatable.rec.{ None, Cons }

// import psbp.internal.implementation.aggregatable.rec.{ 
//   noneFunction1LiftingAtLeft, noneFunction1LiftingAtRight, noneRecAggregatable, 
//   consFunction1LiftingAtLeft, consFunction1LiftingAtRight, consRecAggregatable, 
//   sumFunction1LiftingAtLeft, sumRecAggregatable 
// }

// given listFunction1LiftingAtLeft: Function1LiftingAtLeft[List] = sumFunction1LiftingAtLeft[None, Cons]

// given listRecAggregatable[C[+ _]: FunctionLifting]: RecAggregatable[List, C] 
//   with RecReducerLifting[List, C]
//   with RecInitialTraverser[C] 
//   with RecInitialReducer[List] 
//   with RecFunctionLevelFusing[List] with

//   override private[psbp] def swap[Y, X]: List[C[Y], C[X]] => C[List[Y, X]] =
//     sumRecAggregatable[None, Cons, C].swap


// package psbp.internal.implementation.aggregatable.rec

import psbp.external.specification.program.Program

import psbp.internal.specification.aggregatable.{ TraversableAtLeft, TraversableAtRight }

import psbp.internal.implementation.aggregatable.rec.{ 
  Product, Constant, Identity, Sum, None, 
  noneTraversableAtLeft, noneTraversableAtRight, 
  constantTraversableAtLeft, constantTraversableAtRight, 
  identityTraversableAtLeft, identityTraversableAtRight, 
  productTraversableAtLeft, productTraversableAtRight, 
  sumTraversableAtLeft, sumTraversableAtRight 
  }

private[psbp] given consTraversableAtLeft[>-->[- _, + _]: Program]: TraversableAtLeft[Cons, >-->] = productTraversableAtLeft[Identity, Constant, >-->]

private[psbp] given consTraversableAtRight[>-->[- _, + _]: Program]: TraversableAtRight[Cons, >-->] = productTraversableAtRight[Identity, Constant, >-->]

private[psbp] given listTraversableAtLeft[>-->[- _, + _]: Program]: TraversableAtLeft[List, >-->] = sumTraversableAtLeft[None, Cons, >-->]

private[psbp] given listTraversableAtRight[>-->[- _, + _]: Program]: TraversableAtRight[List, >-->] = sumTraversableAtRight[None, Cons, >-->]

import psbp.external.specification.aggregatable.recursive.{ RecursiveAggregatable }

import psbp.internal.specification.aggregatable.recursive.{ recursiveAggregatableFromTraversableAtLeftAndTraversableAtRight }

// import psbp.external.specification.aggregatable.rec.RecReducerType

import psbp.external.specification.recursive.{ Recursive }

given listRecursiveAggregatable[
  R[+_[+ _]]: [R[+_[+ _]]] =>> Recursive[R, >-->], 
  >-->[- _, + _]: Program]: RecursiveAggregatable[List, R, >-->] = 
    recursiveAggregatableFromTraversableAtLeftAndTraversableAtRight[List, R, >-->]