package psbp.external.implementation.reactive

import psbp.external.specification.program.Program

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  givenActiveResulting
  , givenActiveBinding 
}

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.computation.transformation.Transformation

import psbp.external.implementation.computation.givenProgramFromComputation

import psbp.internal.specification.computation.givenComputationFromResultingAndBinding

import psbp.internal.specification.computation.transformation.givenResultingFromTransformation

import psbp.internal.implementation.computation.transformation.reactiveTransformedTransformation

import psbp.internal.implementation.computation.transformation.reactiveTransformedBinding

given givenReactiveTransformation: Transformation[Active, Reactive] = reactiveTransformedTransformation[Active]

given givenReactiveBinding: Binding[Reactive] = reactiveTransformedBinding[Active]

given givenReactiveProgram: Program[`=>R`] = givenProgramFromComputation[Reactive]
