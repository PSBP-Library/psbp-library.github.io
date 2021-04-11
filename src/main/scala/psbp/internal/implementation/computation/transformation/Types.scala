package psbp.internal.implementation.computation.transformation

// ReadingTransformed

private[psbp] type ReadingTransformed[R, C[+ _]] = [Y] =>> R ?=> C[Y]   

// WritingTransformed

private[psbp] type WritingTransformed[W, C[+ _]] = [Y] =>> C[(W, Y)] 

// ReactiveTransformed

private[psbp] type ReactiveTransformed[C[+ _]] = [Y] =>> (C[Y] => Unit) => Unit

// FreeTransformed

private[psbp] enum Free[C[+ _], +Y]:

  private[psbp] case Transform[C[+ _], +Y](cy: C[Y]) extends Free[C, Y]

  private[psbp] case Result[C[+ _], +Y](y: Y) extends Free[C, Y]
  
  private[psbp] case Bind[C[+ _], -Z, ZZ <: Z, +Y]
    (fczz: Free[C, ZZ], `z=>fcy`: Z => FreeTransformed[C][Y]) extends Free[C, Y]

private[psbp] type FreeTransformed[C[+ _]] = [Y] =>> Free[C, Y]

import Free._

import psbp.internal.specification.computation.Computation

private[psbp] def foldFree[
  Y
  , C[+ _]: Computation
](
  fcz: FreeTransformed[C][Y]
 ): C[Y] =

  type F[+Y] = C[Y]
  type T[+Y] = FreeTransformed[F][Y]
 
  val computationF: Computation[F] = 
    summon[Computation[F]]
  import computationF.{ 
    result => resultF
    , bind => bindF
  } 

  fcz match {
    case Transform(fz) => 
      fz
    case Result(z) =>
      resultF(z)
    case Bind(Transform(fy), y2tz) =>
      bindF(resultF(fy), y2tz andThen foldFree)
    case Bind(Result(y), y2tz) =>
      foldFree(y2tz(y))
    case Bind(Bind(tx, x2ty), y2tz) =>
      foldFree(Bind(tx, { x => Bind(x2ty(x), y2tz) }))
    case any =>
      sys.error(s"Impossible, since, 'foldFree' eliminates the case for $any")
  }

// StateTransformed

private[psbp] type StateTransformed[S, C[+ _]] = [Y] =>> S => C[(S, Y)]  