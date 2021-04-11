package psbp.external.specification.programWithWriting

import psbp.external.specification.program.Program

import psbp.external.specification.program.writing.{
  Writable
  , Writing
}
given programWithWriting[
  W: Writable, 
  >-->[- _, + _]: Program
                : [>-->[- _, + _]] =>> Writing[W, >-->]
]: ProgramWithWriting[W, >-->] with
 
  private val program: Program[>-->] = 
    summon[Program[>-->]]

  export program.`z>-->z`
  export program.toProgram
  export program.andThen
  export program.construct
  export program.conditionally

  private val writing: Writing[W, >-->] = 
    summon[Writing[W, >-->]]

  export writing.`w>-->u`