package psbp.internal.implementation.aggregatable.rec

import psbp.external.specifcation.types.{ &&, || }

private[psbp] type None[+Y, +X] = Unit
// type None[+Y, +X] = Unit


private[psbp] def mkNone[Y, X]: None[Y, X] = ()

// maybe change back
private[psbp] type Constant[+Y, +X] = X
// type Constant[+Y, +X] = X

private[psbp] def mkConstant[Y, X]: X => Constant[Y, X] =
  x =>
    x

// maybe change back
private[psbp] type Identity[+Y, +X] = Y
// type Identity[+Y, +X] = Y

private[psbp] def mkIdentity[Y, X]: Y => Identity[Y, X] =
  y =>
    y

private[psbp] type Product[L[+ _, + _], R[+ _, + _]] = [Y, X] =>> L[Y, X] && R[Y, X]  
   
private[psbp] type Sum[L[+ _, + _], R[+ _, + _]] = [Y, X] =>> L[Y, X] || R[Y, X]  

private[psbp] type Cons[+Y, +X] = Product[Constant, Identity][Y, X]

// private[psbp] type List[+Y, +X] = Sum[None, Cons][Y, X]


private[psbp] type NoneStructure[Y, X] = None[X, Y] => X

private[psbp] type ConstantStructure[Y, X] = Constant[X, Y] => X  

private[psbp] type IdentityStructure[Y, X] = Identity[X, Y] => X

private[psbp] type ProductStructure[L[+ _, + _], R[+ _, + _]] = [Y, X] =>> (L[Y, X] && R[Y, X]) => X

private[psbp] type SumStructure[LS[_, _], RS[_, _]] = [Y, X] =>> LS[Y, X] && RS[Y, X] 

private[psbp] type ConsStructure[Y, X] = ProductStructure[Constant, Identity][Y, X]

// private[psbp] type ListStructure[Y, X] = SumStructure[NoneStructure, ConsStructure][Y, X]