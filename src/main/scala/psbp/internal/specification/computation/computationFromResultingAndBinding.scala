package psbp.internal.specification.computation

private[psbp] given computationFromResultingAndBinding[
  C[+ _]: Resulting
        : Binding
]: Computation[C] with

  private val resulting: Resulting[C] = summon[Resulting[C]]

  export resulting.result

  private val binding: Binding[C] = summon[Binding[C]]

  export binding.bind