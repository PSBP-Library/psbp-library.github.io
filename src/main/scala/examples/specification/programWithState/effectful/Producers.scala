package examples.specification.programWithState.effectful

import scala.language.postfixOps

import psbp.external.specification.program.Program

val effectlessUnitProducer: Unit => Unit = 
  _ =>
    ()

def unitProducer[>-->[- _, + _]: Program]: Unit >--> Unit = 
  effectlessUnitProducer asProgram  