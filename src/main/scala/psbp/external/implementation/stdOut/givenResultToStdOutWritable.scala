package psbp.external.implementation.stdOut

import scala.language.postfixOps

import psbp.external.specification.types.&&

import psbp.external.specification.program.Program

import psbp.external.specification.program.writing.ToWritable

import psbp.external.implementation.stdOut.StdOut

import psbp.external.implementation.stdOut.givenStdOutWritable

given givenResultToStdOutWritable[
  Z
  , Y
  , >-->[- _, + _]: Program
](using programName: String): ToWritable[(Z && Y), StdOut, >-->] with

  type W = StdOut

  override def `y>-->w`: (Z && Y) >--> W =
    `(z&&y)>-->w`

  private def `(z&&y)>-->w`: (Z && Y) >--> W =  
    object function {

      val `(z&&y)=>w`: (Z && Y) => W =
        (z, y) =>
          StdOut{ _ =>
            println(s"applying $programName to argument $z yields result $y")
          }

    }

    function.`(z&&y)=>w` asProgram

