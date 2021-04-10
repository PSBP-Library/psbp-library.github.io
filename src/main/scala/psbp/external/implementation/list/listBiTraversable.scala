package psbp.external.implementation.list

import psbp.external.specification.program.Program

import psbp.internal.specification.structure.BiTraversable 

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

