package examples.specification.program.implementation.rec.list.aggregatable.effectful

import scala.language.postfixOps

import scala.collection.immutable.Seq

import psbp.external.specification.program.Program 

import psbp.external.specification.recursion.Recursion

import psbp.external.implementation.list.List

import psbp.external.specification.aggregatable.recursive.{ 
  Recursive
  , RecursivelyAggregatable 
}

import examples.specification.program.recursive.aggregatable.implementation.list.seqToRecursiveList

def recursiveIntListProducer[
  Z
  , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Program
                  : [>-->[- _, + _]] =>> RecursivelyAggregatable[List, R, >-->]
]: Unit >--> Recursive[R][List][BigInt] =

  object producerFunction {

    val intSeqProducer: Unit => Seq[BigInt] =
      _ =>
        println("Please type a sequence of integers separated by a blank")
        Seq(scala.io.StdIn.readLine.split(" ").map(s => BigInt(s.toInt)).toSeq: _*)

  }

  val intSeqProducer: Unit >--> Seq[BigInt] =
    producerFunction.intSeqProducer asProgram

  intSeqProducer >--> seqToRecursiveList
