package psbp.external.implementation.readingActive

import psbp.external.specification.program.reading.Reading

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.givenActiveComputation

import psbp.internal.implementation.computation.transformation.readingTransformedReading

given givenReadingActiveReading[R]: Reading[R, `=>RA`[R]] = readingTransformedReading[R, Active]

