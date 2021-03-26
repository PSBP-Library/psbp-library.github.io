package psbp.external.specifcation.aggregatable

import psbp.external.specifcation.types.&&

trait Aggregatable[A[+ _], >-->[- _, + _]] 
  extends InitialTraverser[>-->]
  with InitialReducer[A]
  with Traversable[A, >-->] 
  with Reducible[A, >-->]:

  // defined

  // private[psbp] type Aggregator[Z, Y, X] = Traverser[Z, Y] && Reducer[Y, X]

  private[psbp] type Aggregator[Z, Y, X] = Z >--> Y && Reducer[Y, X]

  // declared

  def aggregate[Z, Y, X]: Aggregator[Z, Y, X] => A[Z] >--> X

  // defined

  override def traverse[Z, Y]: Z >--> Y => A[Z] >--> A[Y] =
    aggregate(_, initialReducer)

  override def reduce[Y, X]: Reducer[Y, X] => A[Y] >--> X = 
    aggregate(initialTraverser, _)
