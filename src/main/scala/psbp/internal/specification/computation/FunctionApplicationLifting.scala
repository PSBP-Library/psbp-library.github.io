package psbp.internal.specification.computation

import psbp.specification.types.&&

private[psbp] trait FunctionApplicationLifting[C[+ _]] 
  extends ValueLifting[C] 
  with ProductLifting[C] 
  with ApplicationLifting[C]
