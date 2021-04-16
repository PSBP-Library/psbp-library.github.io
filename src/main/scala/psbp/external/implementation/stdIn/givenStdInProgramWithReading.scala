package psbp.external.implementation.stdIn

import scala.language.postfixOps

import psbp.external.specification.program.Program

import psbp.external.specification.program.reading.{
  Readable
  , Reading
}

import psbp.external.specification.programWithReading.ProgramWithReading

import psbp.external.specification.programWithReading.givenProgramWithReading

given givenStdInProgramWithReading[
  Z: [Z] =>> Readable[StdIn[Z]],
  >-->[- _, + _]: Program
]: ProgramWithReading[StdIn[Z], >-->] with

  private val program: Program[>-->] = summon[Program[>-->]]

  export program.identity
  export program.andThen
  export program.construct
  export program.conditionally
  export program.toProgram

  private type R = StdIn[Z]

  override def readR: Unit >--> R = 

    object function {

      val readR: Unit => R =
        _ =>
          val readable = summon[Readable[StdIn[Z]]]
          readable.r

    }

    function.readR asProgram