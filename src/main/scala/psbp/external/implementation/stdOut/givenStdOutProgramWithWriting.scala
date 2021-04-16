package psbp.external.implementation.stdOut

import scala.language.postfixOps

import psbp.external.specification.program.Program

import psbp.external.specification.program.writing.Writing

import psbp.external.specification.programWithWriting.ProgramWithWriting

import psbp.external.implementation.stdOut.StdOut

import psbp.external.implementation.stdOut.givenStdOutWritable

given givenStdOutProgramWithWriting[>-->[- _, + _]: Program]: ProgramWithWriting[StdOut, >-->] with

  private val program: Program[>-->] = summon[Program[>-->]]

  export program.identity
  export program.andThen
  export program.construct
  export program.conditionally
  export program.toProgram

  private type W = StdOut

  override def writeW: W >--> Unit =

    object function {

      val writeW: W => Unit =
        case StdOut(effect) =>
          effect(())

    }

    function.writeW asProgram
