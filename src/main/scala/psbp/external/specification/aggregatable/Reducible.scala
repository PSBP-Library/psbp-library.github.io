package psbp.external.specifcation.aggregatable

trait Reducible[A[+ _], >-->[- _, + _]] extends InitialReducer[A]:

  // declared
  
  def reduce[Y, X]: Reducer[Y, X] => A[Y] >--> X

