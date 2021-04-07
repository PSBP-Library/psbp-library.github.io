package psbp.external.implementation.reactive

import psbp.internal.implementation.computation.transformation.ReactiveTransformed

import psbp.external.implementation.active.Active

type Reactive = [Y] =>> ReactiveTransformed[Active][Y] 

type `=>R` = [Z, Y] =>> Z => Reactive[Y]
