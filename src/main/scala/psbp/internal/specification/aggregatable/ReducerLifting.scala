// package psbp.internal.specification.aggregatable

// import psbp.external.specifcation.aggregatable.ReducerType

// private[psbp] trait ReducerLifting[C[+ _]] extends ReducerType:
    
//   // declared

//   private[psbp] def liftReducer[Y, X]: Reducer[Y, X] => Reducer[C[Y], C[X]]