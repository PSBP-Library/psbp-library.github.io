// package psbp.internal.specification.structure

// import psbp.external.specifcation.types.&&

// import psbp.external.specifcation.structure.Structure

// private[psbp] trait FunctionLevelReducible[A[+ _], C[+ _]] 
//   extends FunctionLevelFusing
//   with ReducerLifting[C]
//   with Structure[A, [Z, Y] =>> Z => C[Y]]:

//   private type `=>C` = [Z, Y] =>> Z => C[Y]    

//   // declared
  
//   private[psbp] def functionLevelReduce[Y, X]: Reducer[Y, X] => A[Y] => X

//   // defined

//   def aggregate[Z, Y, X]: ((Z `=>C` Y) && Reducer[Y, X])  => A[Z] `=>C` X =
//     (`z=>cy`, reducer) =>
//       functionLevelReduce(functionLevelFuse(`z=>cy`, liftReducer(reducer)))

