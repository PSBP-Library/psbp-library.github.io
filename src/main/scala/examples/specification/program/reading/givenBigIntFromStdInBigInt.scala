package examples.specification.program.reading

import scala.language.postfixOps

import psbp.external.specification.program.Program

import psbp.external.specification.program.reading.FromReadable

import psbp.external.implementation.stdIn.StdIn

given givenBigIntFromStdInBigInt[>-->[- _, + _]: Program]: FromReadable[StdIn[BigInt], BigInt, >-->] with

  override def converter: StdIn[BigInt] >--> BigInt =

    object function {

      val converter: StdIn[BigInt] => BigInt =
        case StdIn(i) =>
          i

    }

    function.converter asProgram