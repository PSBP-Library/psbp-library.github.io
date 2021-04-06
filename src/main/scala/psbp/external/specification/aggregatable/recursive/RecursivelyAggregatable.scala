package psbp.external.specification.aggregatable.recursive

import psbp.external.specification.types.&&

import psbp.external.specification.recursion.Recursion 

import psbp.external.specification.aggregatable.Aggregatable

type Recursive[R[+_[+ _]]] = [A[+ _, + _]] =>> [Z] =>> R[[X] =>> A[Z, X]]

trait RecursivelyAggregatable[
  A[+ _, + _]
  , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]
] extends RecursiveReducerType[A, >-->]
  with Aggregatable[Recursive[R][A], >-->]

// type RecursivelyAggregatable[
//   A[+ _, + _]
//   , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
//   , >-->[- _, + _]
// ] = RecursiveReducerType[A, >-->] && Aggregatable[Recursive[R][A], >-->]

// import psbp.external.specification.program.Classification

// given recursivelyAggregatableFormRecursiveReducerTypeAndAggregatable[
//   A[+ _, + _]: [A[+ _, + _]] =>> RecursiveReducerType[A, >-->] & Aggregatable[Recursive[R][A], >-->]
//   , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
//   , >-->[- _, + _]: Classification
// ]: RecursivelyAggregatable[A, R, >-->] with {

//   val recursivelyAggregatable: RecursiveReducerType[A, >-->] & Aggregatable[Recursive[R][A], >-->] = 
//     summon[RecursiveReducerType[A, >-->] & Aggregatable[Recursive[R][A], >-->]]

//   export recursivelyAggregatable.fuse
//   export recursivelyAggregatable.initialReducer
//   export recursivelyAggregatable.initialTraverser

// }

// given recursivelyAggregatableFormRecursiveReducerTypeAndAggregatable[
//   A[+ _, + _]: [A[+ _, + _]] =>> RecursivelyAggregatable[A, R, >-->]
//   , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
//   , >-->[- _, + _]: Classification
// ]: RecursivelyAggregatable[A, R, >-->] with {

//   val recursivelyAggregatable: RecursivelyAggregatable[A, R, >-->] = summon[RecursivelyAggregatable[A, R, >-->]]

//   export recursivelyAggregatable.fuse
//   export recursivelyAggregatable.initialReducer
//   export recursivelyAggregatable.initialTraverser

// }


