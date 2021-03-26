package psbp.implementation.rec.list

import psbp.external.specifcation.types.{ LeftRec }

import psbp.internal.implementation.aggregatable.rec.{ Sum, None, Cons, SumStructure, NoneStructure, ConsStructure }

private[psbp] type List[+Z, +Y] = Sum[None, Cons][Z, Y]

private[psbp] type ListStructure[Z, Y] = SumStructure[NoneStructure, ConsStructure][Z, Y]

type RecList[+Z] = LeftRec[List][Z]


