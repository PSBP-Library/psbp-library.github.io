package psbp.internalSpecification.computation.transformation

import psbp.internalSpecification.computation.{ Computation, CoResulting }

private[psbp] given freeCoResulting[C[+ _]: Computation: CoResulting]: CoResulting[FreeTransformed[C]] with

  private type F[+Z] = C[Z]
  private type T[+Z] = FreeTransformed[F][Z] 
  
  private val coResultingF : CoResulting[F] = summon[CoResulting[F]]
  import coResultingF.{ coResult => `fz=>z` }

  private def `tz=>fz`[Z]: T[Z] => F[Z] =
    tz =>
      foldFree(tz) 

  override def coResult[Z]: T[Z] => Z =
    `tz=>fz` andThen `fz=>z`
