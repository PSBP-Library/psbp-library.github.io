package examples.specification.program.recursive.structure.implementation.list

import scala.collection.immutable.Seq

import psbp.external.specification.program.Program 

import psbp.external.specification.recursion.Recursion

import psbp.external.specification.structure.recursive.{
  Recursive
  , RecursiveStructure
}

import psbp.external.implementation.list.List

import examples.specification.functional.recursiveFolderType.toSeqFolder

def recursiveListToSeq[
  Z
  , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Program
                  : [>-->[- _, + _]] =>> RecursiveStructure[List, R, >-->]
]: Recursive[R][List][Z] >--> Seq[Z] = 
    
  val structure: RecursiveStructure[List, R, >-->] = summon[RecursiveStructure[List, R, >-->]]
  import structure.fold

  fold(toSeqFolder[Z, >-->])

import examples.specification.functional.recursiveUnfolderType.fromSeqUnfolder
  
def seqToRecursiveList[
  Z
  , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Program
                  : [>-->[- _, + _]] =>> RecursiveStructure[List, R, >-->]
]: Seq[Z] >--> Recursive[R][List][Z]  = 
    
  val structure: RecursiveStructure[List, R, >-->] = summon[RecursiveStructure[List, R, >-->]]
  import structure.unfold

  unfold(fromSeqUnfolder[Z, >-->])  