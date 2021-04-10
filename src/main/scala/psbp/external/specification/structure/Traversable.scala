package psbp.external.specification.structure

trait Traversable[
  A[+ _]
  , >-->[- _, + _]
]:

  // declared

  def traverse[Z, Y]: (Z >--> Y) => A[Z] >--> A[Y]
