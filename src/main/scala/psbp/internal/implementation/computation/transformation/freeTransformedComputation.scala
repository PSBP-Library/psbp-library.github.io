package psbp.internal.implementation.computation.transformation

import psbp.external.specification.program.Program

import psbp.internal.specification.computation.Resulting

import psbp.internal.specification.computation.Computation

import psbp.internal.specification.naturalTransformation.~>

import psbp.internal.specification.computation.transformation.Transformation

import Free._

private[psbp] given freeTransformedComputation[
  C[+ _]: Resulting]: Transformation[C, FreeTransformed[C]
] with Computation[FreeTransformed[C]] with

  private type F[+Y] = C[Y]
  private type T[+Y] = FreeTransformed[F][Y] 
    
  override private[psbp] val `f~>t`: F ~> T = new {
    def apply[Z]: F[Z] => T[Z] =
      fz => 
        Transform(fz)
  }    

  override private[psbp] def result[Z]: Z => T[Z] =
    z =>
      Result(z)

  override private[psbp] def bind[Z, Y](
    tz: T[Z]
    , `z=>ty` : => Z => T[Y]
  ): T[Y] = 
    Bind(tz, `z=>ty`) 
