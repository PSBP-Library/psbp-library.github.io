package examples.specification.programWithState.effectful

import scala.language.postfixOps

import psbp.specification.program.Program

def unitProducer[>-->[- _, + _]: Program]: Unit >--> Unit = 
  { (_: Unit) =>
      ()
  } asProgram  