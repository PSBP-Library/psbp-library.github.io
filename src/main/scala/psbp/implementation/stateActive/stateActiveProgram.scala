package psbp.implementation.stateActive

import psbp.specification.program.Program

import psbp.internalSpecification.computation.Computation

import psbp.internalImplementation.computation.programFromComputation

import psbp.internalImplementation.computation.transformation.stateTransformedComputation

import psbp.implementation.active.Active

import psbp.implementation.active.activeComputation

given stateActiveComputation[S]: Computation[StateActive[S]] = stateTransformedComputation[S, Active]

given stateActiveProgram[S]: Program[`=>SA`[S]] = programFromComputation[StateActive[S]]

