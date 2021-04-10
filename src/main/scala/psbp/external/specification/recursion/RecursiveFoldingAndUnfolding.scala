package psbp.external.specification.recursion

private[psbp] trait RecursiveFoldingAndUnfolding[
  A[+ _]
  , >-->[- _, + _]
]:
  private[psbp] def recursiveFold[
    Y 
    , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  ]: (A[Y] >--> Y) => (R[A] >--> Y)

  private[psbp] def recursiveUnfold[
    Z
    , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  ]: (Z >--> A[Z]) => (Z >--> R[A])
