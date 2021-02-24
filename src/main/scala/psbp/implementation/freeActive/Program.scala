package psbp.implementation.freeActive

import psbp.specification.program.Program

import psbp.internalSpecification.computation.Computation

import psbp.implementation.active.Active

import psbp.implementation.active.given

import psbp.internalSpecification.computation.transformation.given // freeTransformedComputation

given Computation[FreeActive] = freeTransformedComputation[Active]

import psbp.internalSpecification.computation.given // programFromComputation

given Program[`=>FA`] = programFromComputation[FreeActive]
