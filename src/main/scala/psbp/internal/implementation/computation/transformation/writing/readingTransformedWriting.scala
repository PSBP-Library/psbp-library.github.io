package psbp.internal.implementation.computation.transformation.writing

import psbp.external.specification.program.reading.Reading

import psbp.internal.implementation.computation.transformation.ReadingTransformed

import psbp.external.specification.program.writing.{
  Writable
  , Writing
}

import psbp.internal.specification.computation.Computation

private[psbp] given readingTransformedWriting[
  R, W: Writable
  , C[+ _]: [C[+ _]] =>> Writing[W, [Z, Y] =>> Z => C[Y]]
]: Writing[
  W
  , [Z, Y] =>> Z => ReadingTransformed[R, C][Y]
] with

  private type F[+Y] = C[Y]
  private type T[+Y] = ReadingTransformed[R, C][Y]

  private type `=>F` = [Z, Y] =>> Z => F[Y]
  private type `=>T` = [Z, Y] =>> Z => T[Y]

  private val writing: Writing[W, `=>F`] = summon[Writing[W, `=>F`]]
  import writing. {
    `w>-->u` => `w>-f->u`
  }

  private[psbp] def `w>-->u`: W `=>T` Unit =
    w =>
      `w>-f->u`(w)
