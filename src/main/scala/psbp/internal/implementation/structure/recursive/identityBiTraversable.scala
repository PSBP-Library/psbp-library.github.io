package psbp.internal.implementation.structure.recursive

import scala.language.postfixOps

import scala.{ 
  Predef => function 
}

import psbp.external.specification.program.Functional

import psbp.external.specification.structure.Traversable 

import psbp.internal.specification.structure.BiTraversable

given identityBiTraversable[>-->[- _, + _]: Functional]: BiTraversable[Identity, >-->] with

  private val functional: Functional[>-->] = summon[Functional[>-->]]

  override private[psbp] def leftTraversable[X]: Traversable[[Y] =>> Identity[Y, X], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => X >--> X =
        _ =>
          functional.identity
    }
   
  override private[psbp] def rightTraversable[X]: Traversable[[Y] =>> Identity[X, Y], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => Z >--> Y =
        function.identity
    }
        