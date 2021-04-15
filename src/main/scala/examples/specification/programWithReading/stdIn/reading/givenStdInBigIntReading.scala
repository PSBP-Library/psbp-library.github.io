package examples.specification.programWithReading.stdIn.reading

import scala.language.postfixOps

import psbp.external.specification.program.Program

import psbp.external.specification.program.reading.Reading

import psbp.external.implementation.stdIn.StdIn

import psbp.external.implementation.stdIn.givenStdInReadable

given givenStdInBigIntReading[>-->[- _, + _]: Program]: Reading[StdIn[BigInt], >-->] with

  private type R = StdIn[BigInt]

  override def readR: Unit >--> R = 

    object function {

      val readR: Unit => R =
        _ =>
          println("Please type an integer")
          StdIn(BigInt(scala.io.StdIn.readInt))

    }

    function.readR asProgram