package psbp.external.specification.program.parallel

import psbp.external.specification.types.&&

trait Parallel[>-->[- _, + _]]:
  
  // declared

  private[psbp] def parallel[Z, Y, X, W](
    `z>-->x`: Z >--> X
    , `y>-->w`: Y >--> W
  ): (Z && Y) >--> (X && W)

  private[psbp] def asynchronous[Z, Y](`z>-->y`: Z >--> Y): Z >--> Y 
  
  // defined extensions

  extension [Z, Y, X, W] (`z>-->x`: Z >--> X)
    def |&&&|(`y>-->w`: Y >--> W): (Z && Y) >--> (X && W) =
      parallel(`z>-->x`, `y>-->w`)

  extension [Z, Y] (`z>-->y`: Z >--> Y) def async: Z >--> Y  =
    asynchronous(`z>-->y`)