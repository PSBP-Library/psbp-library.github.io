/*

Just one file now


package psbp.specification.aggregatable

import psbp.specification.types.&&

def functionApplication[Z, Y]: ((Z => Y) && Z) => Y =
  (`z=>y`, z) =>
    `z=>y` apply z

trait Traversable[A[+ _], >-->[- _, + _]]:

  def traverse[Z, Y]: Z >--> Y => A[Z] >--> A[Y]

trait Reducible[A[+ _], R[_, _], >-->[- _, + _]]:

  def reduce[Y, X]: R[Y, X] => A[Y] >--> X

import psbp.specification.types.&&

trait Aggregatable[A[+ _], R[_, _], >-->[- _, + _]] :

  def aggregate[Z, Y, X]: (Z >--> Y && R[Y, X]) => A[Z] >--> X  

private[psbp] trait InitialMapper[>-->[- _, + _]]:

  private[psbp] def initialMap[Y]: Y >--> Y

private[psbp] trait InitialReducer[A[+ _], R[_, _]]:

  private[psbp] def initialReduce[Y]: R[Y, A[Y]]  

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
  >-->[- _, + _]: InitialMapper
                : [>-->[- _, + _]] =>> Aggregatable[A, R, >-->]]: Reducible[A, R, >-->] with

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
    (z2cy, ryx) =>
      functionLevelReduce(functionLevelFuse(z2cy, liftReducer(ryx)))

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
*/