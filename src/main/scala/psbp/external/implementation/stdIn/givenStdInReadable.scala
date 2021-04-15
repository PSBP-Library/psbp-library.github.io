package psbp.external.implementation.stdIn

import psbp.external.specification.program.reading.Readable

given givenStdInReadable[Z]: Readable[StdIn[Z]] with

  override private[psbp] val dummy: Unit = ()

