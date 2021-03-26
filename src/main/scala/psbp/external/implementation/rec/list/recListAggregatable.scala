package psbp.implementation.rec.list

import psbp.external.specifcation.function.foldSum

import psbp.external.specifcation.aggregatable.rec.RecAggregatable

import psbp.internal.specification.aggregatable.rec.{ RecAggregatable => InternalRecAggregatable }

import psbp.internal.implementation.aggregatable.rec.{ 

  sumFunction1LiftingAtLeft, 
  noneFunction1LiftingAtLeft, 
  consFunction1LiftingAtLeft, 
  sumFunction1LiftingAtRight, 
  noneFunction1LiftingAtRight, 
  consFunction1LiftingAtRight,  
  sumRecAggregatable, 
  noneRecAggregatable, 
  consRecAggregatable 
  }

import psbp.implementation.rec.list.{ List, RecList }

// trait RecListAggregatable[>-->[- _, + _]] extends RecAggregatable[List, >-->]
  // override private[psbp] type Structure[Y, X] = ListStructure[Y, X]

import psbp.internal.specification.lifting.{ Function0Lifting, Function1Lifting, FunctionLifting, Function1LiftingAtRight , Function1LiftingAtLeft }

given recListAggregatable[
  C[+ _]: Function0Lifting: Function1Lifting: FunctionLifting: [C[+ _]] =>> InternalRecAggregatable[List, C]
  // ]: RecListAggregatable[[Z, Y] =>> Z => C[Y]] with
  ]: RecAggregatable[List, [Z, Y] =>> Z => C[Y]] with

  private val internalRecAggregatable: InternalRecAggregatable[List, C] = 
    summon[InternalRecAggregatable[List, C]]

  // //override private[psbp] type Structure[Y, X] = ListStructure[Y, X]
  // override type Structure[Y, X] = ListStructure[Y, X]

  // def structureToReducer[Y, X]: Structure[Y, X] => Reducer[Y, X] =
  //   sumRecAggregatable.structureToReducer    

  export internalRecAggregatable.initialReducer
  export internalRecAggregatable.initialTraverser
  export internalRecAggregatable.aggregate  
