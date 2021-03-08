package psbp.implementation.reactive

import psbp.specification.program.Program

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.computation.programFromComputation

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.specification.computation.transformation.resultingFromTransformation

import psbp.internal.implementation.computation.transformation.reactiveTransformedBinding

import psbp.implementation.active.Active

import psbp.implementation.active.{ activeResulting, activeBinding }

given Binding[Reactive] = reactiveTransformedBinding[Active]

given Program[`=>R`] = programFromComputation[Reactive]
