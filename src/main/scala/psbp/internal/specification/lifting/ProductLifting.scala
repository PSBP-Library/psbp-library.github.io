package psbp.internal.specification.lifting

import psbp.external.specification.types.&&

import psbp.external.specification.function.`(z=>y)=>((z&&x)=>(y&&x)))`

private[psbp] trait ProductLifting[C[+ _]]:

  // declared

  private[psbp] def liftProduct2[Z, Y]: (C[Z] && C[Y]) => C[Z && Y]

  // defined
  
  private[psbp] def liftProduct3[Z, Y, X, W]: (C[Z] && C[Y] && C[X]) => (C[Z && Y && X]) = 
    `(z=>y)=>((z&&x)=>(y&&x)))`(liftProduct2) andThen liftProduct2

  // liftProduct4, ...  