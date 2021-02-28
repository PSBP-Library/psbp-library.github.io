package psbp.internalSpecification.computation.transformation

private[psbp] type StateTransformed[S, C[+ _]] = [Z] =>> S => C[(S, Z)]
