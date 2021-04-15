package examples.specification.programWithState.seed.effectful

import scala.language.postfixOps

import psbp.external.specification.program.Program

object producerFunction {

  val unitProducer: Unit => Unit = 
    _ =>
      ()

}

def unitProducer[
  >-->[- _, + _]: Program
]: Unit >--> Unit = 
  producerFunction.unitProducer asProgram  