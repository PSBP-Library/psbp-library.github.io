package psbp.implementation.reactive

import psbp.specification.program.Program

import psbp.internalSpecification.computation.Computation

import psbp.implementation.active.Active

import psbp.implementation.active.given

import psbp.internalSpecification.computation.transformation.given // reactiveTransformedComputation

// given Computation[Reactive] with

//   private[psbp] def result[Z]: Z => Reactive[Z] =
//     z => 
//       `z=>u` =>
//         `z=>u`(z)

//   private[psbp] def bind[Z, Y] (cz: Reactive[Z], `z=>cy`: => Z => Reactive[Y]): Reactive[Y] =
//     `y=>u` =>
//       cz { 
//         z =>
//           `z=>cy`(z)(`y=>u`)
//       }

given Computation[Reactive] = reactiveTransformedComputation[Active]

import psbp.internalSpecification.computation.given // programFromComputation

given Program[`=>R`] = programFromComputation[Reactive]