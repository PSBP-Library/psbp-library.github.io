package psbp.internal.specification.computation

import psbp.specification.types.{ &&, || }

import psbp.specification.function.{ `(z&&y)=>(z&&y)`, foldSum, `z=>(z||y)`, `y=>(z||y)`, and, or }

private[psbp] trait FunctionLifting[C[+ _]] 
  extends Function0Lifting[C] 
  with Function1Lifting[C] 
  with Function2Lifting[C] 
  with Function3Lifting[C]: // ...

  // defined
  
  private[psbp] def liftProduct[Z, Y]: (C[Z] && C[Y]) => C[Z && Y] =
    lift2(`(z&&y)=>(z&&y)`)

  private[psbp] def liftSum[Z, Y]: (C[Z] || C[Y]) => C[Z || Y] =
    foldSum(lift1(`z=>(z||y)`), lift1(`y=>(z||y)`)) 

  private[psbp] def liftAnd[Z, Y, X, W]: ((Z => C[X]) && (Y => C[W])) => ((Z && Y) => C[X && W]) =
      (`z=>cx`, `y=>cw`) =>
        and(`z=>cx`, `y=>cw`) andThen liftProduct    

  private[psbp] def liftOr[Z, Y, X, W]: ((Z => C[X]) && (Y => C[W])) => ((Z || Y) => C[X || W]) =
    (`z=>cx`, `y=>cw`) =>
      or(`z=>cx`, `y=>cw`) andThen liftSum 