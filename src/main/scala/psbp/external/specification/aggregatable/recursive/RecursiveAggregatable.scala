package psbp.external.specification.aggregatable.recursive

import psbp.external.specification.program.Program

import psbp.external.specification.recursive.{ Recursive }

import psbp.external.specification.aggregatable.{ Aggregatable }

// todo: maybe not needed
import psbp.internal.specification.aggregatable.{ BiTraversable }

trait RecursiveAggregatable[
  A[+ _, + _]: [A[+ _, + _]] =>> BiTraversable[A, >-->], 
  R[+_[+ _]]: [R[+_[+ _]]] =>> Recursive[R, >-->], 
  >-->[- _, + _]: Program] 
  extends RecursiveReducerType[A, >-->]
  with Aggregatable[[Y] =>> R[[X] =>> A[Y, X]], >-->]
