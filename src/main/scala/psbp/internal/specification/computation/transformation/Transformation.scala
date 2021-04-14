package psbp.internal.specification.computation.transformation

import psbp.internal.specification.computation.Resulting

import psbp.internal.specification.computation.Computation

import psbp.internal.specification.naturalTransformation.~>
  
private[psbp] trait Transformation[F[+ _]: Resulting, T[+ _]]
  extends Computation[T]:

  // declared

  private[psbp] val `f~>t`: F ~> T

  // defined
  
  override private[psbp] def result[Z]: Z => T[Z] = 

    import `f~>t`.{ apply => `fz=>tz` }

    val resulting: Resulting[F] = 
      summon[Resulting[F]]
    import resulting.{ 
      result => `z=>fz` 
    }
    
    `z=>fz` andThen `fz=>tz`    
  
  