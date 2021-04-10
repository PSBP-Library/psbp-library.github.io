package psbp.external.implementation.stateActive

import psbp.external.specification.program.Program

import psbp.external.implementation.computation.programFromComputation

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  activeResulting
  , activeBinding
}

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.specification.computation.transformation.resultingFromTransformation

import psbp.internal.implementation.computation.transformation.stateTransformedBinding

given stateActiveBinding[S]: Binding[StateActive[S]] = stateTransformedBinding[S, Active]

given stateActiveProgram[S]: Program[`=>SA`[S]] = programFromComputation[StateActive[S]]
