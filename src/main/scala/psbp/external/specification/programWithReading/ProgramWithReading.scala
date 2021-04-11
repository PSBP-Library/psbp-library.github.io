package psbp.external.specification.programWithReading

import psbp.external.specification.program.Program

import psbp.external.specification.program.reading.Reading

trait ProgramWithReading[R, >-->[- _, + _]] 
  extends Program[>-->] 
  with Reading[R, >-->]
        