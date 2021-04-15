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

  private type F[+Y] = C[Y]
  private type T[+Y] = WritingTransformed[W, C][Y]

  private type `=>T` = [Z, Y] =>> Z => T[Y]

  private val computation: Computation[F] = 
    summon[Computation[F]]
  import computation.{ 
    result => resultF
  }

  override private[psbp] def writeW: W `=>T` Unit =
    w =>
      resultF((w, ()))