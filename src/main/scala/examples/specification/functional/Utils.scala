package examples.specification.functional

import scala.language.postfixOps

import psbp.external.specification.types.&&

import psbp.external.specification.program.Functional

import examples.specification.function

def isZero[
  >-->[- _, + _]: Functional
  ]: BigInt >--> Boolean =  
  function.isZero asProgram

def one[
  >-->[- _, + _]: Functional
  , Z
  ]: Z >--> BigInt =
  function.one asProgram

def subtractOne[
  >-->[- _, + _]: Functional
  ]: BigInt >--> BigInt =
  function.subtractOne asProgram

def multiply[
  >-->[- _, + _]: Functional
  ]: (BigInt && BigInt) >--> BigInt =  
  function.multiply asProgram

def zero[
  >-->[- _, + _]: Functional
  , Z
  ]: Z >--> BigInt =
  function.zero asProgram  

def isOne[
  >-->[- _, + _]: Functional
  ]: BigInt >--> Boolean =  
  function.isOne asProgram  

def subtractTwo[
  >-->[- _, + _]: Functional
  ]: BigInt >--> BigInt =
  function.subtractTwo asProgram  

def add[
  >-->[- _, + _]: Functional
  ]: (BigInt && BigInt) >--> BigInt =  
  function.add asProgram 
  
def isNegative[
  >-->[- _, + _]: Functional
  ]: BigInt >--> Boolean =  
  function.isNegative asProgram 

def negate[
  >-->[- _, + _]: Functional
  ]: BigInt >--> BigInt =
  function.negate asProgram   

def isPositive[>-->[- _, + _]: Functional]: BigInt >--> Boolean =  
  function.isPositive asProgram 

// recursive reducers

// import psbp.external.specification.function.foldSum

import examples.specification.function.{ areAllPositiveReducer }

import psbp.external.specification.program.Functional

import psbp.external.specification.types.||

import psbp.external.specification.program.Program

def areAllPositiveReducer[>-->[- _, + _]: Program]: (Unit || (Boolean && Boolean)) >--> Boolean =
  function.areAllPositiveReducer asProgram


// import psbp.external.specification.aggregatable.recursive.RecursiveReducerType

// import psbp.implementation.list.{ List }

// def areAllPositiveReducer[
//   // R[+_[+ _]],
//   >-->[- _, + _]: Program
//                 // : [>-->[- _, + _]] =>> Recursive[R, >-->]
//                 : [>-->[- _, + _]] =>> RecursiveReducerType[List, >-->]
//   ]: RecursiveList[R, BigInt] >--> Boolean = 

//     val recursiveAggregatable: RecursiveAggregatable[List, R, >-->] = summon[RecursiveAggregatable[List, R, >-->]]
//     import recursiveAggregatable.aggregate

//     // val traverser: BigInt >--> Boolean = isPositive

//     // val reducer: recursiveAggregatable.Reducer[Boolean, Boolean] = 
//     //   foldSum(constantTrue[Unit], and) asProgram