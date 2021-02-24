package psbp.internalSpecification.computation.transformation

import psbp.internalSpecification.naturalTransformation.~>

import psbp.internalSpecification.computation.Computation

private[psbp] given reactiveTransformedComputation[
  C[+ _]: Computation]: Transformation[C, ReactiveTransformed[C]] 
  with Computation[ReactiveTransformed[C]] with 

    private type F[+Z] = C[Z]
    private type T[+Z] = ReactiveTransformed[F][Z]
  
    private val computationF: Computation[F] = summon[Computation[F]]
    import computationF.{ result => resultF, bind => bindF }
    
    override private[psbp] val `f~>t`: F ~> T = new {
      def apply[Z]: F[Z] => T[Z] =
        fz => 
          `fz=>u` =>
            `fz=>u`(fz)
      }
  
    override private[psbp] def bind[Z, Y] (tz: T[Z], `z=>ty` : => Z => T[Y]): T[Y] =
      `fy=>u` =>
        tz { 
          fz =>
            bindF(fz, { z =>
              resultF(`z=>ty`(z)(`fy=>u`))
            })
        }
