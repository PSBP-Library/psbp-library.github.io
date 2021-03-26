package psbp.internal.specification.rec

import psbp.external.specifcation.types.Rec

import psbp.internal.specification.lifting.Function1Lifting

private[psbp] def rec[A[+ _]: Function1Lifting, X]: (A[X] => X) => Rec[A] => X =

  val function1Lifting = summon[Function1Lifting[A]]
  import function1Lifting.lift1
  
  `a[x]=>x` =>
    `rec[a]` =>
      (lift1(rec apply `a[x]=>x`) andThen `a[x]=>x`)(`rec[a]`.`a[rec[a]]`)
