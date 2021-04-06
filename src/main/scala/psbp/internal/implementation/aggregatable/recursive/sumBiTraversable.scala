package psbp.internal.implementation.aggregatable.recursive

import scala.language.postfixOps

import psbp.external.specification.program.Program

import psbp.external.specification.aggregatable.{ Traversable }

import psbp.internal.specification.aggregatable.{ BiTraversable }

given sumBiTraversable[
  L[+ _, + _]: [L[+ _, + _]] =>> BiTraversable[L, >-->], 
  R[+ _, + _]: [R[+ _, + _]] =>> BiTraversable[R, >-->],
  >-->[- _, + _]: Program]: BiTraversable[Sum[L, R], >-->] with

  private val leftBiTraversable = summon[BiTraversable[L, >-->]]
  private val rightBiTraversable = summon[BiTraversable[R, >-->]]

  import leftBiTraversable.{ leftTraversable => leftLeftTraversable }
  import rightBiTraversable.{ leftTraversable => rightLeftTraversable }

  private[psbp] def leftTraversable[X] : Traversable[[Y] =>> Sum[L, R][Y, X], >-->] =
    new {
      override def traverse[Z, Y]
        : (Z >--> Y) => Sum[L, R][Z, X] >--> Sum[L, R][Y, X] =
          `z>-->y` =>
            leftLeftTraversable.traverse(`z>-->y`) ||| rightLeftTraversable.traverse(`z>-->y`)
   }

  import leftBiTraversable.{ rightTraversable => leftRightTraversable }
  import rightBiTraversable.{ rightTraversable => rightRightTraversable }

  private[psbp] def rightTraversable[X] : Traversable[[Y] =>> Sum[L, R][X, Y], >-->] =
    new {
      override def traverse[Z, Y]
        : (Z >--> Y) => Sum[L, R][X, Z] >--> Sum[L, R][X, Y] =
          `z>-->y` =>
            leftRightTraversable.traverse(`z>-->y`) ||| rightRightTraversable.traverse(`z>-->y`)
   }