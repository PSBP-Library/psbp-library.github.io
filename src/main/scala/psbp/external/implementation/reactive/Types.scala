package psbp.implementation.reactive

import psbp.internal.implementation.computation.transformation.ReactiveTransformed

import psbp.implementation.active.Active

type Reactive = [Y] =>> ReactiveTransformed[Active][Y] 

type `=>R` = [Z, Y] =>> Z => Reactive[Y]

// type Reactive = [Y] =>> (Y => Unit) => Unit

// type `=>R` = [Z, Y] =>> Z => Reactive[Y]