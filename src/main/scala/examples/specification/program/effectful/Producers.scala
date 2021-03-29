package examples.specification.program.effectful

import scala.language.postfixOps

import psbp.external.specification.program.Program

val effectfulIntProducer: Unit => BigInt =
  _ =>
    println("Please type an integer")
    BigInt(scala.io.StdIn.readInt)

def intProducer[
  >-->[- _, + _]: Program
  ]: Unit >--> BigInt = 
    effectfulIntProducer asProgram
  
  
