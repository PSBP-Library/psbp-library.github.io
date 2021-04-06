package psbp.external.implementation.list

import psbp.internal.implementation.aggregatable.recursive.{ 
  Sum
  , None
  , Product
  , Constant
  , Identity
}

type List[+Y, +X] = Sum[None, Product[Constant, Identity]][Y, X]

