package examples.specification.programWithState.effectful

import scala.language.postfixOps

import psbp.specification.program.{ &&, Program }

def twoRandomsConsumer[>-->[- _, + _]: Program]: (Unit && (BigInt && BigInt)) >--> Unit =
  {
    (`u&&(i&&j)`: Unit && (BigInt && BigInt)) =>
      val `i&&j` = `u&&(i&&j)`._2
      println(s"generating two random integers yields result ${`i&&j`}")
  } asProgram

