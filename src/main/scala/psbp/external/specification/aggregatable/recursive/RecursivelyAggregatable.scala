package psbp.external.specification.aggregatable.recursive

import psbp.external.specification.types.&&

import psbp.external.specification.recursion.Recursion 

import psbp.external.specification.aggregatable.Aggregatable

type Recursive[R[+_[+ _]]] = [A[+ _, + _]] =>> [Z] =>> R[[X] =>> A[Z, X]]

trait RecursivelyAggregatable[
  A[+ _, + _]
  , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]
] extends RecursiveFolderType[A, >-->]
  with RecursiveUnfolderType[A, >-->]
  with Aggregatable[Recursive[R][A], >-->]



