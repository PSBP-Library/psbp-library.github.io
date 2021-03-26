package psbp.external.specifcation.aggregatable.rec

import psbp.external.specifcation.aggregatable.Aggregatable

import psbp.external.specifcation.types.RightRec

trait RecAggregatable[A[+ _, + _], >-->[- _, + _]] 
  extends Aggregatable[RightRec[A], >-->] with RecReducerType[A]