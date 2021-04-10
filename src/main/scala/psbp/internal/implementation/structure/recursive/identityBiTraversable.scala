package psbp.internal.implementation.structure.recursive

import scala.language.postfixOps

import scala.{ 
  Predef => function 
}

import psbp.external.specification.program

import psbp.external.specification.structure.Traversable 

import psbp.internal.specification.structure.BiTraversable

given identityBiTraversable[>-->[- _, + _]: program.Identity]: BiTraversable[Identity, >-->] with

  private val identity_ : program.Identity[>-->] = summon[program.Identity[>-->]]

  override private[psbp] def leftTraversable[X]: Traversable[[Y] =>> Identity[Y, X], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => X >--> X =
        _ =>
          identity_.identity
    }
   
  override private[psbp] def rightTraversable[X]: Traversable[[Y] =>> Identity[X, Y], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => Z >--> Y =
        function.identity[Z >--> Y]
    }
        