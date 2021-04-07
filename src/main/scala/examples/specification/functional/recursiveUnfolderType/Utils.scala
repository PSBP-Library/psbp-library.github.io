package examples.specification.functional.recursiveUnfolderType

import scala.language.postfixOps

import scala.collection.immutable.Seq

import psbp.external.specification.program.Functional

import psbp.external.specification.aggregatable.recursive.RecursiveUnfolderType

import psbp.external.implementation.list.List

import examples.specification.function 

def fromSeqUnfolder[
  Z
  , >-->[- _, + _]: Functional
  ](
    using recursiveUnfolderType: RecursiveUnfolderType[List, >-->]
  ): recursiveUnfolderType.Unfolder[Seq[Z], Z] = 

  function.fromSeqUnfolder asProgram    