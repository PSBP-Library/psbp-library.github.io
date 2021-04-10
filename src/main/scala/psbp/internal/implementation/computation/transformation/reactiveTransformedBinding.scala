package psbp.internal.implementation.computation.transformation

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.computation.Computation

import psbp.internal.specification.naturalTransformation.~>

import psbp.internal.specification.computation.transformation.Transformation

private[psbp] given reactiveTransformedBinding[
  C[+ _]: Computation]: 
  Transformation[C, ReactiveTransformed[C]] 
  with Binding[ReactiveTransformed[C]] with 

  private type F[+Z] = C[Z]
  private type T[+Z] = ReactiveTransformed[F][Z]
  
  private val computationF: Computation[F] = 
    summon[Computation[F]]
  import computationF.{ 
    result => resultF
    , bind => bindF
  }
    
  override private[psbp] val `f~>t`: F ~> T = new {
    def apply[Z]: F[Z] => T[Z] =
      fz => 
        _ apply fz
  }
  
  override private[psbp] def bind[Z, Y](
    tz: T[Z]
    , `z=>ty` : => Z => T[Y]
  ): T[Y] =
    `fy=>u` =>
      tz { 
        fz =>
          bindF(fz, { z =>
            resultF(`z=>ty`(z)(`fy=>u`))
            })
      }
