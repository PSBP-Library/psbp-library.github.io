package psbp.external.specification.structure

trait Unfoldable[
  A[+ _]
  , >-->[- _, + _]
] extends UnfolderType: 

  // declared
  
  def unfold[X, Y]: Unfolder[X, Y] => X >--> A[Y]

