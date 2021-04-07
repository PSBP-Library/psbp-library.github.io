package psbp.external.implementation.reactive

import psbp.external.specification.program.Program

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.computation.programFromComputation

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.specification.computation.transformation.resultingFromTransformation

import psbp.internal.implementation.computation.transformation.reactiveTransformedBinding

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ activeResulting, activeBinding }

given reactiveBinding: Binding[Reactive] = reactiveTransformedBinding[Active]

given reactiveProgram: Program[`=>R`] = programFromComputation[Reactive]
