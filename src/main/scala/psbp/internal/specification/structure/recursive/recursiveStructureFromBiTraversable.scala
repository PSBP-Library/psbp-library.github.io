package psbp.internal.specification.structure.recursive

import scala.annotation.targetName

import scala.language.postfixOps

import psbp.external.specification.types.&&

import psbp.external.specification.program.Classification

import psbp.external.specification.structure.Traversable

import psbp.external.specification.recursion.Recursion

import psbp.external.specification.structure.recursive.RecursiveStructure

import psbp.internal.specification.structure.BiTraversable

private[psbp] given recursiveStructureFromBiTraversable[
  A[+ _, + _]: [A[+ _, + _]] =>> BiTraversable[A, >-->]
  , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Classification
] : RecursiveStructure[A, R, >-->] with

  val recursion: Recursion[R, >-->] = 
    summon[Recursion[R, >-->]]
  import recursion.{ 
    `a[r[a]]>-->r[a]`
    , `r[a]>-->a[r[a]]`
  }

  val biTraversableAtLeft: BiTraversable[A, >-->] =
    summon[BiTraversable[A, >-->]]

  override private[psbp] def initialFolder[Y]: Folder[Y, R[[X] =>> A[Y, X]]] =
    `a[r[a]]>-->r[a]`

  override private[psbp] def initialUnfolder[Y]: Unfolder[R[[X] =>> A[Y, X]], Y] =
    `r[a]>-->a[r[a]]`  

  @targetName("fuseFolder")
  override private[psbp] def fuse[Z, Y, X]: (Z >--> Y && Folder[Y, X]) => Folder[Z, X] =
    (`z>-->y`, `a[y,x]>-->x`) =>
      val `a[z,x]>-->a[y,x]`: A[Z, X] >--> A[Y, X] = 
        biTraversableAtLeft.leftTraversable.traverse(`z>-->y`) 
      `a[z,x]>-->a[y,x]`>-->`a[y,x]>-->x`  
      
  @targetName("fuseUnfolder")
  override private[psbp] def fuse[X, Y, Z]: (Unfolder[X, Y] && Y >--> Z) => Unfolder[X, Z] =
    (`x>-->a[y,x]`, `y>-->z`) =>
      val `a[y,x]>-->a[z,x]`: A[Y, X] >--> A[Z, X] = 
        biTraversableAtLeft.leftTraversable.traverse(`y>-->z`) 
      `x>-->a[y,x]`>-->`a[y,x]>-->a[z,x]` 

  override def fold[Y, X]: Folder[Y, X] => R[[X] =>> A[Y, X]] >--> X =
    `a[y,x]>-->x` =>
      given traversable: Traversable[[X] =>> A[Y, X], >-->] = 
        biTraversableAtLeft.rightTraversable
      import psbp.external.specification.structure.recursive.{
        recursiveFoldingAndUnfoldingFromTraversable => recursiveFoldingAndUnfolding
      }  
      recursiveFoldingAndUnfolding.recursiveFold apply `a[y,x]>-->x` 

  override def unfold[X, Y]: Unfolder[X, Y] => X >--> R[[X] =>> A[Y, X]] =
    `x>-->a[y,x]` =>
      given traversable: Traversable[[X] =>> A[Y, X], >-->] = 
        biTraversableAtLeft.rightTraversable
      import psbp.external.specification.structure.recursive.{
        recursiveFoldingAndUnfoldingFromTraversable => recursiveFoldingAndUnfolding
      }  
      recursiveFoldingAndUnfolding.recursiveUnfold apply `x>-->a[y,x]` 
