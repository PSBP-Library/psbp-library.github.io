package psbp.implementation.active

import psbp.specification.program.Program

import psbp.internal.specification.computation.Resulting

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.computation.Computation

import psbp.internal.specification.computation.programFromComputation

import psbp.internal.specification.computation.computationFromResultingAndBinding

given activeResulting: Resulting[Active] with

  private[psbp] def result[Z]: Z => Active[Z] =
    z =>
      z

given activeBinding: Binding[Active] with

  private[psbp] def bind[Z, Y] (cz: Active[Z], `z=>cy`: => Z => Active[Y]): Active[Y] =
    `z=>cy`(cz)

given activeProgram: Program[`=>A`] = programFromComputation[Active]