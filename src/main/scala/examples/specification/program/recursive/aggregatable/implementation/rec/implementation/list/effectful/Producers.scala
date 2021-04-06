package examples.specification.program.recursive.aggregatable.implementation.rec.implementation.list.effectful

import scala.language.postfixOps

import scala.collection.immutable.Seq

import psbp.external.specification.program.Program

import psbp.external.implementation.rec.list.RecursiveList

import examples.specification.functional.seqToRecursiveList

def recursiveIntListProducer[
  >-->[- _, + _]: Program
]: Unit >--> RecursiveList[BigInt] =

  object producerFunction {

    val intSeqProducer: Unit => Seq[BigInt] =
      _ =>
        println("Please type a sequence of integers separated by a blank")
        Seq(scala.io.StdIn.readLine.split(" ").map(s => BigInt(s.toInt)).toSeq: _*)

  }

  val intSeqProducer =
    producerFunction.intSeqProducer asProgram

  intSeqProducer >--> seqToRecursiveList