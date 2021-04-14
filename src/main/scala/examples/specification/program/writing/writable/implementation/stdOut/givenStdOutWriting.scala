package examples.specification.program.writing.writable.implementation.stdOut

import scala.language.postfixOps

import psbp.external.specification.program.Program

import psbp.external.specification.program.writing.Writing

import psbp.external.implementation.stdOut.StdOut

import psbp.external.implementation.stdOut.givenStdOutWritable

given givenStdOutWriting[>-->[- _, + _]: Program]: Writing[StdOut, >-->] with

  override def write: StdOut >--> Unit =

    object function {

      val write: StdOut => Unit =
        case StdOut(effect) =>
          // println(">>> foo")
          effect(()) // effectful
          // () // effectfree

    }

    function.write asProgram
