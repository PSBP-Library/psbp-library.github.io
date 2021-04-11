package psbp.external.implementation.list

import psbp.external.specification.program.Program

import psbp.external.specification.structure.recursive.RecursiveStructure

import psbp.internal.specification.structure.recursive.recursiveStructureFromBiTraversable

import psbp.external.specification.recursion.Recursion

given givenListRecursiveStructure[
  R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Program
  ]: RecursiveStructure[List, R, >-->] = 
    recursiveStructureFromBiTraversable[List, R, >-->]
