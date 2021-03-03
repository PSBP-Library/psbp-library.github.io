package psbp.specification.types

// product

type &&[+Z, +Y] = (Z, Y)

// sum

enum ||[+Z, +Y]:
  case Left(z: Z) extends (Z || Y)
  case Right(y: Y) extends (Z || Y)

  def foldSum[X](`z=>x`: => Z => X, `y=>x`: => Y => X): X =
    this match
      case Left(z) =>
        `z=>x`(z)
      case Right(y) =>
        `y=>x`(y) 
  