/*

Just one file now no documentation yet

*/

package psbp.specification.aggregatable

import psbp.specification.types.&&

private[psbp] trait InitialMapper[>-->[- _, + _]]:

  private[psbp] def initialMap[Y]: Y >--> Y

private[psbp] trait InitialReducer[A[+ _], R[_, _]]:

  private[psbp] def initialReduce[Y]: R[Y, A[Y]]
  
trait Aggregatable[A[+ _], R[_, _], >-->[- _, + _]]:

  def aggregate[Z, Y, X]: (Z >--> Y && R[Y, X]) => A[Z] >--> X  

trait Traversable[A[+ _], >-->[- _, + _]]:

  def traverse[Z, Y]: Z >--> Y => A[Z] >--> A[Y]

trait Reducible[A[+ _], >-->[- _, + _], R[_, _]]:

  def reduce[Y, X]: R[Y, X] => A[Y] >--> X

given traversableFromAggregatable[
  A[+ _], 
  R[_, _]: [R[_, _]] =>> InitialReducer[A, R], 
  >-->[- _, + _]: [>-->[- _, + _]] =>> Aggregatable[A, R, >-->]]: Traversable[A, >-->] with

  private val aggregatable: Aggregatable[A, R, >-->] = summon[Aggregatable[A, R, >-->]]
  import aggregatable.aggregate

  private val initialReducer: InitialReducer[A, R] = summon[InitialReducer[A, R]]
  import initialReducer.initialReduce 
  
  override def traverse[Z, Y]: Z >--> Y => A[Z] >--> A[Y] = 
    aggregate(_, initialReduce)


given reducibleFromAggregatable[
  A[+ _], 
  R[_, _], 
  >-->[- _, + _]: InitialMapper: [>-->[- _, + _]] =>> Aggregatable[A, R, >-->]]: Reducible[A, >-->, R] with

  private val aggregatable: Aggregatable[A, R, >-->] = summon[Aggregatable[A, R, >-->]]
  import aggregatable.aggregate

  private val initialMapper: InitialMapper[>-->] = summon[InitialMapper[>-->]]
  import initialMapper.initialMap
  
  override def reduce[Y, X]: R[Y, X] => A[Y] >--> X = 
    aggregate(initialMap, _)

private[psbp] trait FunctionLevelFusing[R[_, _]]:
  
  private[psbp] def functionLevelFuse[Z, Y, X]: ((Z => Y) && R[Y, X]) => R[Z, X]  

private[psbp] trait FunctionLevelReducible[A[+ _], R[_, _]]:

  private[psbp] def functionLevelReduce[Y, X]: R[Y, X] => A[Y] => X

private[psbp] trait ReducerLifting[R[_, _], C[+ _]]:
    
  private[psbp] def liftReducer[Y, X]: R[Y, X] => R[C[Y], C[X]]

given functionLevelAggregatableFromFunctionLevelReducible[
  A[+ _], 
  R[_, _]: FunctionLevelFusing
          : [R[_, _]] =>> FunctionLevelReducible[A, R],
  C[+ _]: [C[+ _]] =>> ReducerLifting[R, C]
  ]: Aggregatable[A, [Y, X] =>> R[Y, X], [Z, Y] =>> Z => C[Y]] with
  
  private type `=>C` = [Z, Y] =>> Z => C[Y] 
    
  private val functionLevelFusing: FunctionLevelFusing[R] = summon[FunctionLevelFusing[R]]
  import functionLevelFusing.functionLevelFuse 

  private val functionLevelReducible: FunctionLevelReducible[A, R] = summon[FunctionLevelReducible[A, R]]
  import functionLevelReducible.functionLevelReduce 

  private val reducerLifting: ReducerLifting[R, C] = summon[ReducerLifting[R, C]]
  import reducerLifting.liftReducer

  override def aggregate[Z, Y, X]: (Z `=>C` Y && R[Y, X]) => A[Z] `=>C` X =
    case (z2cy, ryx) =>
      functionLevelReduce(functionLevelFuse(z2cy, liftReducer(ryx)))

private[psbp] trait ValueLifting[C[+ _]]:

  private[psbp] def lift0[Z]: Z => C[Z]

private[psbp] trait OperatorLifting[C[+ _]]:

  private[psbp] def lift2[Z, Y, X]: ((Z && Y) => X) => (C[Z] && C[Y]) => C[X] 

private[psbp] trait Lifting[C[+ _]] extends ValueLifting[C] with OperatorLifting[C]

private[psbp] trait FunctionLifting[C[+ _]]:

  private[psbp] def lift1[Z, Y]: (Z => Y) => C[Z] => C[Y]

private[psbp] trait MoreLifting[C[+ _]]:

  private[psbp] def lift3[Z, Y, X, W]: ((Z && Y && X) => W) => (C[Z] && C[Y] && C[X]) => C[W] 

  // lift4, lift5, ...

import psbp.specification.function.{ `((z=>y)&&z)=>y`, `(z&&y)=>(z&&y)`, `(z=>y)=>((z&&x)=>(y&&x)))`, `z=>(z||y)`, `y=>(z||y)`, and, or }

import psbp.specification.types.|| // later

private[psbp] trait LiftingUtils[C[+ _]]:

  private[psbp] def liftApply[Z, Y]: (C[Z => Y] && C[Z]) => C[Y]

  private[psbp] def liftProduct[Z, Y]: (C[Z] && C[Y]) => C[Z && Y]

  private[psbp] def liftSum[Z, Y]: (C[Z] || C[Y]) => C[Z || Y]

  private[psbp] def liftAnd[Z, Y, X, W]: ((Z => C[X]) && (Y => C[W])) => ((Z && Y) => C[X && W])

  private[psbp] def liftOr[Z, Y, X, W]: ((Z => C[X]) && (Y => C[W])) => ((Z || Y) => C[X || W])

given liftingUtils[C[+ _]: Lifting]: LiftingUtils[C] with FunctionLifting[C] with MoreLifting[C] with

  private val lifting: Lifting[C] = summon[Lifting[C]]
  import lifting.{ lift0, lift2 }

  override private[psbp] def liftApply[Z, Y]: (C[Z => Y] && C[Z]) => C[Y] =
    lift2(`((z=>y)&&z)=>y`)

  override private[psbp] def liftProduct[Z, Y]: (C[Z] && C[Y]) => C[Z && Y] =
    lift2(`(z&&y)=>(z&&y)`)

  override private[psbp] def liftSum[Z, Y]: (C[Z] || C[Y]) => C[Z || Y] =
    _.foldSum(lift1(`z=>(z||y)`), lift1(`y=>(z||y)`))      

  override private[psbp] def liftAnd[Z, Y, X, W]: ((Z => C[X]) && (Y => C[W])) => ((Z && Y) => C[X && W]) =
    (`z=>cx`, `y=>cw`) =>
      and(`z=>cx`, `y=>cw`) andThen liftProduct       

  override private[psbp] def liftOr[Z, Y, X, W]: ((Z => C[X]) && (Y => C[W])) => ((Z || Y) => C[X || W]) =
    (`z=>cx`, `y=>cw`) =>
      or(`z=>cx`, `y=>cw`) andThen liftSum 

  override private[psbp] def lift1[Z, Y]: (Z => Y) => C[Z] => C[Y] =
    `z=>y` => 
      cz =>
        liftApply(lift0(`z=>y`), cz)  

  override private[psbp] def lift3[Z, Y, X, W]: ((Z && Y && X) => W) => (C[Z] && C[Y] && C[X]) => C[W] =
    `((z&&y)&&x)=>w` =>
      `(z=>y)=>((z&&x)=>(y&&x)))`(liftProduct) andThen lift2(`((z&&y)&&x)=>w`)

  // lift4, lift5, ...

import psbp.internal.specification.computation.Computation

given liftingFromComputation[C[+ _]: Computation]: Lifting[C] with

  private val computation: Computation[C] = summon[Computation[C]]
  import computation.{ result, bind }

  override private[psbp] def lift0[Z]: Z => C[Z] =
    result
  
  override private[psbp] def lift2[Z, Y, X]: ((Z && Y) => X) => (C[Z] && C[Y]) => C[X] =
    `(z&&y)=>x` =>
      (cz, cy) =>
        cz >= { z => 
          cy >= { y => 
            result(`(z&&y)=>x`(z, y)) 
          } 
        } 

private[psbp] case class Fix[+A[+ _]: FunctionLifting](`a[fix[a]]`: A[Fix[A]]) {

  private val functionLifting = summon[FunctionLifting[A]]
  import functionLifting.lift1

  private[psbp] def fix[X]: (A[X] => X) => X = { `a[x]=>x` =>
    (lift1((_: Fix[A]).fix(`a[x]=>x`)) andThen `a[x]=>x`)(`a[fix[a]]`)
  }

}

private[psbp] def fix[A[+ _]: FunctionLifting, X]: (A[X] => X) => Fix[A] => X = {
  `a[x]=>x` =>
    `fix[a]` =>
      `fix[a]`.fix(`a[x]=>x`)
}  
