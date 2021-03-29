package examples.specification.program.implementation.recursive.implementation.rec.aggregatable.implementation.list

import psbp.external.specification.program.Program

import psbp.external.specification.aggregatable.recursive.{ RecursiveAggregatable }

import psbp.external.implementation.rec.Rec

import psbp.external.implementation.list.{ List }

import psbp.external.implementation.rec.list.RecList

import psbp.external.implementation.rec.recRecursive
  
import examples.specification.program.implementation.recursive // .aggregatable.implementation.list.{ areAllPositive => foo }

// todo: needed, maybe importing recRecursive later (by need) is enough?

def areAllPositive[
  >-->[- _, + _]: Program
                : [>-->[- _, + _]] =>> RecursiveAggregatable[List, Rec, >-->]
  ]: RecList[BigInt] >--> Boolean = 

  recursive.aggregatable.implementation.list.areAllPositive

