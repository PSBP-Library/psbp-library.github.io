package psbp.external.implementation.active

import psbp.external.specification.program.Program

import psbp.internal.specification.computation.Computation

import psbp.external.implementation.computation.givenProgramFromComputation

private[psbp] given givenActiveComputation: Computation[Active] with

  private[psbp] def result[Z]: Z => Active[Z] =
    z =>
      z

  private[psbp] def bind[Z, Y](
    cz: Active[Z]
    , `z=>cy`: => Z => Active[Y]
  ): Active[Y] =
    `z=>cy`(cz)

given givenActiveProgram: Program[`=>A`] = givenProgramFromComputation[Active]