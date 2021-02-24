package psbp.internalSpecification.computation

import psbp.specification.program.{ &&, ||, Program }

private[psbp] trait Computation[C[+ _]] 
  extends Resulting[C] 
  with Binding[C]

private[psbp] given programFromComputation[C[+ _]: Computation]: Program[[Z, Y] =>> Z => C[Y]] with
  
  private val computation: Computation[C] = summon[Computation[C]]

  import computation.{ result, bind }

  private type `=>C`[-Z, +Y] = Z => C[Y]

  private[psbp] override def toProgram[Z, Y]: (Z => Y) => Z `=>C` Y = 
    `z=>y` => 
      z =>
        result(`z=>y`(z))

  private[psbp] override def andThen[Z, Y, X]
    (`z>-->y`: Z `=>C` Y, `y>-->x`: => Y `=>C` X): Z `=>C` X =
    z =>
      `z>-->y`(z) >= 
        `y>-->x`

  private[psbp] override def construct[Z, Y, X]
    (`z>-->y`: Z `=>C` Y, `z>-->x`: => Z `=>C` X): Z `=>C` (Y && X) =
    z =>
      `z>-->y`(z) >= { y => 
        `z>-->x`(z) >= { x =>
          result(y, x)
        }
      }

  private[psbp] override def conditionally[Z, Y, X]
    (`y>-->z`: => Y `=>C` Z, `x>-->z`: => X `=>C` Z): (Y || X) `=>C` Z =
    _.foldSum(`y>-->z`, `x>-->z`) 

