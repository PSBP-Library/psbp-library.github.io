package psbp.internal.implementation.computation.transformation

import psbp.external.specification.types.&&

import psbp.external.specification.program.writing.Writable

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.computation.Computation

import psbp.internal.specification.naturalTransformation.~>

// import psbp.internal.specification.computation.givenComputationFromResultingAndBinding

import psbp.internal.specification.computation.transformation.Transformation

// import psbp.internal.specification.computation.transformation.givenResultingFromTransformation

private[psbp] given writingTransformedTransformationWithBinding[
  W : Writable
  , C[+ _]: Computation
]:
  Transformation[C,  WritingTransformed[W, C]] 
  with Binding[[Y] =>> WritingTransformed[W, C][Y]] with 

  private type F[+Y] = C[Y]
  private type T[+Y] = WritingTransformed[W, C][Y]

  private type `=>T` = [Z, Y] =>> Z => T[Y]

  private val computation: Computation[F] = 
    summon[Computation[F]]
  import computation.{ 
    result => resultF
    , bind => bindF
  }

  private val writable: Writable[W] = summon[Writable[W]]
  import writable.nothing

  override private[psbp] val `f~>t`: F ~> T = new {
    def apply[Z]: F[Z] => T[Z] =
      fz =>
        // println("in `f~>t` of writingTransformedTransformationWithBinding")
        bindF(fz, z => resultF((nothing, z)))
  }  

  override private[psbp] def bind[Z, Y](tz: T[Z], `z=>ty`: => Z => T[Y]): T[Y] =
    bindF(tz, { (w1, z) =>
      // println(">>> in bind of writingTransformedTransformationWithBinding")
      val (w2, y): W && Y = `z=>ty`(z)
      resultF((w1 + w2, y))
    })

private[psbp] given writingTransformedComputation[W: Writable, C[+ _]: Computation]: Computation[WritingTransformed[W, C]] = 
  writingTransformedTransformationWithBinding[W, C]