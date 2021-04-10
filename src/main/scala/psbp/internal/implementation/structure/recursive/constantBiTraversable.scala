package psbp.internal.implementation.structure.recursive

import scala.{ 
  Predef => function
}

import scala.language.postfixOps

import psbp.external.specification.program.Identity

import psbp.external.specification.structure.Traversable

import psbp.internal.specification.structure.BiTraversable

given constantBiTraversable[>-->[- _, + _]: Identity]: BiTraversable[Constant, >-->] with

  override private[psbp] def leftTraversable[X]: Traversable[[Y] =>> Constant[Y, X], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => Z >--> Y =
        function.identity
    }
   
  private val identity_ : Identity[>-->] = summon[Identity[>-->]]

  override private[psbp] def rightTraversable[X]: Traversable[[Y] =>> Constant[X, Y], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => X >--> X =
        _ =>
          identity_.identity
    } 
