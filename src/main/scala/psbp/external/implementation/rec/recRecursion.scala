package psbp.external.implementation.rec

import scala.language.postfixOps

import psbp.external.specification.program.Program

import psbp.external.specification.recursion.Recursion

given recRecursion[>-->[- _, + _]: Program]: Recursion[Rec, >-->] with
  override private[psbp] def `r[a]>-->a[r[a]]`[A[+ _]]: Rec[A] >--> A[Rec[A]] =
    `rec[a]=>a[rec[a]]` asProgram
  override private[psbp] def `a[r[a]]>-->r[a]`[A[+ _]]: A[Rec[A]] >--> Rec[A] =
    `a[rec[a]]=>rec[a]` asProgram