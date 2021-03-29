package psbp.implementation.active

import psbp.external.specification.program.Program

import psbp.internal.specification.computation.programFromComputation

import psbp.internal.specification.computation.computationFromResultingAndBinding

given activeProgram: Program[`=>A`] = programFromComputation[Active]