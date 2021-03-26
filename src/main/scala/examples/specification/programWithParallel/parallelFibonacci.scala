package examples.specification.programWithParallel

import psbp.external.specifcation.programWithParallel.ProgramWithParallel

import examples.specification.functional.{ 
  isZero
  , zero
  , isOne
  , one
  , subtractOne
  , subtractTwo
  , add 
  }

def parallelFibonacci[
  >-->[- _, + _]: ProgramWithParallel]
  : BigInt >--> BigInt =

  val programWithParallel: ProgramWithParallel[>-->] =
    summon[ProgramWithParallel[>-->]]
  import programWithParallel.{
    If
  }

  If(isZero) {
    zero
  } Else {
    If(isOne) {
      one
    } Else {
      (subtractOne && subtractTwo) >-->
        (parallelFibonacci |&&&| parallelFibonacci) >-->
        add
    }
  }

  