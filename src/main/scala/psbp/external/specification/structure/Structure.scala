package psbp.external.specification.structure

import scala.annotation.targetName

import psbp.external.specification.types.&&

private[psbp] trait Structure[
  A[+ _]
  , >-->[- _, + _]
] extends Traversable[A, >-->] 
  with InitialTraverser[>-->]
  with Foldable[A, >-->] 
  with InitialFolder[A] 
  with Unfoldable[A, >-->]
  with InitialUnfolder[A]:

  // declared

  @targetName("fuseFolder")
  private[psbp] def fuse[Z, Y, X]: (Z >--> Y && Folder[Y, X]) => Folder[Z, X]

  
  @targetName("fuseUnfolder")
  private[psbp] def fuse[X, Y, Z]: (Unfolder[X, Y] && Y >--> Z) => Unfolder[X, Z]

  // defined

  def aggregate[Z, Y, X]: (Z >--> Y && Folder[Y, X]) => A[Z] >--> X =
    fuse andThen fold

  def unaggregate[X, Y, Z]: (Unfolder[X, Y] && Y >--> Z) => X >--> A[Z] =
    fuse andThen unfold    

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
