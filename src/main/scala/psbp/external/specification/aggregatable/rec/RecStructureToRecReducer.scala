package psbp.external.specifcation.aggregatable.rec

private[psbp] trait RecStructureToRecReducer[A[+ _, + _]] extends RecReducerType[A] with RecStructureType:

  def structureToReducer[Y, X]: Structure[Y, X] => Reducer[Y, X] 



