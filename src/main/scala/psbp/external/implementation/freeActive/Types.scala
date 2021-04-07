package psbp.external.implementation.freeActive

import psbp.internal.implementation.computation.transformation.FreeTransformed

import psbp.external.implementation.active.Active

type FreeActive = [Y] =>> FreeTransformed[Active][Y] 

type `=>FA`= [Z, Y] =>> Z => FreeActive[Y]