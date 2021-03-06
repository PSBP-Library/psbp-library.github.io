package psbp.implementation.reactive

import psbp.specification.program.parallel.Parallel

import psbp.internal.implementation.computation.transformation.reactiveTransformedParallel

import psbp.implementation.active.Active

import psbp.implementation.active.activeComputation

given reactiveParallel: Parallel[`=>R`] = reactiveTransformedParallel[Active]

