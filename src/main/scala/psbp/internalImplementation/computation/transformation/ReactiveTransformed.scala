package psbp.internalImplementation.computation.transformation

private[psbp] type ReactiveTransformed[C[+ _]] = [Z] =>> (C[Z] => Unit) => Unit
