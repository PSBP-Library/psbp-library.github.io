// package psbp.exercises.internal.specification.functional

// import scala.language.postfixOps

// import psbp.external.specification.program.Functional

// import exercises.external.specification.function

// def `z>-->z`[Z, >-->[- _, + _]: Functional]: Z >--> Z =

//   val functional: Functional[>-->] = 
//     summon[Functional[>-->]]
//   import functional.toProgram

//   toProgram(function.`z==>z`)

// type Function[-Z, + Y] = Z => Y 

// given functionFunctional: Functional[Function] with
//   override private[psbp] def toProgram[Z, Y]: (Z => Y) => Z => Y =
//     identity

// @main def main(args: String*): Unit =
//   val argument: Int = 12345
//   val result: Int = `z>-->z` apply argument
//   println(s"applying `z=>z` to the integer argument $argument yields result $result")    

