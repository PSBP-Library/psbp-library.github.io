package psbp.external.specification.structure.recursive

import psbp.external.specification.structure.UnfolderType

trait RecursiveUnfolderType[
  A[+ _, + _]
  , >-->[- _, + _]
  ] extends UnfolderType:

  // defined

  override type Unfolder[X, Y] = X >--> A[Y, X]

