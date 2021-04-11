package psbp.external.implementation.stateActive

import psbp.external.specification.program.state.State

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  givenActiveResulting
  , givenActiveBinding 
}

import psbp.internal.specification.computation.givenComputationFromResultingAndBinding

import psbp.internal.implementation.computation.transformation.stateTransformedState

given givenStateActiveState[S]: State[S, `=>SA`[S]] = stateTransformedState[S, Active]

