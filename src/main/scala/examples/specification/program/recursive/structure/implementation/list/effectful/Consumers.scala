package examples.specification.program.recursive.structure.implementation.list.effectful

import scala.language.postfixOps

import scala.collection.immutable.Seq

import psbp.external.specification.types.&&

import psbp.external.specification.program.Program 

import psbp.external.specification.recursion.Recursion

import psbp.external.implementation.list.List

import psbp.external.specification.structure.recursive.{ 
  Recursive
  , RecursiveStructure 
}

import examples.specification.program.recursive.structure.implementation.list.recursiveListToSeq

def areAllPositiveConsumer[
  Z
  , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Program
                  : [>-->[- _, + _]] =>> RecursiveStructure[List, R, >-->]
]: (Recursive[R][List][BigInt] && Boolean) >--> Unit =

  val program: Program[>-->] = 
    summon[Program[>-->]]
  import program.identity

  object consumerFunction {

    val areAllOfSeqPositiveConsumer: (Seq[BigInt] && Boolean) => Unit =
      (is, j) =>
        println(s"applying areAllPositive to the list of integer arguments ${is.mkString(" ")} yields result $j")

  }

  val areAllOfSeqPositiveConsumer: (Seq[BigInt] && Boolean) >--> Unit =
    consumerFunction.areAllOfSeqPositiveConsumer asProgram  
  
  (recursiveListToSeq &&& identity) >--> areAllOfSeqPositiveConsumer

