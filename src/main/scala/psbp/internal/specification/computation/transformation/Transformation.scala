package psbp.internal.specification.computation.transformation

import psbp.internal.specification.naturalTransformation.~>
  
private[psbp] trait Transformation[F[+ _], T[+ _]]:

  // declared

  private[psbp] val `f~>t`: F ~> T
  
  