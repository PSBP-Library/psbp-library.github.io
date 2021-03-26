package psbp.implementation.rec.list

import psbp.external.specifcation.function.foldSum

import psbp.external.specifcation.aggregatable.rec.RecAggregatable

import psbp.internal.specification.aggregatable.rec.{ RecAggregatable => InternalRecAggregatable }

trait RecListAggregatable[>-->[- _, + _]] extends RecAggregatable[List, >-->]:

  override private[psbp] type Structure[Y, X] = ListStructure[Y, X]

  def structureToReducer[Y, X]: Structure[Y, X] => Reducer[Y, X] =
    foldSum

given recListAggregatable[
  C[+ _]: [C[+ _]] =>> InternalRecAggregatable[List, C]
  ]: RecListAggregatable[[Z, Y] =>> Z => C[Y]] with

  private val internalRecAggregatable: InternalRecAggregatable[List, C] = 
    summon[InternalRecAggregatable[List, C]]

  export internalRecAggregatable.initialReducer
  export internalRecAggregatable.initialTraverser
  export internalRecAggregatable.aggregate  
