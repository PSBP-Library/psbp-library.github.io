package psbp.internalSpecification.computation.transformation

import psbp.internalSpecification.computation.Computation

import psbp.internalSpecification.naturalTransformation.~>

private[psbp] trait Transformation[F[+ _]: Computation, T[+ _]] extends Computation[T]:

  // declared

  private[psbp] val `f~>t`: F ~> T
  
  // defined computational capabilities 
  
  override private[psbp] def result[Z]: Z => T[Z] = 

    import `f~>t`.{ apply => `fz=>tz` }

    val computationF: Computation[F] = summon[Computation[F]]
    import computationF.{ result => `z=>fz` }
    
    `z=>fz` andThen `fz=>tz`
    
    
