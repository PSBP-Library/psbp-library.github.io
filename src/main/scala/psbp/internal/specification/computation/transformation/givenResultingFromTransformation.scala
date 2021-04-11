package psbp.internal.specification.computation.transformation

import psbp.internal.specification.computation.Resulting

import psbp.internal.specification.naturalTransformation.~>
  
private[psbp] given givenResultingFromTransformation[
  F[+ _]: Resulting
  , T[+ _]: [T[+ _]] =>> Transformation[F, T]
]: Resulting[T] with   

  private val transformation: Transformation[F, T] = 
    summon[Transformation[F, T]]
  import transformation.`f~>t`

  // defined
  
  override private[psbp] def result[Z]: Z => T[Z] = 

    import `f~>t`.{ apply => `fz=>tz` }

    val resultingF: Resulting[F] = 
      summon[Resulting[F]]
    import resultingF.{ 
      result => `z=>fz` 
    }
    
    `z=>fz` andThen `fz=>tz`  