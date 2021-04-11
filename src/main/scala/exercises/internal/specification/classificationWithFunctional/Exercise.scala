// package psbp.exercises.internal.specification.classificationWithFunctional

// import scala.language.postfixOps

// import psbp.external.specification.program.ClassificationWithFunctional

// import exercises.external.specification.functional.{
//   hello
//   , comma
//   , blank
//   , world
//   , exclamation
//   , newLine
// }

// def helloWorld[>-->[- _, + _]: ClassificationWithFunctional]: Unit >--> Unit =
//   hello >--> comma >--> blank >--> world >--> exclamation >--> newLine

// type Function[-Z, + Y] = Z => Y

// given functionClassificationWithFunctional: ClassificationWithFunctional[Function] with

//   override def `z>-->z`[Z]: Z => Z =
//     Predef.identity

//   override private[psbp] def andThen[Z, Y, X](
//   `z>-->y`: Z => Y
//   , `y>-->x`: => Y => X
//   ): Z => X =
//     `z>-->y` andThen `y>-->x`

//   override private[psbp] def toProgram[Z, Y]: (Z => Y) => Z => Y =
//     Predef.identity

// @main def main(args: String*): Unit =
//   helloWorld apply ()
