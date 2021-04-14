package psbp.internal.implementation.computation.transformation

import psbp.internal.specification.computation.Computation

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.naturalTransformation.~>

// import psbp.internal.specification.computation.givenComputationFromResultingAndBinding

import psbp.internal.specification.computation.transformation.Transformation

// import psbp.internal.specification.computation.transformation.givenResultingFromTransformation

private[psbp] given readingTransformedTransformationWithBinding[
  R
  , C[+ _]: Computation
]:  
  Transformation[C, ReadingTransformed[R, C]] 
  with Binding[[Y] =>> ReadingTransformed[R, C][Y]] with

  private type F[+Y] = C[Y]
  private type T[+Y] = ReadingTransformed[R, C][Y]

  private type `=>T` = [Z, Y] =>> Z => T[Y]

  private val computation: Computation[F] = 
    summon[Computation[F]]
  import computation.{ 
    result => resultF
    , bind => bindF
  }

  override private[psbp] val `f~>t`: F ~> T = new {
    def apply[Z]: F[Z] => T[Z] =
      fz =>
        // fz
        bindF(fz, z => resultF(z))
  }  

  override private[psbp] def bind[Z, Y](tz: T[Z], `z>=ty`: => Z => T[Y]): T[Y] =
    bindF(tz, z => `z>=ty`(z) )
       
private[psbp] given readingTransformedComputation[R, C[+ _]: Computation]: Computation[ReadingTransformed[R, C]] = 
  readingTransformedTransformationWithBinding[R, C]
