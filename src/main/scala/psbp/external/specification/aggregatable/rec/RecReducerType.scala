package psbp.external.specifcation.aggregatable.rec

import psbp.external.specifcation.aggregatable.ReducerType

private[psbp] trait RecReducerType[A[+ _, + _]] extends ReducerType:

  override private[psbp] type Reducer[Y, X] = A[Y, X] => X

