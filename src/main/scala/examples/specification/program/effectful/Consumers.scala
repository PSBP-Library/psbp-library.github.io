package examples.specification.program.effectful

import scala.language.postfixOps

import psbp.specification.types.&&

import psbp.specification.program.Program 

def factorialConsumer[>-->[- _, + _]: Program]: (BigInt && BigInt) >--> Unit =
  {
    (`i&&j`: BigInt && BigInt) =>
      val i = `i&&j`._1
      val j = `i&&j`._2
      println(s"applying factorial to the integer argument $i yields result $j")
  } asProgram

def fibonacciConsumer[>-->[- _, + _]: Program]: (BigInt && BigInt) >--> Unit =
  {
    (`i&&j`: BigInt && BigInt) =>
      val i = `i&&j`._1
      val j = `i&&j`._2
      println(s"applying fibonacci to the integer argument $i yields result $j")
  } asProgram

