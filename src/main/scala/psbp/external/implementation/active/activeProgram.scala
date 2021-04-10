package psbp.external.implementation.active

import psbp.external.specification.program.Program

import psbp.external.implementation.computation.programFromComputation

import psbp.internal.specification.computation.computationFromResultingAndBinding

given activeProgram: Program[`=>A`] = programFromComputation[Active]