package psbp.internal.implementation.structure.recursive

import scala.language.postfixOps

import psbp.external.specification.program.Functional

import psbp.external.specification.structure.Traversable

import psbp.internal.specification.structure.BiTraversable

given noneBiTraversable[>-->[- _, + _]: Functional]: BiTraversable[None, >-->] with

  private val functional: Functional[>-->] = summon[Functional[>-->]]

  override private[psbp] def leftTraversable[X]: Traversable[[Y] =>> None[Y, X], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => Unit >--> Unit =
        _ =>
          functional.identity
    }

  override private[psbp] def rightTraversable[X]: Traversable[[Y] =>> None[X, Y], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => Unit >--> Unit =
        _ =>
          functional.identity
    } 
