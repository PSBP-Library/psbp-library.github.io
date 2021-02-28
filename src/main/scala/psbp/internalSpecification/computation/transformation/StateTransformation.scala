package psbp.internalSpecification.computation.transformation

import psbp.specification.state.State

import psbp.internalSpecification.naturalTransformation.~>

import psbp.internalSpecification.computation.Computation

private[psbp] given stateTransformedComputation[
  S,
  C[+ _]: Computation]: Transformation[C, StateTransformed[S, C]] 
  with Computation[[Z] =>> StateTransformed[S, C][Z]]
  with State[S, [Z, Y] =>> Z => StateTransformed[S, C][Y]] with 

  private type F[+Z] = C[Z]
  private type T[+Z] = StateTransformed[S, C][Z]

  private type `=>T` = [Z, Y] =>> Z => StateTransformed[S, C][Y]

  private val computationF: Computation[F] = summon[Computation[F]]
  import computationF.{ result => resultF, bind => bindF }

  override private[psbp] val `f~>t`: F ~> T = new {
    def apply[Z]: F[Z] => T[Z] =
      fz =>
        s =>
          bindF(fz, z => resultF((s, z)))
  }

  override private[psbp] def bind[Z, Y] (tz: T[Z], `z=>ty`: => Z => T[Y]): T[Y] =
    s =>
      bindF(tz(s), (s, z) => `z=>ty`(z)(s))    

  override def `u>-->s`: Unit `=>T` S =
    _ => 
      s =>
        resultF((s, s))

  override def `s>-->u`: S `=>T` Unit =
    s => 
      _ =>
        resultF((s, ()))

      