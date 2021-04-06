package psbp.external.specification.aggregatable.recursive

import psbp.external.specification.aggregatable.ReducerType

trait RecursiveReducerType[
  A[+ _, + _]
  , >-->[- _, + _]
  ] extends ReducerType:

  // defined

  override type Reducer[Y, X] = A[Y, X] >--> X

