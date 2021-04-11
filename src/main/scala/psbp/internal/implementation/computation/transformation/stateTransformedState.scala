package psbp.internal.implementation.computation.transformation

import psbp.external.specification.program.state.State

import psbp.internal.specification.computation.Computation

private[psbp] given stateTransformedState[
  S
  , C[+ _]: Computation
]: State[
  S
  , [Z, Y] =>> Z => StateTransformed[S, C][Y]
] with 

  private type F[+Z] = C[Z]
  private type T[+Z] = StateTransformed[S, C][Z]

  private type `=>T` = [Z, Y] =>> Z => T[Y]

  private val computationF: Computation[F] = 
    summon[Computation[F]]
  import computationF.{ 
    result => resultF
  }

  override private[psbp] def `u>-->s`: Unit `=>T` S =
    _ => 
      val s = summon[S]
      resultF((s, s))

  override private[psbp] def `s>-->u`: S `=>T` Unit =
    s =>
      given gs: S = s
        resultF((gs, ()))

      