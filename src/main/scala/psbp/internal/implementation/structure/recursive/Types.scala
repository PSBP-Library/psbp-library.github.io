package psbp.internal.implementation.structure.recursive

import psbp.external.specification.types.{ 
  &&
  , ||
}

private[psbp] type None[+Y, +X] = Unit

private[psbp] type Identity[+Y, +X] = X

private[psbp] type Constant[+Y, +X] = Y

private[psbp] type Product[L[+ _, + _], R[+ _, + _]] = [Y, X] =>> L[Y, X] && R[Y, X]  
   
private[psbp] type Sum[L[+ _, + _], R[+ _, + _]] = [Y, X] =>> L[Y, X] || R[Y, X]  
