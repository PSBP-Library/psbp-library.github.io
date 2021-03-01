package psbp.internalSpecification.computation

private[psbp] trait Computation[C[+ _]] 
  extends Resulting[C] 
  with Binding[C]


