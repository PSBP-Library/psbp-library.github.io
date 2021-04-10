package psbp.external.specification.structure

trait Foldable[
  A[+ _]
  , >-->[- _, + _]
] extends FolderType: 

  // declared
  
  def fold[Y, X]: Folder[Y, X] => A[Y] >--> X


