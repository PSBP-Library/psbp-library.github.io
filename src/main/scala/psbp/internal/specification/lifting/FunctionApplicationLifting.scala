package psbp.internal.specification.lifting

private[psbp] trait FunctionApplicationLifting[C[+ _]] 
  extends ValueLifting[C] 
  with ProductLifting[C] 
  with ApplicationLifting[C]
