package psbp.specification.program

import psbp.specification.types.||

trait Condition[>-->[- _, + _]]:

  // declared

  private[psbp] def conditionally[Z, Y, X] (`y>-->z`: => Y >--> Z, `x>-->z`: => X >--> Z): (Y || X) >--> Z

  // defined extensions
  
  extension [Z, Y, X] (`y>-->z`: => Y >--> Z) def ||(`x>-->z`: => X >--> Z): (Y || X) >--> Z =
    conditionally(`y>-->z`, `x>-->z`)