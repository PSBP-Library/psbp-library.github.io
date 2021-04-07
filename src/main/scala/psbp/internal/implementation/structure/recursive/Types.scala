package psbp.internal.implementation.structure.recursive

import psbp.external.specification.types.{ &&, || }

private[psbp] type None[+Y, +X] = Unit

def mkNone[Y, X]: None[Y, X] = ()

private[psbp] type Identity[+Y, +X] = X

// def mkIdentity[Y, X]: X => Identity[Y, X] =
//   identity

private[psbp] type Constant[+Y, +X] = Y

// def mkConstant[Y, X]: Y => Constant[Y, X] =
//   identity

private[psbp] type Product[L[+ _, + _], R[+ _, + _]] = [Y, X] =>> L[Y, X] && R[Y, X]  
   
private[psbp] type Sum[L[+ _, + _], R[+ _, + _]] = [Y, X] =>> L[Y, X] || R[Y, X]  
