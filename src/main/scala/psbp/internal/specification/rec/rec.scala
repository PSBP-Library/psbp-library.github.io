// package psbp.internal.specification.rec

// import psbp.external.specification.types.{ Rec, `rec[a]=>a[rec[a]]` }

// import psbp.internal.specification.lifting.Function1Lifting

// private[psbp] def rec[A[+ _]: Function1Lifting, X]: (A[X] => X) => Rec[A] => X =

//   val function1Lifting = summon[Function1Lifting[A]]
//   import function1Lifting.lift1
  
//   `a[x]=>x` =>
//     `rec[a]=>a[rec[a]]` andThen lift1(rec apply `a[x]=>x`) andThen `a[x]=>x`

  // `a[x]=>x` =>
  //   `rec[a]` =>
  //     (lift1(rec apply `a[x]=>x`) andThen `a[x]=>x`)(`rec[a]`.`a[rec[a]]`)

// import psbp.external.specification.types.{ RightRec }

// import psbp.internal.specification.lifting.Function1LiftingAtRight

// import psbp.internal.specification.lifting.function1LiftingFromFunction1LiftingAtRight

// private[psbp] def rec10[A[+ _, + _]: Function1LiftingAtRight, Y, X]: (A[Y, X] => X) => Rec[[X] =>> A[Y, X]] => X =
//   rec00[[X] =>> A[Y, X], X]

// private[psbp] def rec_[A[+ _, + _]: Function1LiftingAtRight, C[+ _], Y, X]: (A[Y, C[X]] => C[X]) => Rec[[X] =>> A[Y, X]] => C[X] =
//   val foo: (A[Y, C[X]] => C[X]) => Rec[[X] =>> A[Y, X]] => C[X] = rec[[X] =>> A[Y, X], C[X]] // rec10[A, Y, C[X]]  
//   foo

