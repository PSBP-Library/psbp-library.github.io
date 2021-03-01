package psbp.implementation.active

import psbp.specification.program.Program

import psbp.internalSpecification.computation.Computation

import psbp.internalImplementation.computation.programFromComputation

given activeComputation: Computation[Active] with

  private[psbp] def result[Z]: Z => Active[Z] =
    z =>
      z

  private[psbp] def bind[Z, Y] (cz: Active[Z], `z=>cy`: => Z => Active[Y]): Active[Y] =
    `z=>cy`(cz)

given activeProgram: Program[`=>A`] = programFromComputation[Active]