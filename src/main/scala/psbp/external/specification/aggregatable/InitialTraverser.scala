package psbp.external.specification.aggregatable

private[psbp] trait InitialTraverser[>-->[- _, + _]]:

  // declared

  private[psbp] def initialTraverser[Y]: Y >--> Y