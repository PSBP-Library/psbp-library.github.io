package psbp.external.specification.structure

import psbp.external.specification.program.Classification

private[psbp] trait InitialTraverser[>-->[- _, + _]: Classification]:

  private val classification: Classification[>-->] =
    summon[Classification[>-->]]
  import classification.`y>-->y`  

  // defined

  private[psbp] def initialTraverser[Y]: Y >--> Y =
    `y>-->y`
