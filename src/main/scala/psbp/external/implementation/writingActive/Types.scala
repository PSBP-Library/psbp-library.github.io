package psbp.external.implementation.writingActive

import psbp.internal.implementation.computation.transformation.WritingTransformed

import psbp.external.implementation.active.Active

type WritingActive[W] = [Y] =>> WritingTransformed[W, Active][Y] 

type `=>WA`[W] = [Z, Y] =>> Z => WritingActive[W][Y]