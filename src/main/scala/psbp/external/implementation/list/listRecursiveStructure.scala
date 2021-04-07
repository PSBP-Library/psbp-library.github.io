package psbp.external.implementation.list

import psbp.external.specification.program.Program

import psbp.internal.specification.structure.{ BiTraversable }

import psbp.internal.implementation.structure.recursive.{ 
  Sum, None, Product, Constant, Identity,  
  sumBiTraversable, 
  noneBiTraversable, 
  productBiTraversable, 
  constantBiTraversable, 
  identityBiTraversable
  }

private[psbp] given listBiTraversable[>-->[- _, + _]: Program]: BiTraversable[List, >-->] = 
  given BiTraversable[Product[Constant, Identity], >-->] = productBiTraversable[Constant, Identity, >-->]
  sumBiTraversable[None, Product[Constant, Identity], >-->]

import psbp.external.specification.structure.recursive.RecursiveStructure

// import psbp.external.specification.structure.{ Structure }

// import psbp.external.specification.structure.recursive.recursiveStructureFormRecursiveReducerTypeAndStructure

import psbp.internal.specification.structure.recursive.recursiveStructureFromBiTraversable

import psbp.external.specification.recursion.Recursion

given listRecursiveStructure[
  R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Program
  ]: RecursiveStructure[List, R, >-->] = 
    // recursiveStructureFormRecursiveReducerTypeAndStructure[List, R, >-->]
    recursiveStructureFromBiTraversable[List, R, >-->]

// given listStructure[
//   R[+_[+ _]]: [R[+_[+ _]]] =>> Recursive[R, >-->]
//   , >-->[- _, + _]: Program
//   ]: Structure[RecursiveList[R], >-->] = 
//     structureFromBiTraversable[List, R, >-->]