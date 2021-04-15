package psbp.internal.implementation.computation.transformation.writing

import psbp.external.specification.program.reading.{
  Readable
  , Reading
}

import psbp.external.specification.program.writing.{
  Writable
  , Writing
}

import psbp.internal.specification.computation.Computation

import psbp.internal.implementation.computation.transformation.ReadingTransformed

private[psbp] given readingTransformedWriting[
  R: Readable
  , W: Writable
  , C[+ _]: [C[+ _]] =>> Writing[W, [Z, Y] =>> Z => C[Y]]
]: Writing[
  W
  , [Z, Y] =>> Z => ReadingTransformed[R, C][Y]
] with

  private type F[+Y] = C[Y]
  private type T[+Y] = ReadingTransformed[R, C][Y]

  private type `=>F` = [Z, Y] =>> Z => F[Y]
  private type `=>T` = [Z, Y] =>> Z => T[Y]

  private val writing: Writing[W, `=>F`] = 
    summon[Writing[W, `=>F`]]
  import writing.{
    write => writeF
  }

  override def writeW: W `=>T` Unit =
    w =>
      writeF(w)
