package examples.specification.function

import psbp.specification.program.&&

val isZero: BigInt => Boolean =
  n =>
   n == 0
  
def one[Z]: Z => BigInt =
  _ =>
    1
  
val subtractOne: BigInt => BigInt =
  n =>
    n - 1
    
val multiply: BigInt && BigInt => BigInt =
  (n, m) =>
    n * m

def zero[Z]: Z => BigInt =
  _ =>
    0

val isOne: BigInt => Boolean =
  n =>
   n == 1
   
val subtractTwo: BigInt => BigInt =
  n =>
    n - 2   

val add: BigInt && BigInt => BigInt =
  (n, m) =>
    n + m