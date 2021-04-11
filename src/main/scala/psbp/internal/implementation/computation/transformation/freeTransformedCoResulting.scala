package psbp.internal.implementation.computation.transformation

import psbp.internal.specification.computation.{ 
  Computation
  , CoResulting 
}

private[psbp] given freeTransformedCoResulting[
  C[+ _]: Computation
        : CoResulting
]: CoResulting[FreeTransformed[C]] with

  private type F[+Y] = C[Y]
  private type T[+Y] = FreeTransformed[F][Y] 
  
  private val coResultingF : CoResulting[F] = 
    summon[CoResulting[F]]
  import coResultingF.{ 
    coResult => `fz=>z` 
  }

  private def `tz=>fz`[Z]: T[Z] => F[Z] =
    foldFree

  override def coResult[Z]: T[Z] => Z =
    `tz=>fz` andThen `fz=>z`
