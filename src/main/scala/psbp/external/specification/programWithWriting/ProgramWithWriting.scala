package psbp.external.specification.programWithWriting

import psbp.external.specification.program.Program

import psbp.external.specification.program.writing.{
  Writable
  , Writing
}

trait ProgramWithWriting[W: Writable, >-->[- _, + _]] 
  extends Program[>-->] 
  with Writing[W, >-->]
        