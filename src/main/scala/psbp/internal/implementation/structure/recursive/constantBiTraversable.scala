package psbp.internal.implementation.structure.recursive

import scala.{ Predef => function }

import scala.language.postfixOps

import psbp.external.specification.program.Program

import psbp.external.specification.program.Functional

import psbp.external.specification.structure.{ Traversable }

import psbp.internal.specification.structure.{ BiTraversable }

given constantBiTraversable[>-->[- _, + _]: Program]: BiTraversable[Constant, >-->] with

  override private[psbp] def leftTraversable[X]: Traversable[[Y] =>> Constant[Y, X], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => Z >--> Y =
        function.identity
    }
   
  private val functional: Functional[>-->] = summon[Functional[>-->]]

  override private[psbp] def rightTraversable[X]: Traversable[[Y] =>> Constant[X, Y], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => X >--> X =
        _ =>
          functional.identity
    } 
