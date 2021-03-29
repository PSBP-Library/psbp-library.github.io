package psbp.external.specification.aggregatable

private[psbp] trait InitialReducer[A[+ _]] extends ReducerType:

  // declared

  private[psbp] def initialReducer[Y]: Reducer[Y, A[Y]]