package psbp.implementation.reactive

import psbp.specification.program.parallel.Parallel

import psbp.internalImplementation.computation.transformation.reactiveTransformedParallel

import psbp.implementation.active.Active

import psbp.implementation.active.activeComputation

given reactiveParallel: Parallel[`=>R`] = reactiveTransformedParallel[Active]

