package psbp.implementation.stateActive

import psbp.specification.program.Program

import psbp.specification.state.State

import psbp.specification.programWithState.ProgramWithState

import psbp.internalSpecification.computation.Computation

import psbp.internalSpecification.computation.programFromComputation

import psbp.internalSpecification.computation.transformation.stateTransformedComputation

import psbp.implementation.active.Active

import psbp.implementation.active.activeComputation

given stateActiveComputation[S]: Computation[StateActive[S]] = stateTransformedComputation[S, Active]

given stateActiveState[S]: State[S, `=>SA`[S]] = stateTransformedComputation[S, Active]

given stateActiveProgram[S]: Program[`=>SA`[S]] = programFromComputation[StateActive[S]]

