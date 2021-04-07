package psbp.external.implementation.stateActive

import psbp.internal.implementation.computation.transformation.StateTransformed

import psbp.external.implementation.active.Active

type StateActive[S] = [Y] =>> StateTransformed[S, Active][Y] 

type `=>SA`[S] = [Z, Y] =>> Z => StateActive[S][Y]