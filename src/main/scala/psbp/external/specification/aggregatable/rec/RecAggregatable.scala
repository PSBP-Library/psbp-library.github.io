package psbp.external.specifcation.aggregatable.rec

import psbp.external.specifcation.types.&&

import psbp.external.specifcation.aggregatable.Aggregatable

import psbp.external.specifcation.types.LeftRec

private[psbp] trait RecAggregatable[A[+ _, + _], >-->[- _, + _]] extends Aggregatable[LeftRec[A], >-->] with RecStructureToRecReducer[A]:

  private[psbp] type RecAggregator[Z, Y, X] = Z >--> Y && Structure[Y, X]

  def recAggregate[Z, Y, X]: RecAggregator[Z, Y, X] => LeftRec[A][Z] >--> X =
    (`z>-->y`, structure) =>
      val reducer = structureToReducer(structure)
      val aggregator = (`z>-->y`, reducer)
      aggregate(aggregator)

