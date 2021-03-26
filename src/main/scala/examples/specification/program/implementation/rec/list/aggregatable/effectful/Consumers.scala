package examples.specification.program.implementation.rec.list.aggregatable.effectful

import scala.language.postfixOps

import psbp.external.specifcation.types.&&

import psbp.external.specifcation.program.Program 

import psbp.implementation.rec.list.{ RecList, toSeq }
  
def areAllPositiveConsumer[>-->[- _, + _]: Program]: (RecList[BigInt] && Boolean) >--> Unit =
  {
    (`is&&j`: RecList[BigInt] && Boolean) =>
      val is = `is&&j`._1
      val j = `is&&j`._2
      println(s"applying areAllPositive to the list of integer arguments ${toSeq(is).mkString(" ")} yields result $j")
  } asProgram