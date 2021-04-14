package psbp.internal.implementation.materialization

import psbp.external.specification.materialization.Materialization

import psbp.internal.specification.computation.Computation

import psbp.internal.implementation.computation.transformation.ReadingTransformed

private[psbp] given readingTransformedMaterialization[
  R
  , C[+ _]: Computation
          : [C[+ _]] =>> Materialization[[Z, Y] =>> Z => C[Y], Z, Y]
  , Z, Y
]: Materialization[[Z, Y] =>> Z => ReadingTransformed[R, C][Y], Z, R ?=> C[Y]] with

  private type F[+Z] = C[Z]
  private type T[+Z] = ReadingTransformed[R, C][Z]

  private type `=>F`[-Z, +Y] = Z => F[Y]
  private type `=>T`[-Z, +Y] = Z => T[Y]

  private val Materialization: Materialization[`=>F`, Z, Y] = 
    summon[Materialization[`=>F`, Z, Y]]
  import Materialization.{ 
    materialize => materializeF 
  }

  private val computation: Computation[C] = 
    summon[Computation[F]]
  import computation.{ 
    result => resultF
    , bind => bindF 
  }

  override val materialize: (Unit `=>T` Unit) => Z ?=> (R ?=> C[Y]) =
    `u=>tu` =>
      println(s">>> in materialize of readingTransformedMaterialization")
      // println(s">>> given $gr in materialize of readingTransformedMaterialization")
      bindF({
        Thread.sleep(3000)
        given gr: R = summon[R]
        val cu: C[Unit] = `u=>tu`(())
        cu
        }
        , (u: Unit) => 
          val y: Y = materializeF(resultF)
          resultF(y)
      )
