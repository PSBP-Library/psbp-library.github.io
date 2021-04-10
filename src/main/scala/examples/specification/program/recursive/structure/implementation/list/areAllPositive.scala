package examples.specification.program.recursive.structure.implementation.list

import psbp.external.specification.program.Program 

import psbp.external.specification.recursion.Recursion

import psbp.external.specification.structure.recursive.{
  Recursive
  , RecursiveStructure
}

import psbp.external.implementation.list.List

import examples.specification.functional.isPositive

import examples.specification.functional.recursiveFolderType.areAllTrueFolder
  
def areAllPositive[
  R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Program
                  : [>-->[- _, + _]] =>> RecursiveStructure[List, R, >-->]
]: Recursive[R, List][BigInt] >--> Boolean = 

  val structure: RecursiveStructure[List, R, >-->] = 
    summon[RecursiveStructure[List, R, >-->]]
  import structure.aggregate

  aggregate(isPositive, areAllTrueFolder[>-->]) 
