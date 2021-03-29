package psbp.external.specification.aggregatable

import psbp.external.specification.program.Program

trait Reducible[A[+ _], >-->[- _, + _]: Program] extends ReducerType: // extends InitialReducer[A]:

  // declared
  
  def reduce[Y, X]: Reducer[Y, X] => A[Y] >--> X

