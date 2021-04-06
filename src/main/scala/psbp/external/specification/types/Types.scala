package psbp.external.specification.types

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

