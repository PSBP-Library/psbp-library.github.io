// package psbp.exercises.internal.specification.constructionWithFunctional

// import psbp.external.specification.types.&&

// import psbp.external.specification.program.Construction

// import psbp.external.specification.program.Functional

// trait ConstructionWithFunctional[>-->[- _, + _]]
//   extends Construction[>-->]
//   with Functional[>-->]

// import exercises.external.specification.functional.{
//   hello
//   , comma
//   , blank
//   , world
//   , exclamation
//   , newLine
// }

// def helloWorld[>-->[- _, + _]: ConstructionWithFunctional]: Unit >--> (((((Unit, Unit), Unit), Unit), Unit), Unit) =
//   hello && comma && blank && world && exclamation && newLine

// type Function[-Z, + Y] = Z => Y

// given functionConstructionWithFunctional: ConstructionWithFunctional[Function] with

//   // override def `z>-->z`[Z]: Z => Z =
//   //   Predef.identity

//   // override private[psbp] def andThen[Z, Y, X](
//   // `z>-->y`: Z => Y
//   // , `y>-->x`: => Y => X
//   // ): Z => X =
//   //   `z>-->y` andThen `y>-->x`

//   override private[psbp] def construct[Z, Y, X](
//     `z>-->y`: Z => Y
//     , `z>-->x`: => Z => X
//   ): Z => (Y && X) =
//     z =>
//       (`z>-->y`(z), `z>-->x`(z))

//   override private[psbp] def toProgram[Z, Y]: (Z => Y) => Z => Y =
//     Predef.identity

// @main def main(args: String*): Unit =
//   helloWorld apply ()  