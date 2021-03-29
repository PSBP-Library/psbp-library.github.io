package psbp.implementation.reactive

import psbp.external.specification.program.parallel.Parallel

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.implementation.computation.transformation.reactiveTransformedParallel

import psbp.implementation.active.Active

import psbp.implementation.active.{ activeResulting, activeBinding }

given reactiveParallel: Parallel[`=>R`] = reactiveTransformedParallel[Active]

