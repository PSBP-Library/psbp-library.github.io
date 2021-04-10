package psbp.internal.implementation.structure.recursive

import scala.language.postfixOps

import psbp.external.specification.program.Program

import psbp.external.specification.structure.Traversable

import psbp.internal.specification.structure.BiTraversable

given productBiTraversable[
  L[+ _, + _]: [L[+ _, + _]] =>> BiTraversable[L, >-->]
  , R[+ _, + _]: [R[+ _, + _]] =>> BiTraversable[R, >-->]
  , >-->[- _, + _]: Program
]: BiTraversable[Product[L, R], >-->] with

  private val leftBiTraversable =
    summon[BiTraversable[L, >-->]]
  private val rightBiTraversable = 
    summon[BiTraversable[R, >-->]]

  import leftBiTraversable.{ 
    leftTraversable => leftLeftTraversable 
  }
  import rightBiTraversable.{ 
    leftTraversable => rightLeftTraversable
  }

  // todo: also try out with parallelism

  private[psbp] def leftTraversable[X] : Traversable[[Y] =>> Product[L, R][Y, X], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => Product[L, R][Z, X] >--> Product[L, R][Y, X] =
          `z>-->y` =>
            leftLeftTraversable.traverse(`z>-->y`) &&& rightLeftTraversable.traverse(`z>-->y`)
   }

  import leftBiTraversable.{ 
    rightTraversable => leftRightTraversable 
  }
  import rightBiTraversable.{ 
    rightTraversable => rightRightTraversable 
  }

  // todo: also try out with parallelism

  private[psbp] def rightTraversable[X] : Traversable[[Y] =>> Product[L, R][X, Y], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => Product[L, R][X, Z] >--> Product[L, R][X, Y] =
          `z>-->y` =>
            leftRightTraversable.traverse(`z>-->y`) &&& rightRightTraversable.traverse(`z>-->y`)
   }   
