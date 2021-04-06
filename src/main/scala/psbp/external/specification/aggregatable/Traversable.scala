package psbp.external.specification.aggregatable

import scala.language.postfixOps

import psbp.external.specification.types.&&

import psbp.external.specification.program.Classification

import psbp.external.specification.recursion.Recursion

private[psbp] trait Traversable[A[+ _], >-->[- _, + _]: Classification]:

  // declared

  private[psbp] def traverse[Z, Y]: (Z >--> Y) => A[Z] >--> A[Y]

  // defined

  private[psbp] def fold[
    Y 
    , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  ]: (A[Y] >--> Y) => (R[A] >--> Y) =
    `a[y]>-->y` =>
      val recursion: Recursion[R, >-->] = summon[Recursion[R, >-->]]
      import recursion.`r[a]>-->a[r[a]]`

      lazy val `a[r[a]]>-->a[y]` = (fold andThen traverse)(`a[y]>-->y`)

      `r[a]>-->a[r[a]]` >--> `a[r[a]]>-->a[y]` >--> `a[y]>-->y`

  private[psbp] def unfold[
    Z
    , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  ]: (Z >--> A[Z]) => (Z >--> R[A]) =
    `z>-->a[z]` =>
      val recursion: Recursion[R, >-->] = summon[Recursion[R, >-->]]
      import recursion.`a[r[a]]>-->r[a]`

      lazy val `a[z]>-->a[r[a]]` = (unfold andThen traverse)(`z>-->a[z]`)

      `z>-->a[z]` >--> `a[z]>-->a[r[a]]` >--> `a[r[a]]>-->r[a]`

  private[psbp] def unfoldAndThenFold[
    Z
    , Y
    , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  ]: ((Z >--> A[Z]) && (A[Y] >--> Y)) => (Z >--> Y) =
    (`z>-->a[z]`, `a[y]>-->y`) =>

      val `z=>r[a]` = unfold apply `z>-->a[z]`
      val `r[a]=>y` = fold apply `a[y]>-->y`

      `z=>r[a]` >--> `r[a]=>y`
        
