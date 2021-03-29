package examples.specification.program.implementation.rec.list.aggregatable.effectful

import scala.language.postfixOps

import scala.collection.immutable.Seq

import psbp.external.specification.types.&&

import psbp.external.specification.program.Program 

import psbp.external.implementation.list.{ List, RecursiveList }

import psbp.external.specification.recursive.Recursive

import psbp.external.implementation.list.{ recListToSeq }

import psbp.external.specification.aggregatable.recursive.{ RecursiveAggregatable }

def areAllPositiveConsumer[
  Z,
  R[+_[+ _]],
  >-->[- _, + _]: Program
                : [>-->[- _, + _]] =>> Recursive[R, >-->]
                : [>-->[- _, + _]] =>> RecursiveAggregatable[List, R, >-->]
  ]: (RecursiveList[R, BigInt] && Boolean) >--> Unit =

  val program: Program[>-->] = summon[Program[>-->]]
  import program.identity

  val localEffectfulAreAllPositiveConsumer: (Seq[BigInt] && Boolean) => Unit =
    (is, j) =>
      println(s"applying areAllPositive to the list of integer arguments ${is.mkString(" ")} yields result $j")

  val localAreAllPositiveConsumer: (Seq[BigInt] && Boolean) >--> Unit =
    localEffectfulAreAllPositiveConsumer asProgram  
  
  (recListToSeq &&& identity) >--> localAreAllPositiveConsumer

/*

package examples.specification.program.implementation.rec.list.aggregatable.effectful

import scala.language.postfixOps

import scala.collection.immutable

import psbp.external.specification.types.&&

import psbp.external.specification.program.Program 

import psbp.external.implementation.rec.list.{ RecList }

import psbp.external.implementation.list.{ recListToSeq }

import psbp.external.implementation.rec.recRecursive

import psbp.external.implementation.list.listRecursiveAggregatable

import psbp.external.implementation.rec.Rec

def areAllPositiveConsumer[>-->[- _, + _]: Program]: (RecList[BigInt] && Boolean) >--> Unit =

  val program: Program[>-->] = summon[Program[>-->]]
  import program.identity

  val localEffectfulAreAllPositiveConsumer: (Seq[BigInt] && Boolean) => Unit =
    (is, j) =>
      println(s"applying areAllPositive to the list of integer arguments ${is.mkString(" ")} yields result $j")

  val localAreAllPositiveConsumer: (Seq[BigInt] && Boolean) >--> Unit =
    localEffectfulAreAllPositiveConsumer asProgram
  
  (recListToSeq &&& identity) >--> localAreAllPositiveConsumer
*/

// package examples.specification.program.implementation.rec.list.aggregatable.effectful

// import scala.language.postfixOps

// import psbp.external.specification.types.&&

// import psbp.external.specification.program.Program 

// import psbp.implementation.rec.list.{ RecList, toSeq }
  
// def areAllPositiveConsumer[>-->[- _, + _]: Program]: (RecList[BigInt] && Boolean) >--> Unit =
//   {
//     (`is&&j`: RecList[BigInt] && Boolean) =>
//       val is = `is&&j`._1
//       val j = `is&&j`._2
//       println(s"applying areAllPositive to the list of integer arguments ${toSeq(is).mkString(" ")} yields result $j")
//   } asProgram