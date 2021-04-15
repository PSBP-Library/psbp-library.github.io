package examples.specification.programWithWriting.stdOut.writing

import scala.language.postfixOps

import psbp.external.specification.program.Program

import psbp.external.specification.program.writing.Writing

import psbp.external.implementation.stdOut.StdOut

import psbp.external.implementation.stdOut.givenStdOutWritable

given givenStdOutWriting[>-->[- _, + _]: Program]: Writing[StdOut, >-->] with

  private type W = StdOut

  override def writeW: W >--> Unit =

    object function {

      val writeW: W => Unit =
        case StdOut(effect) =>
          effect(()) // effectful
          // () // effectfree

    }

    function.writeW asProgram
