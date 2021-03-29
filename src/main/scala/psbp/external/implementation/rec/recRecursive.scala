package psbp.external.implementation.rec

import scala.language.postfixOps

import psbp.external.specification.program.Program

import psbp.external.specification.recursive.Recursive

given recRecursive[>-->[- _, + _]: Program]: Recursive[Rec, >-->] with
  override private[psbp] def `r[a]>-->a[r[a]]`[A[+ _]]: Rec[A] >--> A[Rec[A]] =
    `rec[a]=>a[rec[a]]` asProgram
  override private[psbp] def `a[r[a]]>-->r[a]`[A[+ _]]: A[Rec[A]] >--> Rec[A] =
    `a[rec[a]]=>rec[a]` asProgram