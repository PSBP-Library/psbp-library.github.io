package examples.specification.program.effectful

import scala.language.postfixOps

import psbp.external.specification.program.Program

object producerFunction {

  val intProducer: Unit => BigInt =
    _ =>
      println("Please type an integer")
      BigInt(scala.io.StdIn.readInt)

}

def intProducer[
  >-->[- _, + _]: Program
]: Unit >--> BigInt =
  producerFunction.intProducer asProgram
  
  
