package psbp.external.specification.structure.recursive

import psbp.external.specification.structure.FolderType

trait RecursiveFolderType[
  A[+ _, + _]
  , >-->[- _, + _]
  ] extends FolderType:

  // defined

  override type Folder[Y, X] = A[Y, X] >--> X


