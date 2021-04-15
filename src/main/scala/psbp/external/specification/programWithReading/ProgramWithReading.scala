package psbp.external.specification.programWithReading

import psbp.external.specification.program.Program

import psbp.external.specification.program.reading.{
  Readable
  , Reading
}

trait ProgramWithReading[R: Readable, >-->[- _, + _]] 
  extends Program[>-->] 
  with Reading[R, >-->]
        