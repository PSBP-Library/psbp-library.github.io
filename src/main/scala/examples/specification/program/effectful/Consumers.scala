package examples.specification.program.effectful

import scala.language.postfixOps

import psbp.external.specification.types.&&

import psbp.external.specification.program.Program

object consumerFunction {

  val factorialConsumer: (BigInt && BigInt) => Unit =
    (i, j) =>
      println(s"applying factorial to the integer argument $i yields result $j")

  val fibonacciConsumer: (BigInt && BigInt) => Unit =
    (i, j) =>
      println(s"applying fibonacci to the integer argument $i yields result $j")

}

def factorialConsumer[
  >-->[- _, + _]: Program
]: (BigInt && BigInt) >--> Unit =
  consumerFunction.factorialConsumer asProgram

def fibonacciConsumer[
  >-->[- _, + _]: Program
]: (BigInt && BigInt) >--> Unit =
  consumerFunction.fibonacciConsumer asProgram

  
