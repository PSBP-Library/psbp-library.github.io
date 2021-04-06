package psbp.internal.implementation.aggregatable.recursive

import scala.language.postfixOps

import psbp.external.specification.program.Program
import psbp.external.specification.program.Functional

import psbp.external.specification.aggregatable.{ Traversable }

import psbp.internal.specification.aggregatable.{ BiTraversable }

import scala.{ Predef => function }

given identityBiTraversable[>-->[- _, + _]: Program]: BiTraversable[Identity, >-->] with

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
        