package psbp.external.implementation.list

import psbp.internal.implementation.structure.recursive.{ 
  Sum
  , None
  , Product
  , Constant
  , Identity
}

type List[+Y, +X] = Sum[None, Product[Constant, Identity]][Y, X]

// import psbp.external.specification.program.Program

// import psbp.external.specification.recursion.Recursion

// import psbp.external.specification.structure.recursive.Recursive

// def recursiveListToList[
//   Z
//   , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
//   , >-->[- _, + _]: Program
// ]: Recursive[R, List][Z] >--> List[Z, Recursive[R, List][Z]] = 

//   val recursion: Recursion[R, >-->] = summon[Recursion[R, >-->]]

//   recursion.`r[a]>-->a[r[a]]`

// def listToRecursiveList[
//   Z
//   , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
//   , >-->[- _, + _]: Program
// ]: List[Z, Recursive[R, List][Z]] >--> Recursive[R, List][Z] = 

//   val recursion: Recursion[R, >-->] = summon[Recursion[R, >-->]]

//   recursion.`a[r[a]]>-->r[a]`