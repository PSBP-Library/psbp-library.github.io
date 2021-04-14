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

  private type F[+Y] = C[Y]
  private type T[+Y] = ReadingTransformed[R, C][Y]

  private type `=>T` = [Z, Y] =>> Z => T[Y]

  private val computationF: Computation[F] = 
    summon[Computation[F]]
  import computationF.{ 
    result => resultF
  }

  override def read: Unit `=>T` R =
    _ =>
      println(">>> read in readingTransformedReading")
      Thread.sleep(3000)
      val r: R = summon[R]
      println(s">>> read $r in readingTransformedReading")
      resultF(r)