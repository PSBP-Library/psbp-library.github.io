package psbp.external.implementation.rec

case class Rec[+A[+ _]](`a[rec[a]]`: A[Rec[A]])

private[psbp] def `rec[a]=>a[rec[a]]`[A[+ _]]: Rec[A] => A[Rec[A]] = _.`a[rec[a]]`

private[psbp] def `a[rec[a]]=>rec[a]`[A[+ _]]: A[Rec[A]] => Rec[A] = Rec(_)