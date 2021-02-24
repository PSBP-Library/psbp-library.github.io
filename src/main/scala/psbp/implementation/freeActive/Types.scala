package psbp.implementation.freeActive

import psbp.internalSpecification.computation.transformation.FreeTransformed

import psbp.implementation.active.Active

type FreeActive[+Y] = FreeTransformed[Active][Y] 

type `=>FA`[-Z, +Y] = Z => FreeActive[Y]