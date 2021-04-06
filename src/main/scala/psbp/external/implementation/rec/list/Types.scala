package psbp.external.implementation.rec.list

import psbp.external.specification.function.{ `z=>(z||y)`, `y=>(z||y)` }

import psbp.external.implementation.rec.{
  Rec
  , `a[rec[a]]=>rec[a]`
  }

import psbp.external.specification.aggregatable.recursive.Recursive

import psbp.external.implementation.list.List

type RecursiveList[+Z] = Recursive[Rec][List][Z]

def listToRecursiveList[Z]: List[Z, RecursiveList[Z]] => RecursiveList[Z] = 
  `a[rec[a]]=>rec[a]`

def emptyRecursiveList[Z]: RecursiveList[Z] =
  listToRecursiveList[Z](`z=>(z||y)`(()))

import psbp.external.specification.types.&&

def consRecursiveList[Z]: (Z && RecursiveList[Z]) => RecursiveList[Z] =
  `y=>(z||y)` andThen listToRecursiveList

