package examples.specification.program.recursive.aggregatable.implementation.list

import psbp.external.specification.program.Program 

import psbp.external.specification.recursion.Recursion

import psbp.external.specification.aggregatable.recursive.{
  Recursive
  , RecursivelyAggregatable
}

import psbp.external.implementation.list.List

import examples.specification.functional.isPositive

import examples.specification.functional.recursiveFolderType.areAllTrueFolder
  
def areAllPositive[
  R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Program
                  : [>-->[- _, + _]] =>> RecursivelyAggregatable[List, R, >-->]
]: Recursive[R][List][BigInt] >--> Boolean = 

  val aggregatable: RecursivelyAggregatable[List, R, >-->] = 
    summon[RecursivelyAggregatable[List, R, >-->]]
  import aggregatable.aggregate

  aggregate(isPositive, areAllTrueFolder[>-->]) 
