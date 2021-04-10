package psbp.external.implementation.reactive

import psbp.external.specification.program.parallel.Parallel

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  activeResulting
  , activeBinding 
}

import psbp.internal.implementation.computation.transformation.reactiveTransformedParallel

given reactiveParallel: Parallel[`=>R`] = reactiveTransformedParallel[Active]

