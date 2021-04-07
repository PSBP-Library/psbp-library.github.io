package psbp.external.specification.structure.recursive

import psbp.external.specification.types.&&

import psbp.external.specification.recursion.Recursion 

import psbp.external.specification.structure.Structure

type Recursive[R[+_[+ _]]] = [A[+ _, + _]] =>> [Z] =>> R[[X] =>> A[Z, X]]

trait RecursiveStructure[
  A[+ _, + _]
  , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]
] extends RecursiveFolderType[A, >-->]
  with RecursiveUnfolderType[A, >-->]
  with Structure[Recursive[R][A], >-->]



