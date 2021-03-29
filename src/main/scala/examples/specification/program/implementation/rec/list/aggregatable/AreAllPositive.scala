// package examples.specification.program.implementation.recursive.list.aggregatable

// import psbp.external.specification.program.Program

// import psbp.internal.specification.aggregatable.rec.RecAggregatable

// import psbp.implementation.rec.list.{ List, RecursiveList }

// import psbp.external.specification.types.Recursive

// import examples.specification.functional.isPositive

// import psbp.external.specification.function.foldSum

// import examples.specification.function.{ constantTrue, and }

// import scala.language.postfixOps

// // def areAllPositive[
// //   R[+_[+ _]]: [R[+_[+ _]]] =>> Recursive[R, >-->],
// //   >-->[- _, + _]: Program: [>-->[- _, + _]] =>> RecAggregatable[List, R, >-->]
// //   ]: RecursiveList[R, BigInt] >--> Boolean = 
  
// def areAllPositive[
//   R[+_[+ _]],
//   >-->[- _, + _]: Program
//                 : [>-->[- _, + _]] =>> Recursive[R, >-->]
//                 : [>-->[- _, + _]] =>> RecAggregatable[List, R, >-->]
//   ]: RecursiveList[R, BigInt] >--> Boolean = 

//     val recAggregatable: RecAggregatable[List, R, >-->] = summon[RecAggregatable[List, R, >-->]]
//     import recAggregatable.aggregate

//     val traverser: BigInt >--> Boolean = isPositive

//     // maybe inline and make Reducer type private
//     val reducer: recAggregatable.Reducer[Boolean, Boolean] = 
//       foldSum(constantTrue[Unit], and) asProgram

//     aggregate(traverser, reducer)

// // import psbp.external.specification.program.Program

// // import psbp.implementation.rec.list.{ List, RecList }

// // // import psbp.implementation.rec.list.RecListAggregatable

// // import psbp.external.specification.aggregatable.rec.RecAggregatable

// // import examples.specification.function.{ constantTrue, and }

// // import examples.specification.functional.isPositive

// // // def areAllPositive[>-->[- _, + _]: Program: RecListAggregatable]: RecList[BigInt] >--> Boolean =
// // def areAllPositive[>-->[- _, + _]: Program: [>-->[- _, + _]] =>> RecAggregatable[List, >-->]]: RecList[BigInt] >--> Boolean =
// //     // def areAllPositive[>-->[- _, + _]: Program: [>-->[- _, + _]] =>> RecAggregatable[List, >-->]]: RecList[BigInt] >--> Boolean =

// //   val recListAggregatable: RecAggregatable[List, >-->] = summon[RecAggregatable[List, >-->]]
// //   // import recListAggregatable.{ recAggregate, Structure } 
// //   import recListAggregatable.{ aggregate } 

// //   // val recListAggregatable: RecListAggregatable[>-->] = summon[RecListAggregatable[>-->]]
// //   // import recListAggregatable.recAggregate 

// //   // val recListAggregatable: RecAggregatable[List, >-->] = summon[RecAggregatable[List, >-->]]
// //   // import recListAggregatable.recAggregate 

// //   // import psbp.implementation.rec.list.ListStructure

// //   // def foo: recListAggregatable.Structure[Boolean, Boolean] = (constantTrue[Unit], and)

// //   import psbp.external.specification.function.foldSum

// //   // val structure = // Structure[Boolean, Boolean] = 
// //   //   foldSum(constantTrue[Unit], and) // .asInstanceOf[Structure[Boolean, Boolean]]

// //   aggregate(isPositive, foldSum(_ => true, and))