// package psbp.internal.specification.computation

// import psbp.external.specification.types.&&

// import psbp.internal.specification.computation.Computation

// import psbp.internal.specification.lifting.FunctionApplicationLifting

// private[psbp] given functionApplicationLiftingFromComputation[C[+ _]: Computation]: FunctionApplicationLifting[C] with

//   private val computation: Computation[C] = summon[Computation[C]]
//   import computation. { result, bind }

//   // defined

//   private[psbp] def liftValue[Z]: Z => C[Z] = 
//     z => 
//       result(z)

//   private[psbp] def liftProduct2[Z, Y]: (C[Z] && C[Y]) => C[Z && Y] =
//     (cz, cy) =>
//       bind(cz, { z => 
//         bind(cy, { y => 
//           result(z, y) }) })

//   private[psbp] def liftApply[Z, Y]: (C[Z => Y] && C[Z]) => C[Y] =
//     (cz2y, cz) =>
//       bind(cz2y, { z2y => 
//         bind(cz, { z => 
//           result(z2y(z)) }) })
