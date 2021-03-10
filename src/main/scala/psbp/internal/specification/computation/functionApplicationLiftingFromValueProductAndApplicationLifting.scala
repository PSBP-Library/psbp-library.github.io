package psbp.internal.specification.computation

given functionApplicationLiftingFromValueProductAndApplicationLifting[
  C[+ _]: ValueLifting: ProductLifting: ApplicationLifting]: FunctionApplicationLifting[C] with

  private val valueLifting: ValueLifting[C] = summon[ValueLifting[C]]

  private val productLifting: ProductLifting[C] = summon[ProductLifting[C]]

  private val applicationLifting: ApplicationLifting[C] = summon[ApplicationLifting[C]]

  export valueLifting.liftValue
  export productLifting.liftProduct2 
  export applicationLifting.liftApply