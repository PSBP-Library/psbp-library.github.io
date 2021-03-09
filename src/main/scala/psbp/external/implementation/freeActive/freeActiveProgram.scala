package psbp.implementation.freeActive

import psbp.specification.program.Program

import psbp.internal.specification.computation.Computation

import psbp.internal.specification.computation.programFromComputation

import psbp.internal.implementation.computation.transformation.freeTransformedComputation

import psbp.implementation.active.Active

given freeActiveComputation: Computation[FreeActive] = freeTransformedComputation[Active]

given freeActiveProgram: Program[`=>FA`] = programFromComputation[FreeActive]
