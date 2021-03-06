package psbp.implementation.stateActive

import psbp.specification.program.Program

import psbp.internal.specification.computation.Computation

import psbp.implementation.programFromComputation

import psbp.internal.implementation.computation.transformation.stateTransformedComputation

import psbp.implementation.active.Active

import psbp.implementation.active.activeComputation

given stateActiveComputation[S]: Computation[StateActive[S]] = stateTransformedComputation[S, Active]

given stateActiveProgram[S]: Program[`=>SA`[S]] = programFromComputation[StateActive[S]]

