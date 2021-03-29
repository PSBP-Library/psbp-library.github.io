package psbp.external.specification.aggregatable

import psbp.external.specification.types.&&

import psbp.external.specification.program.Program

trait Aggregatable[A[+ _], >-->[- _, + _]: Program] 
  extends InitialTraverser[>-->]
  with InitialReducer[A]
  with Traversable[A, >-->] 
  with Reducible[A, >-->]:

  // defined

  private[psbp] type Aggregator[Z, Y, X] = Z >--> Y && Reducer[Y, X]

  private[psbp] def fuse[Z, Y, X]: Aggregator[Z, Y, X] => Reducer[Z, X]

  // declared

  def aggregate[Z, Y, X]: Aggregator[Z, Y, X] => A[Z] >--> X =
    fuse andThen reduce

  // defined

  override def traverse[Z, Y]: Z >--> Y => A[Z] >--> A[Y] =
    aggregate(_, initialReducer)

  // defined but overridden anyway (can be left out)
  // override def reduce[Y, X]: Reducer[Y, X] => A[Y] >--> X =
  //   aggregate(initialTraverser, _)
