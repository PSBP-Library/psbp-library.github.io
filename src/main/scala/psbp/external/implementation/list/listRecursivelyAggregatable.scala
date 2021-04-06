package psbp.external.implementation.list

import psbp.external.specification.program.Program

import psbp.internal.specification.aggregatable.{ BiTraversable }

import psbp.internal.implementation.aggregatable.recursive.{ 
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

import psbp.external.specification.aggregatable.recursive.RecursivelyAggregatable

// import psbp.external.specification.aggregatable.{ Aggregatable }

// import psbp.external.specification.aggregatable.recursive.recursivelyAggregatableFormRecursiveReducerTypeAndAggregatable

import psbp.internal.specification.aggregatable.recursive.recursivelyAggregatableFromBiTraversable

import psbp.external.specification.recursion.Recursion

given listRecursivelyAggregatable[
  R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Program
  ]: RecursivelyAggregatable[List, R, >-->] = 
    // recursivelyAggregatableFormRecursiveReducerTypeAndAggregatable[List, R, >-->]
    recursivelyAggregatableFromBiTraversable[List, R, >-->]

// given listAggregatable[
//   R[+_[+ _]]: [R[+_[+ _]]] =>> Recursive[R, >-->]
//   , >-->[- _, + _]: Program
//   ]: Aggregatable[RecursiveList[R], >-->] = 
//     aggregatableFromBiTraversable[List, R, >-->]