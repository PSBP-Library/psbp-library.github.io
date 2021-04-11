package psbp.external.implementation.reactive

import psbp.external.specification.program.parallel.Parallel

import psbp.internal.specification.computation.givenComputationFromResultingAndBinding

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  givenActiveResulting
  , givenActiveBinding 
}

import psbp.internal.implementation.computation.transformation.reactiveTransformedParallel

given givenReactiveParallel: Parallel[`=>R`] = reactiveTransformedParallel[Active]

