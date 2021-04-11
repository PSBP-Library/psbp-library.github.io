package psbp.internal.implementation.computation.transformation

import psbp.external.specification.program.reading.Reading

import psbp.internal.specification.computation.Computation

private[psbp] given readingTransformedReading[
  R
  , C[+ _]: Computation
]: Reading[
  R
  , [Z, Y] =>> Z => ReadingTransformed[R, C][Y]
] with

  private type F[+Z] = C[Z]
  private type T[+Z] = ReadingTransformed[R, C][Z]

  private type `=>T` = [Z, Y] =>> Z => T[Y]

  private val computationF: Computation[F] = 
    summon[Computation[F]]
  import computationF.{ 
    result => resultF
  }

  override private[psbp] def `u>-->r`: Unit `=>T` R =
    _ =>
      val r: R = summon[R]
      resultF(r)