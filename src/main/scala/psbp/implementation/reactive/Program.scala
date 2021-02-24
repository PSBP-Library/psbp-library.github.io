package psbp.implementation.reactive

import psbp.specification.program.Program

import psbp.internalSpecification.computation.Computation

import psbp.implementation.active.Active

import psbp.implementation.active.given

import psbp.internalSpecification.computation.transformation.given // reactiveTransformedComputation

given Computation[Reactive] = reactiveTransformedComputation[Active]

import psbp.internalSpecification.computation.given // programFromComputation

given Program[`=>R`] = programFromComputation[Reactive]