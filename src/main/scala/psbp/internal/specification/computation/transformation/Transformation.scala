package psbp.internal.specification.computation.transformation

import psbp.internal.specification.computation.Computation

import psbp.internal.specification.naturalTransformation.~>

private[psbp] trait Transformation[F[+ _]: Computation, T[+ _]] extends Computation[T]:

  // declared

  private[psbp] val `f~>t`: F ~> T
  
  // defined
  
  override private[psbp] def result[Z]: Z => T[Z] = 

    import `f~>t`.{ apply => `fz=>tz` }

    val computationF: Computation[F] = summon[Computation[F]]
    import computationF.{ result => `z=>fz` }
    
    `z=>fz` andThen `fz=>tz`
    
// private[psbp] trait Transformation[F[+ _]: Computation, T[+ _]] extends Computation[T]:

//   // declared

//   private[psbp] val `f~>t`: F ~> T
  

// private[psbp] given resultingFromTransformation[
//     C[+ _]: Computation]: Transformation[C, ReactiveTransformed[C]] 
//     with Computation[ReactiveTransformed[C]] with   
//   // defined
  
//   override private[psbp] def result[Z]: Z => T[Z] = 

//     import `f~>t`.{ apply => `fz=>tz` }

//     val computationF: Computation[F] = summon[Computation[F]]
//     import computationF.{ result => `z=>fz` }
    
//     `z=>fz` andThen `fz=>tz`    
