package psbp.implementation.reactive

import psbp.internalSpecification.computation.transformation.ReactiveTransformed

import psbp.implementation.active.Active

type Reactive[+Y] = ReactiveTransformed[Active][Y] 

type `=>R`[-Z, +Y] = Z => Reactive[Y]

// package psbp.implementation.reactive.types

// type Reactive[+Y] = (Y => Unit) => Unit

// type `=>R`[-Z, +Y] = Z => Reactive[Y]