// package psbp.internal.implementation.computation.transformation

// // import psbp.internal.specification.computation.Binding

// import psbp.internal.specification.computation.Computation

// import psbp.internal.specification.naturalTransformation.~>

// import psbp.internal.specification.computation.transformation.Transformation

// private[psbp] given readingTransformedTransformation[
//   R
//   , C[+ _]: Computation
// ]: Transformation[C, ReadingTransformed[R, C]] with 
//   // Binding[[Y] =>> ReadingTransformed[R, C][Y]] with

//   private type F[+Y] = C[Y]
//   private type T[+Y] = ReadingTransformed[R, C][Y]

//   private type `=>T` = [Z, Y] =>> Z => T[Y]

//   private val computationF: Computation[F] = 
//     summon[Computation[F]]
//   import computationF.{ 
//     result => resultF
//     , bind => bindF
//   }

//   override private[psbp] val `f~>t`: F ~> T = new {
//     def apply[Z]: F[Z] => T[Z] =
//       fz =>
//         // fz
//         bindF(fz, z => resultF(z))
//   }  

//   // override private[psbp] def bind[Z, Y](tz: T[Z], `z>=ty`: => Z => T[Y]): T[Y] =
//   //   bindF(tz, z => `z>=ty`(z) )
       