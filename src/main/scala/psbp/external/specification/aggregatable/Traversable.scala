package psbp.external.specifcation.aggregatable


trait Traversable[A[+ _], >-->[- _, + _]] extends InitialTraverser[>-->]:

  // declared

  def traverse[Z, Y]: (Z >--> Y) => A[Z] >--> A[Y]
