package examples.specification.program.implementation.rec.list.aggregatable.effectful

import scala.language.postfixOps

import scala.collection.immutable.Seq

import psbp.external.specification.program.Program

import psbp.external.implementation.rec.list.{ RecList, emptyRecList, consRecList }

def seqToRecListFunction[Z]: Seq[Z] => RecList[Z] = 
  _.foldRight(emptyRecList)(consRecList) 

def seqToRecList[
  Z,
  >-->[- _, + _]: Program]: Seq[Z] >--> RecList[Z] = 
  seqToRecListFunction asProgram
  
def effectfulIntSeqProducer[>-->[- _, + _]: Program]: Unit => Seq[BigInt] =
  _ =>
    println("Please type a sequence of integers separated by a blank")
    Seq(scala.io.StdIn.readLine.split(" ").map(s => BigInt(s.toInt)).toSeq: _*)

def intSeqProducer[>-->[- _, + _]: Program]: Unit >--> Seq[BigInt] =
  effectfulIntSeqProducer asProgram

def intRecListProducer[>-->[- _, + _]: Program]: Unit >--> RecList[BigInt] =
  intSeqProducer >--> seqToRecList