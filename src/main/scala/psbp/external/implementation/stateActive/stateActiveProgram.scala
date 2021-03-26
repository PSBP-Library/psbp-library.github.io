package psbp.implementation.stateActive

import psbp.external.specifcation.program.Program

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.computation.programFromComputation

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.specification.computation.transformation.resultingFromTransformation

import psbp.internal.implementation.computation.transformation.stateTransformedBinding

import psbp.implementation.active.Active

import psbp.implementation.active.{ activeResulting, activeBinding }

given stateActiveBinding[S]: Binding[StateActive[S]] = stateTransformedBinding[S, Active]

given stateActiveProgram[S]: Program[`=>SA`[S]] = programFromComputation[StateActive[S]]
