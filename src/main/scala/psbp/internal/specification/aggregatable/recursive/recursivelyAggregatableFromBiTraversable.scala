package psbp.internal.specification.aggregatable.recursive

import scala.language.postfixOps

import psbp.external.specification.types.&&

import psbp.external.specification.program.Program

import psbp.external.specification.program.Classification

import psbp.internal.specification.aggregatable.BiTraversable

import psbp.external.specification.recursion.Recursion

import psbp.external.specification.aggregatable.recursive.RecursivelyAggregatable

import psbp.external.specification.aggregatable.recursive.RecursiveReducerType

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
  import recursion.{ `a[r[a]]>-->r[a]` }

  override private[psbp] def initialTraverser[Y]: Y >--> Y = 
    identity

  override private[psbp] def initialReducer[Y]: Reducer[Y, R[[X] =>> A[Y, X]]] =
    `a[r[a]]>-->r[a]`

  override private[psbp] def fuseReducer[Z, Y, X]: (Z >--> Y && Reducer[Y, X]) => Reducer[Z, X] =
    (`z>-->y`, `a[y,x]>-->x`) =>
      val biTraversableAtLeft: BiTraversable[A, >-->] = summon[BiTraversable[A, >-->]]
      val `a[z,x]>-->a[y,x]`: A[Z, X] >--> A[Y, X] = biTraversableAtLeft.leftTraversable.traverse(`z>-->y`) 
      `a[z,x]>-->a[y,x]`>-->`a[y,x]>-->x`

  override def reduce[Y, X]: Reducer[Y, X] => R[[X] =>> A[Y, X]] >--> X =
    `a[y,x]>-->x` =>
      val biTraversableAtLeft: BiTraversable[A, >-->] = summon[BiTraversable[A, >-->]]
      val `(a[y,x]>-->x)=>(r[[x]=>>a[y,x]]>-->x)`: (A[Y, X] >--> X) => (R[[X] =>> A[Y, X]] >--> X) = 
        biTraversableAtLeft.rightTraversable.fold 
      `(a[y,x]>-->x)=>(r[[x]=>>a[y,x]]>-->x)`apply`a[y,x]>-->x` 
