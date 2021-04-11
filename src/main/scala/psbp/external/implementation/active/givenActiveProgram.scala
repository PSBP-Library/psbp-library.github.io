package psbp.external.implementation.active

import psbp.external.specification.program.Program

import psbp.external.implementation.computation.givenProgramFromComputation

import psbp.internal.specification.computation.givenComputationFromResultingAndBinding

given givenActiveProgram: Program[`=>A`] = givenProgramFromComputation[Active]