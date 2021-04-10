package psbp.internal.specification.lifting

import psbp.external.specification.types.&&

private[psbp] given functionLiftingFromFunctionApplicationLifting[
  C[+ _]: FunctionApplicationLifting
]: FunctionLifting[C] with

  private val functionApplicationLifting: FunctionApplicationLifting[C] = 
    summon[FunctionApplicationLifting[C]]
  import functionApplicationLifting.{ 
    liftValue
    , liftProduct2
    , liftProduct3
    , liftApply
  }
  
  // defined
  
  override private[psbp] def lift0[Z]: Z => C[Z] =
    z =>
      liftValue(z)

  override private[psbp] def lift1[Z, Y]: (Z => Y) => C[Z] => C[Y] =
    `z=>y` => 
      cz =>
        liftApply(liftValue(`z=>y`), cz)  

  override private[psbp] def lift2[Z, Y, X]: ((Z && Y) => X) => (C[Z] && C[Y]) => C[X] =
    `(z&&y)=>x` =>
      `cz&&cy` =>
        liftApply(liftValue(`(z&&y)=>x`), liftProduct2(`cz&&cy`))      

  override private[psbp] def lift3[Z, Y, X, W]: ((Z && Y && X) => W) => (C[Z] && C[Y] && C[X]) => C[W] =
    (`((z&&y)&&x)=>w`: ((Z && Y && X) => W)) =>
      (`cz&&cy&&cx`: (C[Z] && C[Y] && C[X])) =>
        liftApply(liftValue(`((z&&y)&&x)=>w`), liftProduct3(`cz&&cy&&cx`))   

  // ...