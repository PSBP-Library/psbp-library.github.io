package psbp.external.implementation.freeActive

import psbp.external.specification.program.Program

import psbp.external.implementation.active.Active

import psbp.external.implementation.computation.programFromComputation

import psbp.internal.specification.computation.Computation

import psbp.internal.implementation.computation.transformation.freeTransformedComputation

given freeActiveComputation: Computation[FreeActive] = freeTransformedComputation[Active]

given freeActiveProgram: Program[`=>FA`] = programFromComputation[FreeActive]
