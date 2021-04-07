package psbp.external.specification.aggregatable

private[psbp] trait Foldable[A[+ _], >-->[- _, + _]] extends FolderType: 

  // declared
  
  private[psbp] def fold[Y, X]: Folder[Y, X] => A[Y] >--> X


