package psbp.external.specification.aggregatable

// import psbp.external.specification.program.Program

private[psbp] trait Reducible[A[+ _], >-->[- _, + _]] extends ReducerType: // extends InitialReducer[A]:

  // declared
  
  private[psbp] def reduce[Y, X]: Reducer[Y, X] => A[Y] >--> X

