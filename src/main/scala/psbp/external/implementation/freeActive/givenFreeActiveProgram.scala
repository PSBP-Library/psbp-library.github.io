package psbp.external.implementation.freeActive

import psbp.external.specification.program.Program

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.givenActiveComputation

import psbp.external.implementation.computation.givenProgramFromComputation

import psbp.internal.specification.computation.Computation

import psbp.internal.implementation.computation.transformation.freeTransformedComputation

private[psbp] given givenFreeActiveComputation: Computation[FreeActive] = freeTransformedComputation[Active]

given givenFreeActiveProgram: Program[`=>FA`] = givenProgramFromComputation[FreeActive]
