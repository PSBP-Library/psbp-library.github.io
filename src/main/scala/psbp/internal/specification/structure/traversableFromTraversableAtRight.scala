// package psbp.internal.specification.structure

// import psbp.external.specification.program.Program

// import psbp.external.specification.structure.Traversable

// private[psbp] given traversableFromTraversableAtRight[X, A[+ _, + _]: [A[+ _, + _]] =>> TraversableAtRight[A, >-->], >-->[- _, + _]: Program]: Traversable[[Z] =>> A[X, Z], >-->] with

//   private val traversableAtRight: TraversableAtRight[A, >-->] = summon[TraversableAtRight[A, >-->]]
//   import traversableAtRight.rightTraversable

//   def traverse[Z, Y]: (Z >--> Y) => (A[X, Z] >--> A[X, Y]) =
//     rightTraversable.traverse

//   // override private[psbp] def rec[Y]: (A[X, Y] >--> Y) => (Rec[[Y] =>> A[X, Y]] >--> Y) =
//   //   rightTraversable.rec