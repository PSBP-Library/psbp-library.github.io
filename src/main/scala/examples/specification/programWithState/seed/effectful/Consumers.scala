package examples.specification.programWithState.seed.effectful

import scala.language.postfixOps

import psbp.external.specification.types.&&

import psbp.external.specification.program.Program

object consumerFunction {

  val twoRandomsConsumer: (Unit && (BigInt && BigInt)) => Unit =
    case (_, (i, j)) =>
      println(s"generating two random numbers yields result ${(i, j)}")

}

def twoRandomsConsumer[
  >-->[- _, + _]: Program
]: (Unit && (BigInt && BigInt)) >--> Unit =
  consumerFunction.twoRandomsConsumer asProgram

