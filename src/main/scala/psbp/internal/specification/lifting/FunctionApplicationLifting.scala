package psbp.internal.specification.lifting

import psbp.external.specifcation.types.&&

private[psbp] trait FunctionApplicationLifting[C[+ _]] 
  extends ValueLifting[C] 
  with ProductLifting[C] 
  with ApplicationLifting[C]
