package psbp.external.specification.recursion

import psbp.external.specification.program.Program

trait Recursion[R[+_[+ _]], >-->[- _, + _]: Program]:
  private[psbp] def `r[a]>-->a[r[a]]`[A[+ _]]: R[A] >--> A[R[A]]
  private[psbp] def `a[r[a]]>-->r[a]`[A[+ _]]: A[R[A]] >--> R[A]