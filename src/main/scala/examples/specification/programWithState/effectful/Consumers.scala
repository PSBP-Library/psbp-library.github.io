package examples.specification.programWithState.effectful

import scala.language.postfixOps

import psbp.external.specifcation.types.&&

import psbp.external.specifcation.program.Program

val effectfulTwoRandomsConsumer: (Unit && (BigInt && BigInt)) => Unit =
  case (_, (i, j)) =>
    println(s"generating two random numbers yields result ${(i, j)}")

def twoRandomsConsumer[
  >-->[- _, + _]: Program
  ]: (Unit && (BigInt && BigInt)) >--> Unit =
  effectfulTwoRandomsConsumer asProgram

