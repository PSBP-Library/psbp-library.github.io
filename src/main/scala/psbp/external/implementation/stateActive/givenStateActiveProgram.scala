package psbp.external.implementation.stateActive

import psbp.external.specification.program.Program

import psbp.external.implementation.computation.givenProgramFromComputation

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  givenActiveResulting
  , givenActiveBinding
}

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.computation.transformation.Transformation

import psbp.internal.specification.computation.givenComputationFromResultingAndBinding

import psbp.internal.specification.computation.transformation.givenResultingFromTransformation

import psbp.internal.implementation.computation.transformation.stateTransformedTransformation

import psbp.internal.implementation.computation.transformation.stateTransformedBinding

given givenStateTransformation[S]: Transformation[Active, StateActive[S]] = stateTransformedTransformation[S, Active]

given givenStateActiveBinding[S]: Binding[StateActive[S]] = stateTransformedBinding[S, Active]

given givenStateActiveProgram[S]: Program[`=>SA`[S]] = givenProgramFromComputation[StateActive[S]]
