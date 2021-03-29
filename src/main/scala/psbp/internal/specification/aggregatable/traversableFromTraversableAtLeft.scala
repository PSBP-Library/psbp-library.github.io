// package psbp.internal.specification.aggregatable

// import psbp.external.specification.program.Program

// import psbp.external.specification.aggregatable.Traversable

// private[psbp] given traversableFromTraversableAtLeft[X, A[+ _, + _]: [A[+ _, + _]] =>> TraversableAtLeft[A, >-->], >-->[- _, + _]: Program]: Traversable[[Z] =>> A[Z, X], >-->] with

//   private val traversableAtLeft: TraversableAtLeft[A, >-->] = summon[TraversableAtLeft[A, >-->]]
//   import traversableAtLeft.leftTraversable

//   def traverse[Z, Y]: (Z >--> Y) => (A[Z, X] >--> A[Y, X]) =
//     leftTraversable.traverse    

//   // override private[psbp] def rec[Y]: (A[Y, X] >--> Y) => (Rec[[Y] =>> A[Y, X]] >--> Y) =
//   //   leftTraversable.rec