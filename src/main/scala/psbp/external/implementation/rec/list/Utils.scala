package psbp.external.implementation.rec.list

// import scala.collection.immutable

import psbp.external.implementation.list.{ List }

import psbp.external.implementation.rec.{ `a[rec[a]]=>rec[a]` } 

import psbp.external.specification.function.{ `z=>(z||y)`, `y=>(z||y)` }

def listToRecList[Z]: List[Z, RecList[Z]] => RecList[Z] = 
  `a[rec[a]]=>rec[a]`

def emptyRecList[Z]: RecList[Z] =
  listToRecList[Z](`z=>(z||y)`(()))

def consRecList[Z](z: Z, zs: RecList[Z]): RecList[Z] =
  listToRecList[Z](`y=>(z||y)`((z, zs)))

// def seqToRecListFunction[Z]: immutable.Seq[Z] => RecList[Z] = 
//   _.foldRight(emptyRecList)(consRecList) 

// def seqToRecList[
//   Z,
//   >-->[- _, + _]: Program]: immutable.Seq[Z] >--> RecList[Z] = 
//   seqToRecListFunction asProgram

// package psbp.implementation.rec.list

// import scala.language.postfixOps

// import scala.collection.immutable

// import psbp.external.specification.types.{ && }

// import psbp.external.specification.function.{ foldSum, `z=>(z||y)`, `y=>(z||y)` }

// // import psbp.external.specification.types.Rec

// // import psbp.internal.specification.rec.{ rec }

// import psbp.internal.implementation.aggregatable.rec.mkNone

// import psbp.implementation.list.List


// def recList[Z]: List[Z, RecList[Z]] => RecList[Z] = `a[rec[a]]=>rec[a]`

// def emptyRecList[Z]: RecList[Z] =
//   recList[Z](`z=>(z||y)`(()))

// def consRecList[Z](z: Z, zs: RecList[Z]): RecList[Z] =
//   recList[Z](`y=>(z||y)`((z, zs)))

// // // // extension [Z] (z: Z) def ::(zs: RecList[Z]): RecList[Z] =
// // // //   consRecList(z, zs)  
  
// def fromSeq[Z]: immutable.Seq[Z] => RecList[Z] = 
//   _.foldRight(emptyRecList)(consRecList[Z])

// import psbp.external.specification.program.Program

// def fromSeqProg[>-->[- _, + _]: Program, Z]: immutable.Seq[Z] => (Unit >--> RecList[Z]) =
//   (seq: immutable.Seq[Z]) =>
//     { (_: Unit) =>
//         seq.foldRight(emptyRecList)(consRecList[Z])
//     } asProgram

// import psbp.external.specification.aggregatable.recursive.{ RecursiveAggregatable }
  
// import psbp.implementation.list.{ List, RecursiveList }
      
// import psbp.external.specification.recursive.Recursive

// def emptySeq[Z]: Unit => immutable.Seq[Z] = 
//   _ =>
//     immutable.Seq()

// def consSeq[Z]: Z && immutable.Seq[Z] => immutable.Seq[Z] =
//   case (z, zs) => 
//     z +: zs

// def toSeq[
//   Z,
//   R[+_[+ _]],
//   >-->[- _, + _]: Program
//                 : [>-->[- _, + _]] =>> Recursive[R, >-->]
//                 : [>-->[- _, + _]] =>> RecursiveAggregatable[List, R, >-->]
//   ]: RecursiveList[R, Z] >--> immutable.Seq[Z] = 
    
//   val recursiveAggregatable: RecursiveAggregatable[List, R, >-->] = summon[RecursiveAggregatable[List, R, >-->]]
//   import recursiveAggregatable.reduce

//   val reducer: recursiveAggregatable.Reducer[Z, immutable.Seq[Z]] = 
//     foldSum(emptySeq[Z], consSeq[Z]) asProgram

//   reduce(reducer)

  

// // import psbp.external.specification.types.||

// // def toSeq[Z]: RecList[Z] => immutable.Seq[Z] = 
// //   val foo: (Unit || (immutable.Seq[Z] && Z)) => immutable.Seq[Z] = 
// //     foldSum(emptySeq[Z], consSeq[Z])

// //   ???

//   //`rec[a]=>a[rec[a]]`apply(foldSum((_: Unit) => emptySeq[Z], consSeq[Z]))

// /*  

// package psbp.implementation.rec.list

// import scala.language.postfixOps

// import scala.collection.immutable

// import psbp.external.specification.program.Program

// import psbp.external.specification.types.{ &&,Recursive }

// def makeRecursiveList[
//   R[+_[+ _]]: [R[+_[+ _]]] =>> Recursive[R, >-->], 
//   >-->[- _, + _]: Program, 
//   Z]: List[Z, RecursiveList[R, Z]] >--> RecursiveList[R, Z] = 

//   val recursive: Recursive[R, >-->] = summon[Recursive[R, >-->]]
//   recursive.`a[r[a]]>-->r[a]`

// import psbp.internal.implementation.aggregatable.rec.{ mkNone }
// import psbp.external.specification.function.{ foldSum, `z=>(z||y)`, `y=>(z||y)` }

// def fromSeqToList[
//   R[+_[+ _]]: [R[+_[+ _]]] =>> Recursive[R, >-->], 
//   >-->[- _, + _]: Program, 
//   Z]: immutable.Seq[Z] >--> List[Z, RecursiveList[R, Z]] = 
//   {
//     (seq: immutable.Seq[Z]) =>
//       seq match {
//         case z +: zs => `y=>(z||y)`((z, ???))
//         case _ => `z=>(z||y)`(mkNone[Z, RecursiveList[R, Z]])
//       }
//   } asProgram    
//   // _.foldRight(emptyRecList)(consRecList[Z])

// def fromSeqToRecursiveList[
//   R[+_[+ _]]: [R[+_[+ _]]] =>> Recursive[R, >-->], 
//   >-->[- _, + _]: Program, 
//   Z]: immutable.Seq[Z] >--> RecursiveList[R, Z] = 
//   fromSeqToList >--> makeRecursiveList

// package psbp.implementation.rec.list

// import psbp.internal.implementation.aggregatable.rec.{ Product, Constant, Identity, Sum, None }

// import psbp.external.specification.function.{ foldSum, `z=>(z||y)`, `y=>(z||y)` }

// import scala.language.postfixOps

// import psbp.external.specification.functional.{ `z>-->(z||y)` , `y>-->(z||y)` }

// def makeNoneRecursiveList[
//   R[+_[+ _]]: [R[+_[+ _]]] =>> Recursive[R, >-->], 
//   >-->[- _, + _]: Program, 
//   // Z]: None[Z, RecursiveList[R, Z]] >--> RecursiveList[R, Z] =
//   Z]: Unit >--> RecursiveList[R, Z] =

//   `z>-->(z||y)` >--> makeRecursiveList[R, >-->, Z]
  
// def makeConsRecursiveList[
//   R[+_[+ _]]: [R[+_[+ _]]] =>> Recursive[R, >-->], 
//   >-->[- _, + _]: Program, 
//   // Z]: Cons[Z, RecursiveList[R, Z]] >--> RecursiveList[R, Z] =
//   // Z]: Product[Constant, Identity][Z, RecursiveList[R, Z]] >--> RecursiveList[R, Z] =
//   // Z]: (Constant[Z, RecursiveList[R, Z]] && Identity[Z, RecursiveList[R, Z]]) >--> RecursiveList[R, Z] =
//   Z]: (Z && RecursiveList[R, Z]) >--> RecursiveList[R, Z] =
  
//   `y>-->(z||y)` >--> makeRecursiveList[R, >-->, Z]

// */
