package examples.specification.program.implementation.recursive.aggregatable.implementation.list

import psbp.external.specification.program.{ Functional, Program }

import psbp.external.specification.aggregatable.recursive.{ RecursiveAggregatable }

import psbp.external.implementation.list.{ List, RecursiveList }

import psbp.external.specification.recursive.Recursive

import examples.specification.functional.{ isPositive, areAllPositiveReducer }

// import examples.specification.function.areAllPositiveReducer

// import psbp.external.specification.function.foldSum

// import examples.specification.function.{ constantTrue, and }

import scala.language.postfixOps
  
def areAllPositive[
  R[+_[+ _]],
  >-->[- _, + _]: Program
                : [>-->[- _, + _]] =>> Recursive[R, >-->]
                : [>-->[- _, + _]] =>> RecursiveAggregatable[List, R, >-->]
  ]: RecursiveList[R, BigInt] >--> Boolean = 

    val recursiveAggregatable: RecursiveAggregatable[List, R, >-->] = summon[RecursiveAggregatable[List, R, >-->]]
    import recursiveAggregatable.aggregate

    // val localAreAllPositiveReducer = areAllPositiveReducer[>-->]

    aggregate(isPositive, areAllPositiveReducer[>-->]) 
    // aggregate(isPositive, areAllPositiveReducer asProgram) 

    // val traverser: BigInt >--> Boolean = isPositive

    // val reducer: recursiveAggregatable.Reducer[Boolean, Boolean] = 
    //   foldSum(constantTrue[Unit], and) asProgram

    // aggregate(traverser, reducer)

    // maybe inline and make Reducer type private (like belob)

// import psbp.external.specification.program.Program

// import psbp.implementation.rec.list.{ List, RecList }

// // import psbp.implementation.rec.list.RecListAggregatable

// import psbp.external.specification.aggregatable.rec.RecAggregatable

// import examples.specification.function.{ constantTrue, and }

// import examples.specification.functional.isPositive

// // def areAllPositive[>-->[- _, + _]: Program: RecListAggregatable]: RecList[BigInt] >--> Boolean =
// def areAllPositive[>-->[- _, + _]: Program: [>-->[- _, + _]] =>> RecAggregatable[List, >-->]]: RecList[BigInt] >--> Boolean =
//     // def areAllPositive[>-->[- _, + _]: Program: [>-->[- _, + _]] =>> RecAggregatable[List, >-->]]: RecList[BigInt] >--> Boolean =

//   val recListAggregatable: RecAggregatable[List, >-->] = summon[RecAggregatable[List, >-->]]
//   // import recListAggregatable.{ recAggregate, Structure } 
//   import recListAggregatable.{ aggregate } 

//   // val recListAggregatable: RecListAggregatable[>-->] = summon[RecListAggregatable[>-->]]
//   // import recListAggregatable.recAggregate 

//   // val recListAggregatable: RecAggregatable[List, >-->] = summon[RecAggregatable[List, >-->]]
//   // import recListAggregatable.recAggregate 

//   // import psbp.implementation.rec.list.ListStructure

//   // def foo: recListAggregatable.Structure[Boolean, Boolean] = (constantTrue[Unit], and)

//   import psbp.external.specification.function.foldSum

//   // val structure = // Structure[Boolean, Boolean] = 
//   //   foldSum(constantTrue[Unit], and) // .asInstanceOf[Structure[Boolean, Boolean]]

//   aggregate(isPositive, foldSum(_ => true, and))