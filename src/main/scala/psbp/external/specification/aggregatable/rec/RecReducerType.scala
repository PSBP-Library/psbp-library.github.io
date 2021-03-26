package psbp.external.specifcation.aggregatable.rec

import psbp.external.specifcation.aggregatable.ReducerType

trait RecReducerType[A[+ _, + _]] extends ReducerType:

  override type Reducer[Y, X] = A[Y, X] => X

