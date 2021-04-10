package psbp.external.specification.structure.recursive

import scala.language.postfixOps

import psbp.external.specification.types.&&

import psbp.external.specification.program.Classification

import psbp.external.specification.structure.Traversable

import psbp.external.specification.recursion.Recursion

import psbp.external.specification.recursion.RecursiveFoldingAndUnfolding

private[psbp] given recursiveFoldingAndUnfoldingFromTraversable[
  A[+ _]: [A[+ _]] =>> Traversable[A, >-->]
  , >-->[- _, + _]: Classification
]: RecursiveFoldingAndUnfolding[A, >-->] with
  
  val traversable: Traversable[A, >-->] =
    summon[Traversable[A, >-->]]
  import traversable.traverse  
  
  // defined

  private[psbp] def recursiveFold[
    Y 
    , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  ]: (A[Y] >--> Y) => (R[A] >--> Y) =
    `a[y]>-->y` =>
      val recursion: Recursion[R, >-->] = 
        summon[Recursion[R, >-->]]
      import recursion.`r[a]>-->a[r[a]]`

      lazy val `a[r[a]]>-->a[y]` = (recursiveFold andThen traverse)(`a[y]>-->y`)

      `r[a]>-->a[r[a]]` >--> `a[r[a]]>-->a[y]` >--> `a[y]>-->y`

  private[psbp] def recursiveUnfold[
    Z
    , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  ]: (Z >--> A[Z]) => (Z >--> R[A]) =
    `z>-->a[z]` =>
      val recursion: Recursion[R, >-->] = 
        summon[Recursion[R, >-->]]
      import recursion.`a[r[a]]>-->r[a]`

      lazy val `a[z]>-->a[r[a]]` = (recursiveUnfold andThen traverse)(`z>-->a[z]`)

      `z>-->a[z]` >--> `a[z]>-->a[r[a]]` >--> `a[r[a]]>-->r[a]`

  private[psbp] def recursiveUnfoldAndThenFold[
    Z, Y
    , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  ]: ((Z >--> A[Z]) && (A[Y] >--> Y)) => (Z >--> Y) =
    (`z>-->a[z]`, `a[y]>-->y`) =>

      val `z=>r[a]` = recursiveUnfold apply `z>-->a[z]`
      val `r[a]=>y` = recursiveFold apply `a[y]>-->y`

      `z=>r[a]` >--> `r[a]=>y`
