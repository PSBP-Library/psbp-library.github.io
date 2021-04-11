package psbp.external.implementation.readingActive

import psbp.internal.implementation.computation.transformation.ReadingTransformed

import psbp.external.implementation.active.Active

type ReadingActive[R] = [Y] =>> ReadingTransformed[R, Active][Y] 

type `=>RA`[R] = [Z, Y] =>> Z => ReadingActive[R][Y]