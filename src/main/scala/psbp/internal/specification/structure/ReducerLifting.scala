// package psbp.internal.specification.structure

// import psbp.external.specifcation.structure.ReducerType

// private[psbp] trait ReducerLifting[C[+ _]] extends ReducerType:
    
//   // declared

//   private[psbp] def liftReducer[Y, X]: Reducer[Y, X] => Reducer[C[Y], C[X]]