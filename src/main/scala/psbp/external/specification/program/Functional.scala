package psbp.external.specification.program

import psbp.external.specification.function.`z=>z`

trait Functional[>-->[- _, + _]] 
  extends Identity[>-->]:

  // declared

  private[psbp] def toProgram[Z, Y]: (Z => Y) => Z >--> Y

  // defined

  override def `z>-->z`[Z]: Z >--> Z =
    toProgram(`z=>z`)  

  // defined extensions

  extension [Z, Y] (`z=>y`: Z => Y) def asProgram: Z >--> Y =
    toProgram(`z=>y`)
