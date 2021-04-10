package psbp.external.specification.programWithParallel

import psbp.external.specification.types.&&

import psbp.external.specification.program.Program

import psbp.external.specification.program.parallel.Parallel

import psbp.external.specification.functional.{ 
  `z>-->(z&&u)`
  , `(y&&u)>-->y`
  , `z>-->(z&&z)`
}

trait ProgramWithParallel[>-->[- _, + _]] 
  extends Program[>-->] 
  with Parallel[>-->]:

  private given program: Program[>-->] = this

  // defined
  
  override private[psbp] def asynchronous[Z, Y](`z>-->y`: Z >--> Y): Z >--> Y =
    `z>-->(z&&u)` >--> (`z>-->y` |&&&| `u>-->u`) >--> `(y&&u)>-->y`

  // defined extensions

  extension [Z, Y, X] (`z>-->y`: Z >--> Y) 
    def |&&|(`z>-->x`: Z >--> X): Z >--> (Y && X) =
      `z>-->(z&&z)` >--> (`z>-->y` |&&&| `z>-->x`)