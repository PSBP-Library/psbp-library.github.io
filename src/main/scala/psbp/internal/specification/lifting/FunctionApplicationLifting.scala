package psbp.internal.specification.lifting

// import psbp.external.specification.types.&&

private[psbp] trait FunctionApplicationLifting[C[+ _]] 
  extends ValueLifting[C] 
  with ProductLifting[C] 
  with ApplicationLifting[C]
