package psbp.internal.specification.aggregatable.recursive

// import psbp.external.specification.types.&&

// import psbp.external.specification.types.{ Rec, RightRec }

// import psbp.external.specification.aggregatable.rec.RecReducerType

// import psbp.external.specification.aggregatable.{ InitialTraverser, InitialReducer, Aggregatable }

// import psbp.internal.specification.lifting.{ Function0Lifting, Function1Lifting, Function1LiftingAtRight , Function1LiftingAtLeft }

// import psbp.internal.specification.lifting.function1LiftingFromFunction1LiftingAtRight

// import psbp.internal.specification.lifting.function1LiftingFromFunction1LiftingAtLeft

// import psbp.internal.specification.aggregatable.{ FunctionLevelFusing, FunctionLevelReducible }

// import psbp.internal.specification.lifting.{ Function0Lifting }

import scala.language.postfixOps

import psbp.external.specification.recursive.{ Recursive }

// import psbp.external.specification.aggregatable.{ InitialTraverser, InitialReducer, Traversable} // , traversableFromTraversableAtRight } // , traversableFromTraversableAtLeft }

import psbp.external.specification.aggregatable.recursive.{ RecursiveReducerType, RecursiveAggregatable }

import psbp.external.specification.program.Program

// import psbp.external.specification.functional.`z>-->z`

import psbp.external.specification.aggregatable.{ Aggregatable }

import psbp.internal.specification.aggregatable.{ BiTraversable }

// given recAggregatable[A[+ _, + _]: [A[+ _, + _]] =>> TraversableAtLeft[A, >-->]: [A[+ _, + _]] =>> TraversableAtRight[A, >-->], >-->[- _, + _]: Program]: 
//   Aggregatable[[Y] =>> Rec[[X] =>> A[Y, X]], >-->]
//   with RecReducerType[A, >-->] with

//   override private[psbp] def initialTraverser[Y]: Y >--> Y = 
//     val y2y : Y => Y = 
//       y => 
//         y
//     y2y asProgram // `z>-->z`[>-->, Y]

//   override private[psbp] def initialReducer[Y]: Reducer[Y, Rec[[X] =>> A[Y, X]]] =
//     `a[rec[a]]=>rec[a]` asProgram

//   override private[psbp] def fuse[Z, Y, X]: Aggregator[Z, Y, X] => Reducer[Z, X] =
//     (`z>-->y`, `a[y,x]>-->x`) =>
//       val traversableAtLeft: TraversableAtLeft[A, >-->] = summon[TraversableAtLeft[A, >-->]]
//       traversableAtLeft.leftTraversable.traverse(`z>-->y`) >--> `a[y,x]>-->x`

//   override def reduce[Y, X]: Reducer[Y, X] => Rec[[X] =>> A[Y, X]] >--> X = 
//     `a[y,x]>-->x` =>
//       val traversableAtRight: TraversableAtRight[A, >-->] = summon[TraversableAtRight[A, >-->]]
//       traversableAtRight.rightTraversable.rec(`a[y,x]>-->x`)   

// trait RecAggregatable[
//   A[+ _, + _]: [A[+ _, + _]] =>> TraversableAtLeft[A, >-->]: [A[+ _, + _]] =>> TraversableAtRight[A, >-->], 
//   R[+_[+ _]]: [R[+_[+ _]]] =>> Recursive[R, >-->], 
//   >-->[- _, + _]: Program] 
//   extends Aggregatable[[Y] =>> R[[X] =>> A[Y, X]], >-->]
//   with RecReducerType[A, >-->]

private[psbp] given recursiveAggregatableFromBiTraversable[
  A[+ _, + _]: [A[+ _, + _]] =>> BiTraversable[A, >-->], 
  R[+_[+ _]]: [R[+_[+ _]]] =>> Recursive[R, >-->], 
  >-->[- _, + _]: Program]: 
  RecursiveAggregatable[A, R, >-->] with

  val program: Program[>-->] = summon[Program[>-->]]
  import program.identity

  val recursive: Recursive[R, >-->] = summon[Recursive[R, >-->]]
  import recursive.{ `a[r[a]]>-->r[a]` }

  override private[psbp] def initialTraverser[Y]: Y >--> Y = 
    identity

  override private[psbp] def initialReducer[Y]: Reducer[Y, R[[X] =>> A[Y, X]]] =
    `a[r[a]]>-->r[a]`

  override private[psbp] def fuse[Z, Y, X]: Aggregator[Z, Y, X] => Reducer[Z, X] =
    (`z>-->y`, `a[y,x]>-->x`) =>
      val biTraversableAtLeft: BiTraversable[A, >-->] = summon[BiTraversable[A, >-->]]
      val `a[z,x]>-->a[y,x]`: A[Z, X] >--> A[Y, X] = biTraversableAtLeft.leftTraversable.traverse(`z>-->y`) 
      `a[z,x]>-->a[y,x]`>-->`a[y,x]>-->x`

  override def reduce[Y, X]: Reducer[Y, X] => R[[X] =>> A[Y, X]] >--> X =
    `a[y,x]>-->x` =>
      val biTraversableAtLeft: BiTraversable[A, >-->] = summon[BiTraversable[A, >-->]]
      val `(a[y,x]>-->x)=>(r[[x]=>>a[y,x]]>-->x)`: (A[Y, X] >--> X) => (R[[X] =>> A[Y, X]] >--> X) = 
        biTraversableAtLeft.rightTraversable.recurse 
      `(a[y,x]>-->x)=>(r[[x]=>>a[y,x]]>-->x)`apply`a[y,x]>-->x` 

/*

class RecAggregatable[A[+ _, + _]: [A[+ _, + _]] =>> TraversableAtLeft[A, >-->]: [A[+ _, + _]] =>> TraversableAtRight[A, >-->], >-->[- _, + _]: Program] 
  extends Aggregatable[[Y] =>> Rec[[X] =>> A[Y, X]], >-->]
  with RecReducerType[A, >-->]:

  override private[psbp] def initialTraverser[Y]: Y >--> Y = `z>-->z`[>-->, Y]

  override private[psbp] def initialReducer[Y]: Reducer[Y, Rec[[X] =>> A[Y, X]]] =
    `a[rec[a]]=>rec[a]` asProgram

  override private[psbp] def fuse[Z, Y, X]: Aggregator[Z, Y, X] => Reducer[Z, X] =
    (`z>-->y`, `a[y,x]>-->x`) =>
      val traversableAtLeft: TraversableAtLeft[A, >-->] = summon[TraversableAtLeft[A, >-->]]
      traversableAtLeft.leftTraversable.traverse(`z>-->y`) >--> `a[y,x]>-->x`

  override def reduce[Y, X]: Reducer[Y, X] => Rec[[X] =>> A[Y, X]] >--> X = 
    `a[y,x]>-->x` =>
      val traversableAtRight: TraversableAtRight[A, >-->] = summon[TraversableAtRight[A, >-->]]
      traversableAtRight.rightTraversable.rec(`a[y,x]>-->x`) 

*/

  // override private[psbp] def initialReducer[Y]: Reducer[Y, RightRec[A][Y]] = 
  //   Rec(_)  

// private[psbp] trait RecInitialTraverser[C[+ _]: Function0Lifting] 
//   extends InitialTraverser[[Z, Y] =>> Z => C[Y]]:

//   private val function0Lifting = summon[Function0Lifting[C]]
//   import function0Lifting.lift0

//   override private[psbp] def initialTraverser[Y]: Y => C[Y] = lift0

// private[psbp] trait RecInitialReducer[A[+ _, + _]: Function1LiftingAtRight] 
//   extends RecReducerType[A]
//   // with InitialReducer[[Y] =>> Rec[[X] =>> A[Y, X]]]:
//   with InitialReducer[RightRec[A]]:

//   // override private[psbp] def initialReducer[Y]: Reducer[Y, Rec[Left[A][Y]]] = 
//   override private[psbp] def initialReducer[Y]: Reducer[Y, RightRec[A][Y]] = 
//     Rec(_)

// private[psbp] trait RecFunctionLevelFusing[A[+ _, + _]: Function1LiftingAtLeft] 
//   extends RecReducerType[A]
//   with FunctionLevelFusing:

//   override private[psbp] def functionLevelFuse[Z, Y, X]: ((Z => Y) && Reducer[Y, X]) => Reducer[Z, X] =
//     case (z2y, reducer) =>
//       val foo: A[Z, X] => A[Y, X] = function1LiftingFromFunction1LiftingAtLeft.lift1(z2y)
//       function1LiftingFromFunction1LiftingAtLeft.lift1(z2y) andThen reducer 

// // private[psbp] type `=>`[C[+ _]] = [Z, Y] =>> Z => C[Y]

// private[psbp] trait RecAggregatable[A[+ _, + _]: Function1LiftingAtLeft: Function1LiftingAtRight, C[+ _]: Function0Lifting: Function1Lifting]
//   extends RecInitialTraverser[C]
//   with RecInitialReducer[A]
//   with RecFunctionLevelFusing[A]
//   with FunctionLevelReducible[RightRec[A], C]
//   with Aggregatable[RightRec[A], [Z, Y] =>> Z => C[Y]]:
  
//   override private[psbp] def functionLevelReduce[Y, X]: Reducer[Y, X] => RightRec[A][Y] => X = 
//     rec
