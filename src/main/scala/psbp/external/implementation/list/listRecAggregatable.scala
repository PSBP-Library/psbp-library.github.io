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

import psbp.internal.specification.aggregatable.{ BiTraversable }

import psbp.internal.implementation.aggregatable.rec.{ 
  Product, Constant, Identity, Sum, None, 
  noneBiTraversable, 
  constantBiTraversable, 
  identityBiTraversable, 
  productBiTraversable, 
  sumBiTraversable 
  }

private[psbp] given consBiTraversable[>-->[- _, + _]: Program]: BiTraversable[Cons, >-->] = productBiTraversable[Identity, Constant, >-->]

private[psbp] given listBiTraversable[>-->[- _, + _]: Program]: BiTraversable[List, >-->] = sumBiTraversable[None, Cons, >-->]

import psbp.external.specification.aggregatable.recursive.{ RecursiveAggregatable }

import psbp.internal.specification.aggregatable.recursive.{ recursiveAggregatableFromBiTraversable }

// import psbp.external.specification.aggregatable.rec.RecReducerType

import psbp.external.specification.recursive.{ Recursive }

given listRecursiveAggregatable[
  R[+_[+ _]]: [R[+_[+ _]]] =>> Recursive[R, >-->], 
  >-->[- _, + _]: Program]: RecursiveAggregatable[List, R, >-->] = 
    recursiveAggregatableFromBiTraversable[List, R, >-->]