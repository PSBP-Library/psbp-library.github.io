package psbp.internal.implementation.aggregatable.recursive

import scala.language.postfixOps

import psbp.external.specification.program.Program
import psbp.external.specification.program.Functional

import psbp.external.specification.aggregatable.{ Traversable }

import psbp.internal.specification.aggregatable.{ BiTraversable }

given noneBiTraversable[>-->[- _, + _]: Program]: BiTraversable[None, >-->] with

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
