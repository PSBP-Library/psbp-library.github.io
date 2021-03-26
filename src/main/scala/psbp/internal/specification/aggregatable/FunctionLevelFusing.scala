package psbp.internal.specification.aggregatable

import psbp.external.specifcation.types.&&

import psbp.external.specifcation.aggregatable.ReducerType

private[psbp] trait FunctionLevelFusing extends ReducerType:

  // defined

  private[psbp] type FunctionLevelAggregator[Z, Y, X] = (Z => Y) && Reducer[Y, X]

  // declared

  private[psbp] def functionLevelFuse[Z, Y, X]: FunctionLevelAggregator[Z, Y, X] => Reducer[Z, X]
