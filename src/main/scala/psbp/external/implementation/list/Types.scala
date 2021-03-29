package psbp.external.implementation.list

// import psbp.external.specification.types.{ Rec }

import psbp.internal.implementation.aggregatable.rec.{ Product, Identity, Constant, Sum, None }

// // import psbp.internal.implementation.aggregatable.rec.{ Sum, None, Cons, SumStructure }

// // import psbp.internal.implementation.aggregatable.rec.{ Sum, None, Cons, SumStructure, NoneStructure, ConsStructure }

private[psbp] type Cons[+Y, +X] = Product[Identity, Constant][Y, X]

type List[+Y, +X] = Sum[None, Cons][Y, X]

type RecursiveList[R[+_[+ _]], +Z] = R[[X] =>> List[Z, X]]


// // private[psbp] type ListStructure[Y, X] = SumStructure[NoneStructure, ConsStructure][Y, X]

// // private[psbp] type ListStructure[Y, X] = SumStructure[None, Cons][Y, X]

// // private[psbp] type ListStructure[Y, X] = Sum[None, Cons][Y, X] => X

// type RecList[+Z] = RightRec[List][Z]


// // private[psbp] type RightRec[A[+ _, + _]] = [Y] =>> Rec[[X] =>> A[Y, X]]
