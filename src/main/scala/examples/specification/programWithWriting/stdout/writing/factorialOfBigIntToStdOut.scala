package examples.specification.programWithWriting.stdOut.writing

import scala.language.postfixOps

import psbp.external.specification.types.&&

import psbp.external.specification.program.Program

import psbp.external.specification.program.writing.ToWritable

import psbp.external.implementation.stdOut.StdOut

import psbp.external.implementation.stdOut.givenStdOutWritable

given factorialOfBigIntToStdOut[>-->[- _, + _]: Program]: ToWritable[(BigInt && BigInt), StdOut, >-->] with

  type Y = BigInt && BigInt

  type W = StdOut

  override def `y>-->w`: Y >--> W =

    object function {

      val `y=>w`: Y => W =
        (i, j) =>
          StdOut{ _ =>
            println(s"applying factorial to the integer argument $i yields result $j")
          }

    }

    function.`y=>w` asProgram