package examples.specification.program.reading

import scala.language.postfixOps

import psbp.external.specification.program.Program

import psbp.external.specification.program.reading.Reading

import psbp.external.implementation.stdIn.StdIn

given givenStdInBigIntReading[>-->[- _, + _]: Program]: Reading[StdIn[BigInt], >-->] with

  override def read: Unit >--> StdIn[BigInt] = 

    object function {

      val read: Unit => StdIn[BigInt] =
        _ =>
          println("Please type an integer")
          StdIn(BigInt(scala.io.StdIn.readInt))

    }

    function.read asProgram