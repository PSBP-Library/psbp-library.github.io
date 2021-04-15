package psbp.external.implementation.stateActive

import psbp.external.specification.program.Program

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.givenActiveComputation

import psbp.internal.specification.computation.Computation

import psbp.external.implementation.computation.givenProgramFromComputation

import psbp.internal.implementation.computation.transformation.stateTransformedComputation

private[psbp] given givenStateActiveComputation[S]: Computation[StateActive[S]] = 
  stateTransformedComputation[S, Active]

given givenStateActiveProgram[S]: Program[`=>SA`[S]] = 
  givenProgramFromComputation[StateActive[S]]
