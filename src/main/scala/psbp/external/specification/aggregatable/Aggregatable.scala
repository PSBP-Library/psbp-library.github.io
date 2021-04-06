package psbp.external.specification.aggregatable

import psbp.external.specification.types.&&

private[psbp] trait Aggregatable[
  A[+ _]
  , >-->[- _, + _]
] 
  extends InitialTraverser[>-->]
  with InitialReducer[A]
  with Traversable[A, >-->] 
  with Reducible[A, >-->]:

  // declared

  private[psbp] def fuseReducer[Z, Y, X]: (Z >--> Y && Reducer[Y, X]) => Reducer[Z, X]

  def aggregate[Z, Y, X]: (Z >--> Y && Reducer[Y, X]) => A[Z] >--> X =
      fuseReducer andThen reduce

  // defined

  override def traverse[Z, Y]: Z >--> Y => A[Z] >--> A[Y] =
    aggregate(_, initialReducer)

  /**
   * can be defined but is overridden 
   * for recursive poynomial data structures 
   */
  override def reduce[Y, X]: Reducer[Y, X] => A[Y] >--> X =
    aggregate(initialTraverser, _)
