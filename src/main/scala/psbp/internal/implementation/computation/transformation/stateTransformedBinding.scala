package psbp.internal.implementation.computation.transformation

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.computation.Computation

import psbp.internal.specification.naturalTransformation.~>

import psbp.internal.specification.computation.transformation.Transformation

private[psbp] given stateTransformedBinding[
  S
  , C[+ _]: Computation
]: Transformation[C, StateTransformed[S, C]] 
   with Binding[[Z] =>> StateTransformed[S, C][Z]] with
  
  private type F[+Z] = C[Z]
  private type T[+Z] = StateTransformed[S, C][Z]

  private type `=>T` = [Z, Y] =>> Z => T[Y]

  private val computationF: Computation[F] = 
    summon[Computation[F]]
  import computationF.{ 
    result => resultF
    , bind => bindF
  }

  override private[psbp] val `f~>t`: F ~> T = new {
    def apply[Z]: F[Z] => T[Z] =
      fz =>
        val gs: S = summon[S]
        bindF(fz, z => resultF((gs, z)))
  }

  override private[psbp] def bind[Z, Y](
    tz: T[Z]
    , `z=>ty`: => Z => T[Y]
  ): T[Y] =
    // val gs = summon[S]
    bindF(tz, (s, z) => { given gs: S = s; `z=>ty`(z) })    


      