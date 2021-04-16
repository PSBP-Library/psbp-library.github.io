package psbp.external.implementation.readingWithWritingActive

import psbp.external.specification.types.&&

import psbp.external.specification.program.reading.Readable

import psbp.external.specification.program.writing.Writable

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.writingActive.WritingActive

import psbp.external.implementation.writingActive.{ 
  givenWritingActiveComputation
  , givenWritingActiveMaterialization 
}

import psbp.internal.implementation.materialization.readingTransformedMaterialization

import psbp.internal.implementation.materialization.writingTransformedMaterialization

given givenReadingWithWritingActiveMaterialization[
  R: Readable
  , W: Writable
]: Materialization[
  `=>RWA`[R, W]
  , Unit
  // , R ?=> (W, (W, Unit))
  , (W, (W, Unit))
] = readingTransformedMaterialization[R, WritingActive[W], Unit, (W, Unit)]



//   // private[psbp] given readingTransformedMaterialization[
//   //   R
//   //   , C[+ _]: Computation
//   //           : [C[+ _]] =>> Materialization[[Z, Y] =>> Z => C[Y], Z, Y]
//   //   , Z, Y
//   // ]: Materialization[[Z, Y] =>> Z => ReadingTransformed[R, C][Y], Z, C[Y]] with 


// // val foo = ???

// // given givenReadingWithWritingActiveMaterialization[R, W: Writable]: Materialization[`=>RWA`[R, W], Unit, (W, Unit)] with

// //   // TODO : probably w1+w2 is always right because one of them is `nothing`
// //   // override val materialize: (Unit => ReadingWithWritingActive[R, W][Unit]) => Unit ?=> (W, Unit) =
// //   override val materialize: (Unit `=>RWA`[R, W] Unit) => Unit ?=> (W, Unit) =
// //       `u=>rwau` =>
// //       val (w1, (w2, u)) = readingTransformedMaterialization[R, WritingActive[W], Unit, (W, Unit)].materialize(`u=>rwau`)
// //       (w1+w2, u)
// //       // (w1, u)
// //       // (w2, u)

// /*

// package psbp.external.implementation.readingWithWritingActive

// import psbp.external.specification.types.&&

// import psbp.external.specification.program.writing.Writable

// import psbp.external.specification.materialization.Materialization

// import psbp.external.implementation.writingActive.WritingActive

// import psbp.external.implementation.writingActive.{ 
//   givenWritingActiveComputation
//   , givenWritingActiveMaterialization 
// }

// import psbp.internal.implementation.materialization.readingTransformedMaterialization

// given givenReadingWithWritingActiveMaterialization[R, W: Writable]: Materialization[`=>RWA`[R, W], Unit, (W, Unit)] with

//   // TODO : probably w1+w2 is always right because one of them is `nothing`
//   override val materialize: (Unit => ReadingWithWritingActive[R, W][Unit]) => Unit ?=> (W, Unit) =
//     `u=>rwau` =>
//       val (w1, (w2, u)) = readingTransformedMaterialization[R, WritingActive[W], Unit, (W, Unit)].materialize(`u=>rwau`)
//       (w1+w2, u)
//       // (w1, u)
//       // (w2, u)

// */