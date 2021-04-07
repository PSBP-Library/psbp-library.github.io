package psbp.external.specification.structure

private[psbp] trait InitialUnfolder[A[+ _]] extends UnfolderType:

  // declared

  private[psbp] def initialUnfolder[Y]: Unfolder[A[Y], Y]