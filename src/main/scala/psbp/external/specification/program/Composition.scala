package psbp.external.specification.program

trait Composition[>-->[- _, + _]]:

  // declared

  private[psbp] def andThen[Z, Y, X](
    `z>-->y`: Z >--> Y
    , `y>-->x`: => Y >--> X
  ) : Z >--> X

  // defined extensions
  
  extension [Z, Y, X] (`z>-->y`: Z >--> Y) 
    def >-->(`y>-->x`: => Y >--> X): Z >--> X =
      andThen(`z>-->y`, `y>-->x`)
