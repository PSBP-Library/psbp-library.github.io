package psbp.external.specification.programWithReadingWithWriting

import psbp.external.specification.program.Program

import psbp.external.specification.program.reading.{
  Readable
  , Reading
}

import psbp.external.specification.program.writing.{
  Writable
  , Writing
}

given givenProgramWithReadingWithWriting[
  R: Readable
  , W: Writable
  , >-->[- _, + _]: Program
                  : [>-->[- _, + _]] =>> Reading[R, >-->]
                  : [>-->[- _, + _]] =>> Writing[W, >-->]
]: ProgramWithReadingWithWriting[R, W, >-->] with
 
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

  private val writing: Writing[W, >-->] = 
    summon[Writing[W, >-->]]  
  export writing.writeW