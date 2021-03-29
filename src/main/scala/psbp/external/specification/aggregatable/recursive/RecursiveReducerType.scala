package psbp.external.specification.aggregatable.recursive

import psbp.external.specification.program.Program

import psbp.external.specification.aggregatable.ReducerType

// maybe back to private
private[psbp] trait RecursiveReducerType[A[+ _, + _], >-->[- _, + _]: Program] extends ReducerType:
  // maybe back to private
  override private[psbp] type Reducer[Y, X] = A[Y, X] >--> X

