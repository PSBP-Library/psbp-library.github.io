package psbp.external.specification.structure

private[psbp] trait InitialTraverser[>-->[- _, + _]]:

  // declared

  private[psbp] def initialTraverser[Y]: Y >--> Y