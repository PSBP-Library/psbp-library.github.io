package psbp.external.specification.program

import scala.language.postfixOps

import psbp.external.specification.function.`z=>z`

trait Functional[>-->[- _, + _]] extends Identity[>-->]:

  // declared

  private[psbp] def toProgram[Z, Y]: (Z => Y) => Z >--> Y

  private implicit val functional: Functional[>-->] = this

  // defined

  override def identity[Z]: Z >--> Z =
    
    `z=>z` asProgram  

  // defined extensions

  extension [Z, Y] (`z=>y`: Z => Y) def asProgram: Z >--> Y =
    toProgram(`z=>y`)
