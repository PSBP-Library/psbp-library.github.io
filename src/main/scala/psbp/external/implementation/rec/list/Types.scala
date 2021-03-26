package psbp.implementation.rec.list

import psbp.external.specifcation.types.{ RightRec }

import psbp.internal.implementation.aggregatable.rec.{ Sum, None, Cons }

// import psbp.internal.implementation.aggregatable.rec.{ Sum, None, Cons, SumStructure }

// import psbp.internal.implementation.aggregatable.rec.{ Sum, None, Cons, SumStructure, NoneStructure, ConsStructure }

// maybe back to private
type List[+Y, +X] = Sum[None, Cons][Y, X]

// private[psbp] type ListStructure[Y, X] = SumStructure[NoneStructure, ConsStructure][Y, X]

// private[psbp] type ListStructure[Y, X] = SumStructure[None, Cons][Y, X]

// private[psbp] type ListStructure[Y, X] = Sum[None, Cons][Y, X] => X

type RecList[+Z] = RightRec[List][Z]


