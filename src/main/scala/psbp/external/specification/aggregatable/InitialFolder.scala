package psbp.external.specification.aggregatable

private[psbp] trait InitialFolder[A[+ _]] extends FolderType:

  // declared

  private[psbp] def initialFolder[Y]: Folder[Y, A[Y]]
