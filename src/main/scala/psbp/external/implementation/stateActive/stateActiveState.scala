package psbp.implementation.stateActive

import psbp.specification.program.state.State

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.implementation.computation.transformation.stateTransformedState

import psbp.implementation.active.Active

import psbp.implementation.active.{ activeResulting, activeBinding }

given stateActiveState[S]: State[S, `=>SA`[S]] = stateTransformedState[S, Active]

