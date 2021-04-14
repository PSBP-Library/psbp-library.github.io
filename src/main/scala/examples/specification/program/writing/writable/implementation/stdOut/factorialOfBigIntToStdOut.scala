package examples.specification.program.writing.writable.implementation.stdOut

import scala.language.postfixOps

import psbp.external.specification.types.&&

import psbp.external.specification.program.Program

import psbp.external.specification.program.writing.ToWritable

import psbp.external.implementation.stdOut.StdOut

import psbp.external.implementation.stdOut.givenStdOutWritable

given factorialOfBigIntToStdOut[>-->[- _, + _]: Program]: ToWritable[(BigInt && BigInt), StdOut, >-->] with

  override def converter: (BigInt && BigInt) >--> StdOut =

    object function {

      val converter: (BigInt && BigInt) => StdOut =
        (i, j) =>
          StdOut{ _ =>
            // println(">>> converter")
            println(s"applying factorial to the integer argument $i yields result $j")
          }

    }

    function.converter asProgram