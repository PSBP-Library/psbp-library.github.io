package psbp.external.specification.aggregatable.recursive

import psbp.external.specification.aggregatable.FolderType

trait RecursiveFolderType[
  A[+ _, + _]
  , >-->[- _, + _]
  ] extends FolderType:

  // defined

  override type Folder[Y, X] = A[Y, X] >--> X


