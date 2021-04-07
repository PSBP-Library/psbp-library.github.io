package psbp.internal.specification.aggregatable.recursive

import scala.language.postfixOps

import psbp.external.specification.types.&&

import psbp.external.specification.program.Program

import psbp.external.specification.program.Classification

import psbp.internal.specification.aggregatable.BiTraversable

import psbp.external.specification.recursion.Recursion

import psbp.external.specification.aggregatable.recursive.RecursivelyAggregatable

import psbp.external.specification.aggregatable.recursive.RecursiveFolderType

import psbp.external.specification.aggregatable.Aggregatable

import psbp.external.specification.aggregatable.recursive.Recursive

private[psbp] given recursivelyAggregatableFromBiTraversable[
  A[+ _, + _]: [A[+ _, + _]] =>> BiTraversable[A, >-->]
  , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Classification
  ] : RecursivelyAggregatable[A, R, >-->] with

  val classification: Classification[>-->] = summon[Classification[>-->]]
  import classification.identity

  val recursion: Recursion[R, >-->] = summon[Recursion[R, >-->]]
  import recursion.{ `a[r[a]]>-->r[a]`, `r[a]>-->a[r[a]]` }

  override private[psbp] def initialTraverser[Y]: Y >--> Y = 
    identity

  override private[psbp] def initialFolder[Y]: Folder[Y, R[[X] =>> A[Y, X]]] =
    `a[r[a]]>-->r[a]`

  override private[psbp] def initialUnfolder[Y]: Unfolder[R[[X] =>> A[Y, X]], Y] =
    `r[a]>-->a[r[a]]`  

  override private[psbp] def fuseFolder[Z, Y, X]: (Z >--> Y && Folder[Y, X]) => Folder[Z, X] =
    (`z>-->y`, `a[y,x]>-->x`) =>
      val biTraversableAtLeft: BiTraversable[A, >-->] = summon[BiTraversable[A, >-->]]
      val `a[z,x]>-->a[y,x]`: A[Z, X] >--> A[Y, X] = biTraversableAtLeft.leftTraversable.traverse(`z>-->y`) 
      `a[z,x]>-->a[y,x]`>-->`a[y,x]>-->x`  
      
  override private[psbp] def fuseUnfolder[X, Y, Z]: (Unfolder[X, Y] && Y >--> Z) => Unfolder[X, Z] =
    (`x>-->a[y,x]`, `y>-->z`) =>
      val biTraversableAtLeft: BiTraversable[A, >-->] = summon[BiTraversable[A, >-->]]
      val `a[y,x]>-->a[z,x]`: A[Y, X] >--> A[Z, X] = biTraversableAtLeft.leftTraversable.traverse(`y>-->z`) 
      `x>-->a[y,x]`>-->`a[y,x]>-->a[z,x]` 

  override def fold[Y, X]: Folder[Y, X] => R[[X] =>> A[Y, X]] >--> X =
    `a[y,x]>-->x` =>
      val biTraversableAtLeft: BiTraversable[A, >-->] = summon[BiTraversable[A, >-->]]
      val `(a[y,x]>-->x)=>(r[[x]=>>a[y,x]]>-->x)`: (A[Y, X] >--> X) => (R[[X] =>> A[Y, X]] >--> X) = 
        biTraversableAtLeft.rightTraversable.fold 
      `(a[y,x]>-->x)=>(r[[x]=>>a[y,x]]>-->x)`apply`a[y,x]>-->x` 

  override def unfold[X, Y]: Unfolder[X, Y] => X >--> R[[X] =>> A[Y, X]] =
    `x>-->a[y,x]` =>
      val biTraversableAtLeft: BiTraversable[A, >-->] = summon[BiTraversable[A, >-->]]
      val `(x>-->a[y,x])=>(x>-->r[[x]=>>a[y,x]])`: (X >--> A[Y, X]) => (X >--> R[[X] =>> A[Y, X]]) = 
        biTraversableAtLeft.rightTraversable.unfold
      `(x>-->a[y,x])=>(x>-->r[[x]=>>a[y,x]])`apply`x>-->a[y,x]` 
