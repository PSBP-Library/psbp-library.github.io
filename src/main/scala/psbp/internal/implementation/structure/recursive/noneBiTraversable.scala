package psbp.internal.implementation.structure.recursive

import scala.language.postfixOps

import psbp.external.specification.program.Identity

import psbp.external.specification.structure.Traversable

import psbp.internal.specification.structure.BiTraversable

given noneBiTraversable[>-->[- _, + _]: Identity]: BiTraversable[None, >-->] with

  private val identity_ : Identity[>-->] = summon[Identity[>-->]]

  override private[psbp] def leftTraversable[X]: Traversable[[Y] =>> None[Y, X], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => Unit >--> Unit =
        _ =>
          identity_.identity
    }

  override private[psbp] def rightTraversable[X]: Traversable[[Y] =>> None[X, Y], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => Unit >--> Unit =
        _ =>
          identity_.identity
    } 
