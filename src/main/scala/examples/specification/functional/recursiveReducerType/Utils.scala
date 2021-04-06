package examples.specification.functional.recursiveReducerType

import scala.language.postfixOps

import scala.collection.immutable.Seq

import psbp.external.specification.program.Functional

import psbp.external.specification.aggregatable.recursive.RecursiveReducerType

import psbp.external.implementation.list.List

import examples.specification.function

def areAllTrueReducer[
  >-->[- _, + _]: Functional
](
   using recursiveReducerType: RecursiveReducerType[List, >-->]
 ): recursiveReducerType.Reducer[Boolean, Boolean] = 

  function.areAllTrueReducer asProgram 

def toSeqReducer[
  Z
  , >-->[- _, + _]: Functional
  ](
    using recursiveReducerType: RecursiveReducerType[List, >-->]
  ): recursiveReducerType.Reducer[Z, Seq[Z]] = 

  function.toSeqReducer asProgram  