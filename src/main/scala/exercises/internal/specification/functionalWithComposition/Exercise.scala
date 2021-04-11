// package psbp.exercises.internal.specification.functionalWithComposition

// // import psbp.external.specification.program.Functional

// // import psbp.external.specification.program.Composition

// import psbp.external.specification.materialization.Materialization

// import exercises.external.specification.functionalWithComposition.{
//   FunctionalWithComposition
//   , mainHelloWorld
// }

// type Function[-Z, + Y] = Z => Y

// given functionFunctionalWithComposition: FunctionalWithComposition[Function] with

//   override private[psbp] def andThen[Z, Y, X](
//   `z>-->y`: Z => Y
//   , `y>-->x`: => Y => X
//   ): Z => X =
//     `z>-->y` andThen `y>-->x`

//   override private[psbp] def toProgram[Z, Y]: (Z => Y) => Z => Y =
//     Predef.identity 

// given functionMaterialization: Materialization[Function, Unit, Unit] with
//   override private[psbp] val materialize: (Unit => Unit) => Unit => Unit =
//     Predef.identity  

// @main def main(args: String*): Unit =
//   mainHelloWorld materialized () 