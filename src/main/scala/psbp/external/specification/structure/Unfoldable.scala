package psbp.external.specification.structure

private[psbp] trait Unfoldable[A[+ _], >-->[- _, + _]] extends UnfolderType: 

  // declared
  
  private[psbp] def unfold[X, Y]: Unfolder[X, Y] => X >--> A[Y]

