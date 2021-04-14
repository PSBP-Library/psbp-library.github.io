package psbp.external.implementation.reactive

import psbp.external.specification.program.Program

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.givenActiveComputation

import psbp.internal.specification.computation.Computation

import psbp.external.implementation.computation.givenProgramFromComputation

import psbp.internal.implementation.computation.transformation.reactiveTransformedComputation

given givenReactiveComputation: Computation[Reactive] = reactiveTransformedComputation[Active]

given givenReactiveProgram: Program[`=>R`] = givenProgramFromComputation[Reactive]
