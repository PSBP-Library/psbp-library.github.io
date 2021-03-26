package examples.specification.program.implementation.rec.list.aggregatable.effectful

import scala.collection.immutable

import scala.language.postfixOps

import psbp.external.specifcation.program.Program

import psbp.implementation.rec.list.{ RecList, fromSeq }
  
def intRecListProducer[>-->[- _, + _]: Program]: Unit >--> RecList[BigInt] =
  { (_: Unit) =>
    println("Please type a sequence of integers separated by a blank")
    fromSeq(immutable.Seq(scala.io.StdIn.readLine.split(" ").map(s => BigInt(s.toInt)).toSeq: _*))
  } asProgram