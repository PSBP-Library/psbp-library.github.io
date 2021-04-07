package examples.specification.functional.recursiveFolderType

import scala.language.postfixOps

import scala.collection.immutable.Seq

import psbp.external.specification.program.Functional

import psbp.external.specification.aggregatable.recursive.RecursiveFolderType

import psbp.external.implementation.list.List

import examples.specification.function

def areAllTrueFolder[
  >-->[- _, + _]: Functional
](
   using recursiveFolderType: RecursiveFolderType[List, >-->]
 ): recursiveFolderType.Folder[Boolean, Boolean] = 

  function.areAllTrueFolder asProgram 

def toSeqFolder[
  Z
  , >-->[- _, + _]: Functional
  ](
    using recursiveFolderType: RecursiveFolderType[List, >-->]
  ): recursiveFolderType.Folder[Z, Seq[Z]] = 

  function.toSeqFolder asProgram  