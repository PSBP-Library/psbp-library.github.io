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

// private[psbp] type WithAtLeft[+Y] = [A[+ _, + _]] =>> [X] =>> A[Y, X]

// private[psbp] type RightRec[A[+ _, + _]] = [Y] =>> Rec[[X] =>> A[Y, X]]

// private[psbp] type AtLeft[A[+ _, + _]] = [Z] =>> [Y] =>> A[Z, Y]

// private[psbp] type LeftRec[A[+ _, + _]] = [Z] =>> Rec[[Y] =>> A[Z, Y]]
  
// private[psbp] type LeftRec[A[+ _, + _]] = [Z] =>> Rec[AtLeft[A][Z]]

// trait Recursive[R[+_[+ _]], >-->[- _, + _]: Program]:
//   private[psbp] def `r[a]>-->a[r[a]]`[A[+ _]]: R[A] >--> A[R[A]]
//   private[psbp] def `a[r[a]]>-->r[a]`[A[+ _]]: A[R[A]] >--> R[A]

// Rec

// private[psbp] case class Rec[+A[+ _]](`a[rec[a]]`: A[Rec[A]])

// def `rec[a]=>a[rec[a]]`[A[+ _]]: Rec[A] => A[Rec[A]] = _.`a[rec[a]]`

// def `a[rec[a]]=>rec[a]`[A[+ _]]: A[Rec[A]] => Rec[A] = Rec(_)
  
// import scala.language.postfixOps

// given recRecursive[>-->[- _, + _]: Program]: Recursive[Rec, >-->] with
//   override private[psbp] def `r[a]>-->a[r[a]]`[A[+ _]]: Rec[A] >--> A[Rec[A]] =
//     `rec[a]=>a[rec[a]]` asProgram
//   override private[psbp] def `a[r[a]]>-->r[a]`[A[+ _]]: A[Rec[A]] >--> Rec[A] =
//     `a[rec[a]]=>rec[a]` asProgram
