package psbp.internal.implementation.computation.transformation

import psbp.external.specification.program.writing.{
  Writable
  , Writing
}

import psbp.internal.specification.computation.Computation

private[psbp] given writingTransformedWriting[
  W: Writable
  , C[+ _]: Computation
]: Writing[
  W
  , [Z, Y] =>> Z => WritingTransformed[W, C][Y]
] with

  private type F[+Z] = C[Z]
  private type T[+Z] = WritingTransformed[W, C][Z]

  private type `=>T` = [Z, Y] =>> Z => T[Y]

  private val computationF: Computation[F] = 
    summon[Computation[F]]
  import computationF.{ 
    result => resultF
  }

  override private[psbp] def `w>-->u`: W `=>T` Unit =
    w =>
      resultF((w, ()))