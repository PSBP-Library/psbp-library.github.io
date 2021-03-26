package psbp.external.specifcation.types

// product

type &&[+Z, +Y] = (Z, Y)

// sum

enum ||[+Z, +Y]:
  case Left(z: Z) extends (Z || Y)
  case Right(y: Y) extends (Z || Y)

  def foldSum[X]: ((Z => X) && (Y => X)) => X =
    (`z=>x`, `y=>x`) =>
      this match
        case Left(z) =>
          `z=>x`(z)
        case Right(y) =>
          `y=>x`(y) 

// Rec

private[psbp] case class Rec[+A[+ _]](`a[rec[a]]`: A[Rec[A]])

private[psbp] type Left[A[+ _, + _]] = [Z] =>> [Y] =>> A[Z, Y]

// private[psbp] type LeftRec[A[+ _, + _]] = [Z] =>> Rec[[Y] =>> A[Z, Y]]
  
private[psbp] type LeftRec[A[+ _, + _]] = [Z] =>> Rec[Left[A][Z]]
