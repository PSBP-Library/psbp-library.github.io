package examples.specification.program.recursive.aggregatable.implementation.list

import scala.collection.immutable.Seq

import psbp.external.specification.program.Program 

import psbp.external.specification.recursion.Recursion

import psbp.external.specification.aggregatable.recursive.{
  Recursive
  , RecursivelyAggregatable
}

import psbp.external.implementation.list.List

import examples.specification.functional.recursiveFolderType.toSeqFolder

def recursiveListToSeq[
  Z
  , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Program
                  : [>-->[- _, + _]] =>> RecursivelyAggregatable[List, R, >-->]
]: Recursive[R][List][Z] >--> Seq[Z] = 
    
  val aggregatable: RecursivelyAggregatable[List, R, >-->] = summon[RecursivelyAggregatable[List, R, >-->]]
  import aggregatable.fold

  fold(toSeqFolder[Z, >-->])

import examples.specification.functional.recursiveUnfolderType.fromSeqUnfolder
  
def seqToRecursiveList[
  Z
  , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Program
                  : [>-->[- _, + _]] =>> RecursivelyAggregatable[List, R, >-->]
]: Seq[Z] >--> Recursive[R][List][Z]  = 
    
  val aggregatable: RecursivelyAggregatable[List, R, >-->] = summon[RecursivelyAggregatable[List, R, >-->]]
  import aggregatable.unfold

  unfold(fromSeqUnfolder[Z, >-->])  