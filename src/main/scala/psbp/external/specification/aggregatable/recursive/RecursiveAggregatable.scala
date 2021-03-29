package psbp.external.specification.aggregatable.recursive

import psbp.external.specification.program.Program

import psbp.external.specification.recursive.{ Recursive }

import psbp.external.specification.aggregatable.{ Aggregatable }

import psbp.internal.specification.aggregatable.{ TraversableAtLeft, TraversableAtRight }

trait RecursiveAggregatable[
  A[+ _, + _]: [A[+ _, + _]] =>> TraversableAtLeft[A, >-->]: [A[+ _, + _]] =>> TraversableAtRight[A, >-->], 
  R[+_[+ _]]: [R[+_[+ _]]] =>> Recursive[R, >-->], 
  >-->[- _, + _]: Program] 
  extends RecursiveReducerType[A, >-->]
  with Aggregatable[[Y] =>> R[[X] =>> A[Y, X]], >-->]
