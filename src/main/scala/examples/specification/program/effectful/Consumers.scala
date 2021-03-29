package examples.specification.program.effectful

import scala.language.postfixOps

import psbp.external.specification.types.&&

import psbp.external.specification.program.Program 

val effectfulFactorialConsumer: (BigInt && BigInt) => Unit =
  (i, j) =>
    println(s"applying factorial to the integer argument $i yields result $j")

def factorialConsumer[
  >-->[- _, + _]: Program
  ]: (BigInt && BigInt) >--> Unit =
  effectfulFactorialConsumer asProgram

val effectfulFibonacciConsumer: (BigInt && BigInt) => Unit =
  (i, j) =>
    println(s"applying fibonacci to the integer argument $i yields result $j")

def fibonacciConsumer[
  >-->[- _, + _]: Program
  ]: (BigInt && BigInt) >--> Unit =
  effectfulFibonacciConsumer asProgram

  
