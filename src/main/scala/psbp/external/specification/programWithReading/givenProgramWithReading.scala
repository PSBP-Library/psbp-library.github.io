package psbp.external.specification.programWithReading

import psbp.external.specification.program.Program

import psbp.external.specification.program.reading.{
  Readable
  , Reading
}

given givenProgramWithReading[
  R: Readable, 
  >-->[- _, + _]: Program
                : [>-->[- _, + _]] =>> Reading[R, >-->]
]: ProgramWithReading[R, >-->] with
 
  private val program: Program[>-->] = 
    summon[Program[>-->]]

  export program.identity
  export program.toProgram
  export program.andThen
  export program.construct
  export program.conditionally

  private val reading: Reading[R, >-->] = 
    summon[Reading[R, >-->]]

  export reading.readR