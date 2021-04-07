package psbp.external.specification.aggregatable.recursive

import psbp.external.specification.aggregatable.UnfolderType

trait RecursiveUnfolderType[
  A[+ _, + _]
  , >-->[- _, + _]
  ] extends UnfolderType:

  // defined

  override type Unfolder[X, Y] = X >--> A[Y, X]

