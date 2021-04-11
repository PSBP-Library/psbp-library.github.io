package psbp.external.implementation.readingWithWritingActive

import psbp.internal.implementation.computation.transformation.WritingTransformed

import psbp.internal.implementation.computation.transformation.ReadingTransformed

import psbp.external.implementation.active.Active

import psbp.external.implementation.writingActive.WritingActive

type ReadingWithWritingActive[R, W] = [Y] =>> ReadingTransformed[R, WritingActive[W]][Y]

type `=>RWA`[R, W] = [Z, Y] =>> Z => ReadingWithWritingActive[R, W][Y]