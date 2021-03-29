package psbp.external.implementation.list

import scala.language.postfixOps

import scala.collection.immutable

import psbp.external.specification.types.&&

import psbp.external.specification.function.{ foldSum }

import psbp.external.specification.program.Program

import psbp.external.specification.recursive.Recursive

import psbp.external.implementation.list.{ List, RecursiveList }

import psbp.external.specification.aggregatable.recursive.{ RecursiveAggregatable }

def emptySeq[Z]: Unit => immutable.Seq[Z] = 
  _ =>
    immutable.Seq()

def consSeq[Z]: Z && immutable.Seq[Z] => immutable.Seq[Z] =
  case (z, zs) => 
    z +: zs

import psbp.external.specification.types.||

def recListToSeqReducerFunction[Z]: (Unit || (Z && immutable.Seq[Z])) => immutable.Seq[Z] = 
  foldSum(emptySeq[Z], consSeq[Z]) 
  
def recListToSeqReducer[Z, >-->[- _, + _]: Program]: (Unit || (Z && immutable.Seq[Z])) >--> immutable.Seq[Z] = 
  recListToSeqReducerFunction asProgram

def recListToSeq[
  Z,
  R[+_[+ _]],
  >-->[- _, + _]: Program
                : [>-->[- _, + _]] =>> Recursive[R, >-->]
                : [>-->[- _, + _]] =>> RecursiveAggregatable[List, R, >-->]
  ]: RecursiveList[R, Z] >--> immutable.Seq[Z] = 
    
  val recursiveAggregatable: RecursiveAggregatable[List, R, >-->] = summon[RecursiveAggregatable[List, R, >-->]]
  import recursiveAggregatable.reduce

  // val reducer: recursiveAggregatable.Reducer[Z, immutable.Seq[Z]] = 
  //   toSeqReducer asProgram

  reduce(recListToSeqReducer[Z, >-->])    



