package psbp.external.specification.aggregatable

import psbp.external.specification.types.&&

private[psbp] trait Aggregatable[
  A[+ _]
  , >-->[- _, + _]
] 
  extends Traversable[A, >-->] 
  with InitialTraverser[>-->]
  with Foldable[A, >-->] 
  with Unfoldable[A, >-->]
  with InitialFolder[A] 
  with InitialUnfolder[A]:

  // declared

  private[psbp] def fuseFolder[Z, Y, X]: (Z >--> Y && Folder[Y, X]) => Folder[Z, X]

  private[psbp] def fuseUnfolder[X, Y, Z]: (Unfolder[X, Y] && Y >--> Z) => Unfolder[X, Z]

  def aggregate[Z, Y, X]: (Z >--> Y && Folder[Y, X]) => A[Z] >--> X =
    fuseFolder andThen fold

  def unaggregate[X, Y, Z]: (Unfolder[X, Y] && Y >--> Z) => X >--> A[Z] =
    fuseUnfolder andThen unfold    

  // defined

  override def traverse[Z, Y]: Z >--> Y => A[Z] >--> A[Y] =
    aggregate(_, initialFolder)

  /**
   * can be defined but is overridden 
   * for recursive poynomial data structures 
   */
  override def fold[Y, X]: Folder[Y, X] => A[Y] >--> X =
    aggregate(initialTraverser, _)
    
  /**
   * can be defined but is overridden 
   * for recursive poynomial data structures 
   */
  override def unfold[X, Y]: Unfolder[X, Y] => X >--> A[Y] =
    unaggregate(_, initialTraverser)
