package psbp.internal.implementation.aggregatable.rec

import scala.collection.immutable

import psbp.external.specifcation.types.&&

import psbp.external.specifcation.function.{ foldSum, `z=>(z||y)`, `y=>(z||y)` }

// import psbp.external.specifcation.aggregatable.Aggregatable

import psbp.external.specifcation.types.Rec

import psbp.internal.specification.rec.{ rec }

import psbp.internal.specification.lifting.{ Function1LiftingAtRight , Function1LiftingAtLeft, FunctionLifting }

// import psbp.internal.specification.aggregatable.rec.RecReducerType

import psbp.internal.specification.aggregatable.rec.{ RecReducerLifting, RecAggregatable, RecInitialTraverser, RecInitialReducer, RecFunctionLevelFusing }

private[psbp] given consFunction1LiftingAtLeft: Function1LiftingAtLeft[Cons] = productFunction1LiftingAtLeft[Constant, Identity]

private[psbp] given consFunction1LiftingAtRight: Function1LiftingAtRight[Cons] = productFunction1LiftingAtRight[Constant, Identity]

// import psbp.external.specifcation.aggregatable.rec.RecStructureToRecReducer

private[psbp] given consRecAggregatable[C[+ _]: FunctionLifting]: RecAggregatable[Cons, C] 
  // with RecStructureToRecReducer[Cons]
  with RecReducerLifting[Cons, C]
  with RecInitialTraverser[C] 
  with RecInitialReducer[Cons]
  with RecFunctionLevelFusing[Cons] with

  // // override private[psbp] type Structure[Y, X] = ConsStructure[Y, X]
  // override type Structure[Y, X] = Reducer[Y, X] // ConsStructure[Y, X]

  // override def structureToReducer[Y, X]: Structure[Y, X] => Reducer[Y, X] =
  //   identity

  override private[psbp] def swap[Y, X]: Cons[C[Y], C[X]] => C[Cons[Y, X]] =
    productRecAggregatable[Constant, Identity, C].swap


