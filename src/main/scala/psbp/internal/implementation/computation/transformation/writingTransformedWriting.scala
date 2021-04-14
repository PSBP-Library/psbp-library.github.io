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

  private val computationF: Computation[F] = 
    summon[Computation[F]]
  import computationF.{ 
    result => resultF
  }

  override def write: W `=>T` Unit =
    w =>
      // println(">>> writing $w in writingTransformedWriting")
      resultF((w, ()))