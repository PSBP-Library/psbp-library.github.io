package psbp.external.implementation.stateActive

import psbp.external.specification.program.state.State

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ activeResulting, activeBinding }

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.implementation.computation.transformation.stateTransformedState

given stateActiveState[S]: State[S, `=>SA`[S]] = stateTransformedState[S, Active]

