<style type="text/css"> body { margin: auto; max-width: 44em; font-family: Calibri, sans-serif; font-size: 18pt; } /* automatic heading numbering */ h1 { counter-reset: h2counter; } 
h2 { counter-reset: h3counter; } 
h3 { counter-reset: h4counter; } 
h4 { counter-reset: h5counter; } 
h5 { counter-reset: h6counter; }
h6 { } 
h2:before { counter-increment: h2counter; content: counter(h2counter) ".\0000a0\0000a0"; } 
h3:before { counter-increment: h3counter; content: counter(h2counter) "." counter(h3counter) ".\0000a0\0000a0"; } 
h4:before { counter-increment: h4counter; content: counter(h2counter) "." counter(h3counter) "." counter(h4counter) ".\0000a0\0000a0"; } 
h5:before { counter-increment: h5counter; content: counter(h2counter) "." counter(h3counter) "." counter(h4counter) "." counter(h5counter) ".\0000a0\0000a0"; } 
h6:before { counter-increment: h6counter; content: counter(h2counter) "." counter(h3counter) "." counter(h4counter) "." counter(h5counter) "." counter(h6counter) ".\0000a0\0000a0"; } 
</style>


# Application development

## Programs

### `factorial`

```scala
package examples.specification.program

import psbp.external.specification.program.Program

import examples.specification.functional.{ 
  isZero
  , one
  , subtractOne
  , multiply
}

def factorial[
  >-->[- _, + _]: Program
]: BigInt >--> BigInt =

  val program: Program[>-->] = 
    summon[Program[>-->]]
  import program.{
    Let
    , If
  }

  If(isZero) {
    one
  } Else {
    Let {
      subtractOne >--> factorial
    } In {
      multiply
    }
  }
```

where

```scala
package examples.specification.functional

import scala.language.postfixOps

import psbp.external.specification.types.&&

import psbp.external.specification.program.Functional

import examples.specification.function

def isZero[
  >-->[- _, + _]: Functional
]: BigInt >--> Boolean = 
  function.isZero asProgram
  
def one[
  Z
  , >-->[- _, + _]: Functional
]: Z >--> BigInt =
  function.one asProgram

def subtractOne[
  >-->[- _, + _]: Functional
]: BigInt >--> BigInt =
  function.subtractOne asProgram

def multiply[
  >-->[- _, + _]: Functional
]: (BigInt && BigInt) >--> BigInt =  
  function.multiply asProgram

// ...
```

are functional utilities,

where

```scala
package examples.specification.function

import psbp.external.specifcation.types.&&

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

// ...
```

are function utilities.

`factorial` is a program that makes use of the following programming ingredients 

- `asProgram` from `Functional`
- `>-->` from `Composition`
- `Let {} In {}` local values, equivalent to `&&` from `Construction`
- `If() {} Else {}` if-then-else logic, equivalent to `||` from `Condition`

See 

- [Functional](https://psbp-library.github.io#functional)
- [Composition](https://psbp-library.github.io#composition)
- [Construction](https://psbp-library.github.io#construction)
- [Condition](https://psbp-library.github.io#condition) 

and 

- [Program](https://psbp-library.github.io#program) 

for more details.

### Complexity versus abstraction

`factorial` could also have been defined as

```scala
val factorial: BigInt => BigInt =
  i =>
    if(i == 0) {
      1
    } else { 
      val j = factorial(i - 1)
      i * j
    }

// ...    
```

and `mainFactorial` below

```scala
@main def mainFactorial(args: String*): Unit =
  println("Please type an integer")
  val i: BigInt = BigInt(scala.io.StdIn.readInt)
  println(s"applying factorial to the integer argument ${i} yields result ${factorial(i)}")
```

could have been run as

```scala
Please type an integer
10
applying factorial to the integer argument 10 yields result 3628800
[success] 
```

 Let's compare the pointfree definition, purely from a developer point of view, not even considering any other benefits or drawbacks

```scala
  If(isZero) {
    one
  } Else {
    Let {
      subtractOne >--> factorial
    } In {
      multiply
    }
  }
```

with the pointful definition

```scala
val factorial: BigInt => BigInt =
  i =>
    if(isZero(i)) {
      one(i)
    } else { 
      val j = factorial(subtractOne(i))
      multiply(i, j)
    }
```

First, and, from a developer point of view, most important, the pointfree definition 

- does not make use of parameter `i`.

Second, and, from a developer point of view, less important, the pointfree definition 

- makes use of *library-level syntax* `If() {} Else {}` instead of *language-level `if() {} else {}` syntax* `val z = e` for if-then-else logic.

Third, and, from a developer point of view, also important, the pointfree definition 

- makes use of *library-level syntax* `Let {} In {}` instead of *language-level syntax* `val j = ...` for local values.

The pointfree definition is more *abstract* than the pointful one (where are `i` and `j` gone to?).

On the one hand using abstraction is an *advantage* because it simplifies *complexity*.

Compexity needs to be *handled*, which is difficult for *most* human beings.

On the other hand using abstraction is a *disadvantage*.

Abstraction needs to be *understood*, which is difficult for *some* human beings.

But here is the thing: many *different* definitions have *similar* complexity.

Handling the complexity of different definitions with similar complexity has to be done *over and over again*.

Understanding the abstraction of similar complexity of different definitions has to be done *only once*.

### Explaining pointfree `factorial` in a pointful way

Let's explain the pointfree definition of `factorial`, for now thinking of programs as functions, in a pointful way using a parameter `i`.

At level `1`, `factorial` looks like

```scala
  If(isZero) {
    // level 2.1
  } Else {
    // level 2.2
  }
```

If `isZero(i)`, in other words, if `i == 0` then, 

at level `2.1`, `factorial` looks like

```scala
    one
```

The result is `one(i)`, which equals `1`.

Otherwise, in other words, if `i != 0` then, 

at level `2.2`, `factorial` looks like

```scala
    Let {
      // level 3.1
    } In {
      // level 3.2
    }
```

At level `3.1`, `factorial` looks like

```scala
      subtractOne >--> factorial
```

a local value `j`, which equals `(subtractOne >--> factorial)(i)`, which equals `factorial(subtractOne(i))`, which equals `factorial(i - 1)` is defined, which is available as the second component of a product-value `(i, j)`.

At level `3.2`, `factorial` looks like

```scala
      multiply
```

The result is `multiply(i, j)`, which equals `multiply(i, factorial(i - 1))`, which equals `i * factorial(i - 1)`.

### `fibonacci`

```scala
package examples.specification.program

import psbp.external.specification.program.Program

import examples.specification.functional.{ 
  isZero
  , zero
  , isOne
  , one
  , subtractOne
  , subtractTwo
  , add
}

def fibonacci
  [>-->[- _, + _]: Program
]: BigInt >--> BigInt =

  val program: Program[>-->] = 
    summon[Program[>-->]]
  import program.If

  If(isZero) {
    zero
  } Else {
    If(isOne) {
      one
    } Else {
      (subtractOne && subtractTwo) >-->
        (fibonacci &&& fibonacci) >-->
        add
    }
  }
```

where

```scala
package examples.specification.functional

// ..

def zero[
  Z
  , >-->[- _, + _]: Functional
]: Z >--> BigInt =
  function.zero asProgram  

def isOne[
  >-->[- _, + _]: Functional
]: BigInt >--> Boolean =  
  function.isOne asProgram  

def subtractTwo[
  >-->[- _, + _]: Functional
]: BigInt >--> BigInt =
  function.subtractTwo asProgram  

def add[
  >-->[- _, + _]: Functional
]: (BigInt && BigInt) >--> BigInt =  
  function.add asProgram 

// ...
```
are functional utilities,

where

```scala
package examples.specification.function

// ...

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

// ...
```

are function utilities.

`fibonacci` is a program that makes use of the following programming ingredients 

- `asProgram` from `Functional`
- `>-->` from `Composition`
- `&&` and `&&&` from `Construction`
- `If() {} Else {}` if-then-else logic, equivalent to `||` from `Condition`

See 

- [Functional](https://psbp-library.github.io#functional)
- [Composition](https://psbp-library.github.io#composition)
- [Construction](https://psbp-library.github.io#construction)
- [Condition](https://psbp-library.github.io#condition) 

and 

- [Program](https://psbp-library.github.io#program) 

for more details.

### Explaining pointfree `fibonacci` in a pointful way

Let's explain the pointfree definition of `fibonacci`, for now thinking of programs as functions, in a pointful way using a parameter `i`.

At level `1`, `fibonacci` looks like

```scala
  If(isZero) {
    // level 2.1
  } Else {
    // level 2.2
  }
```

If `isZero(i)`, in other words, if `i == 0` then, 

at level `2.1`, `fibonacci` looks like

```scala
    zero
```

The result is `zero(i)`, which equals `0`.

Otherwise, in other words, if `i != 0` then, 

at level `2.2`, `fibonacci` looks like

```scala
    If(isOne) {
      // level 3.1
    } Else {
      // level 3.2
    }
```

If `isOne(i)` in other words if `i == 1` then, 

at level `3.1`, `fibonacci` looks like

```scala
      one
```

The result is `one(i)`, which equals `1`.

Otherwise, in other words, if `i != 1` (and `i != 0`) then, 

at level `3.2`, `fibonacci` looks like

```scala
      /* level 4.1 */ >-->
        /* level 4.2 */  >-->
        /* level 4.3 */ 
```

At level `4.1` `fibonacci` looks like

```scala
      subtractOne && subtractTwo
```

A first product-value `(subtractOne && subtractTwo)(i)`, which equals `(subtractOne(i), subtractTwo(i))`, which equals `(i - 1, i - 2)`, is constructed.

At level `4.2` `fibonacci` looks like

```scala
        fibonacci &&& fibonacci
```

A second product-value `(fibonacci &&& fibonacci)(i - 1, i - 2)` which equals `(fibonacci(i - 1), fibonacci(i - 2))` is constructed.

At level `4.3` `fibonacci` looks like

```scala
        add
```

The result is `add(fibonacci(i - 1), fibonacci(i - 2))` which equals `fibonacci(i - 1) + fibonacci(i - 2)`.


### `optimize`

```scala
package examples.specification.program
  
import psbp.external.specification.types.&&

import psbp.external.specification.program.Program

import psbp.external.specification.functional.{
  `(z&&y)>-->z` => argument
  , `(z&&y)>-->y` => accumulator
}

def optimize[
  >-->[- _, + _]: Program
  , A
  , Z
  , Y
](
  accumulatorInitializer: Z >--> A,
  argumentPredicate: Z >--> Boolean,
  updater: (Z && A) >--> (Z && A),
  resultExtractor: A >--> Y
): Z >--> Y =

  val program: Program[>-->] = 
    summon[Program[>-->]]
  import program.{ 
    Let
    , If
  }

  lazy val updateUntilArgumentPredicateHolds: (Z && A) >--> A =
    If(argument >--> argumentPredicate) {
      accumulator
    } Else {
      updater >--> updateUntilArgumentPredicateHolds
    }
    
  Let {
    accumulatorInitializer
  } In {
    updateUntilArgumentPredicateHolds >--> resultExtractor
  }
```

On the one hand, from a developer point of view, recursion is not a big deal in the program specification based programming world.

Both `factorial` (see [factorial above](https://psbp-library.github.io#factorial)) and `fibonacci` (see [fibonacci above](https://psbp-library.github.io#fibonacci)) are defined using a `val`.

On the other hand, from a performance point of view, recursive programs like `factorial` and `fibonacci` should be optimized using an *accumulator* as `optimizedFactorial` (see [optimizedFactorial below](https://psbp-library.github.io#optimizedfactorial)) and `optimizedFibonacci` (see [optimizedFibonacci below](https://psbp-library.github.io#optimizedfibonacci)). 

Especially for `fibonacci` this is a relevant optimization.

The good news is that this can be done, once and for all, at the right level of abstraction.

Let's explain `optimize`.

The *projection programs* with typeful names, `` `(z&&y)>-->z` `` and `` `(z&&y)>-->y` ``, are `Functional` and `Construction` related utilities that are given meaningful names.

See [Program](https://psbp-library.github.io#program) for more details.

```scala
  Let {
    accumulatorInitializer
  } In {
    updateUntilArgumentPredicateHolds >--> resultExtractor
  }
```

This program fragment initializes an accumulator using `accumulatorInitializer`, updates both the argument and the accumulator until an argument predicate holds using `updateUntilArgumentPredicateHolds` and then extracts the result using `resultExtractor`.

```scala
  lazy val updateUntilArgumentPredicateHolds: (Z && A) >--> A =
    If(argument >--> argumentPredicate) {
      accumulator
    } Else {
      updater >--> updateUntilArgumentPredicateHolds
    }
```

If the argument predicate `argumentPredicate` holds for argument `argument`, then the result of this program fragment is the accumulator `accumulator`, otherwise, the result of this program fragment is yielded by updating using `updater` and then, recursively, updating until the argument predicate holds.

Note that `updateUntilArgumentPredicateHolds` is a `lazy val` in order to let it be evaluated by need, as second argument of the sequential composition operator `>-->`.

### `optimizedFactorial`

```scala
package examples.specification.program

import psbp.external.specification.program.Program

import psbp.external.specification.functional.{ 
  `(z&&y)>-->z` => argument
  , `z>-->z` => accumulator 
}

import examples.specification.functional.{ 
  isZero
  , one
  , subtractOne
  , multiply 
}

import examples.specification.program.optimize

def optimizedFactorial[
  >-->[- _, + _]: Program
]: BigInt >--> BigInt =
  optimize(
    accumulatorInitializer = one, 
    argumentPredicate = isZero, 
    updater = (argument >--> subtractOne) && multiply, 
    resultExtractor = accumulator
  )
```

See [optimize above](https://psbp-library.github.io#optimize) for more details.

For `optimizedFactorial` the accumulator type is `BigInt`.

One `BigInt` optimizes one recursion occurrence.

This is not really an enormous optimization.

### `optimizedFibonacci`

```scala
package examples.specification.program

import psbp.external.specification.program.Program

import psbp.external.specification.functional.{ 
  `(z&&y)>-->z` => firstAccumulator
  , `(z&&y)>-->y` => secondAccumulator
}

import examples.specification.functional.{ 
  isZero
  , zero
  , isOne
  , one
  , subtractOne
  , subtractTwo
  , add 
}

import examples.specification.program.optimize

def optimizedFibonacci[
  >-->[- _, + _]: Program
]: BigInt >--> BigInt =
  optimize(
    accumulatorInitializer = one && one, 
    argumentPredicate = isZero, 
    updater = subtractOne &&& (secondAccumulator && add), 
    resultExtractor = firstAccumulator
  )
```

See [optimize above](https://psbp-library.github.io#optimize) for more details.

For `optimizedFibonacci` the accumulator type is `BigInt && BigInt`.

Two `BigInt`'s optimize two recursion occurrences. 

This is really an enormous optimization.


### `random`

```scala
package examples.specification.programWithState

import scala.language.postfixOps

import psbp.external.specification.functional.identity

import psbp.external.specification.programWithState.ProgramWithState

import examples.specification.functional.{ 
  isNegative
  , negate 
  }

type Seed = Long

def random[
  >-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithState[Seed, >-->]
]: Unit >--> BigInt =

  val programWithSeedState: ProgramWithState[Seed, >-->] =
    summon[ProgramWithState[Seed, >-->]]
  import programWithSeedState.{
    If
    , readStateModifiedWith
    }

  object function {

    val seedModifier: Seed => Seed = 
      seed =>
        (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
  
    val seed2randomBigInt: Seed => BigInt = 
      seed =>
        BigInt((seed >>> 16).toInt) 
        
    val moduloSomeBigInt: BigInt => BigInt =
      n =>
        n % BigInt("9876543210")

  }    

  val readModifiedSeed: Unit >--> Seed = 
    readStateModifiedWith(function.seedModifier asProgram)  

  val seed2randomBigInt: Seed >--> BigInt =
    function.seed2randomBigInt asProgram

  val negateIfNegative: BigInt >--> BigInt =
    If(isNegative) {
      negate
    } Else {
      identity
    } 
  
  val moduloSomeBigInt: BigInt >--> BigInt =
    function.moduloSomeBigInt asProgram 

  readModifiedSeed >--> 
    seed2randomBigInt >--> 
      negateIfNegative >--> 
        moduloSomeBigInt
```

where

```scala
package examples.specification.functional

// ...

def isNegative[
  >-->[- _, + _]: Functional
]: BigInt >--> Boolean =  
  function.isNegative asProgram 

def negate[
  >-->[- _, + _]: Functional
]: BigInt >--> BigInt =
  function.negate asProgram 

// ...  
```

are functional utilities,

where

```scala
package examples.specification.function

// ...

val isNegative: BigInt => Boolean =
  n =>
   n < 0

val negate: BigInt => BigInt =
  n =>
    -n

// ...    
```

are function utilities

While, somehow, transforming its dummy argument to a result, `random` internally modifies a seed computation state along the way.

The implementation details are not important (in fact, `random` is not really a good random number generation program at all).

`random` is a program that makes use of the following programming ingredients 

- `asProgram` from `Functional`
- `>-->` from `Composition`
- `If() {} Else {}` if-then-else logic, equivalent to `||` from `Condition`

and

- `readStateModifiedWith` from `ProgramWithState`

See

- [State](https://psbp-library.github.io#state)
- [ProgramWithState](https://psbp-library.github.io#programwithstate) 

for more details.

### `twoRandoms`

```scala
package examples.specification.programWithState

import psbp.external.specification.types.&&

import psbp.external.specification.programWithState.ProgramWithState

def twoRandoms[
  >-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithState[Seed, >-->]
]: Unit >--> (BigInt && BigInt) =  
  random && random
```

Illustrating statefulness can be done using `twoRandoms`, transforming its dummy argument to *two* random number results.


### `parallelFibonacci`

```scala
package examples.specification.programWithParallel

import psbp.external.specification.programWithParallel.ProgramWithParallel

import examples.specification.functional.{ 
  isZero
  , zero
  , isOne
  , one
  , subtractOne
  , subtractTwo
  , add 
}

def parallelFibonacci[
  >-->[- _, + _]: ProgramWithParallel
]: BigInt >--> BigInt =

  val programWithParallel: ProgramWithParallel[>-->] =
    summon[ProgramWithParallel[>-->]]
  import programWithParallel.If

  If(isZero) {
    zero
  } Else {
    If(isOne) {
      one
    } Else {
      (subtractOne && subtractTwo) >-->
        (parallelFibonacci |&&&| parallelFibonacci) >-->
        add
    }
  }
```

The definition of `parallelFibonacci` differs from the definion of `fibonacci` by its usage of `|&&&|` instead of `&&&`.

See

- [Parallel](https://psbp-library.github.io#parallel)
- [ProgramWithParallel](https://psbp-library.github.io#programwithparallel) 

for more details.

### `areAllPositive`

```scala
package examples.specification.program.recursive.structure.implementation.list

import psbp.external.specification.program.Program 

import psbp.external.specification.recursion.Recursion

import psbp.external.specification.structure.recursive.{
  Recursive
  , RecursiveStructure
}

import psbp.external.implementation.list.List

import examples.specification.functional.isPositive

import examples.specification.functional.recursiveFolderType.areAllTrueFolder
  
def areAllPositive[
  R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Program
                  : [>-->[- _, + _]] =>> RecursiveStructure[List, R, >-->]
]: Recursive[R][List][BigInt] >--> Boolean = 

  val structure: RecursiveStructure[List, R, >-->] = 
    summon[RecursiveStructure[List, R, >-->]]
  import structure.aggregate

  aggregate(isPositive, areAllTrueFolder[>-->]) 
```

where

```scala
package examples.specification.functional

// ...

def isPositive[
  >-->[- _, + _]: Functional
]: BigInt >--> Boolean =  
  function.isPositive asProgram 

// ...  
```

and

```scala
package examples.specification.functional.recursiveFolderType

import scala.language.postfixOps

import psbp.external.specification.program.Functional

import psbp.external.specification.structure.recursive.RecursiveFolderType

import psbp.external.implementation.list.List

import examples.specification.function

def areAllTrueFolder[
  >-->[- _, + _]: Functional
](
   using recursiveFolderType: RecursiveFolderType[List, >-->]
 ): recursiveFolderType.Folder[Boolean, Boolean] = 

  function.areAllTrueFolder asProgram 
```

are functional utilities, 

where 

```scala
package examples.specification.function

import psbp.external.specification.types.{
  &&
  , ||
}  

import psbp.external.specification.function.foldSum

import psbp.external.implementation.list.List

// ...

val isPositive: BigInt => Boolean =
  n =>
   n > 0  
       
def constantTrue[Z]: Z => Boolean =
  _ =>
    true 

def and: (Boolean && Boolean) => Boolean = 
  (b, a) =>
    b && a 
    
val areAllTrueFolder: List[Boolean, Boolean] => Boolean =
  foldSum(constantTrue, and)

// ...  
```

are function utilities,

where

```scala
package psbp.external.specification.function

import psbp.external.specification.types.{ 
  &&
  , ||
}

// ...
    
// condition

// ...

def foldSum[Z, Y, X]: ((Y => Z) && (X => Z)) => (Y || X) => Z =
  (`y=>z`, `x=>z`) =>
    _.foldSum(`y=>z`, `x=>z`)

// ...
```

is a condition related function utility.


`areAllPositive` is a program that makes use of the following programming ingredient 

- `aggregate` from `RecursiveStructure`

See 

- [RecursiveStructure](https://psbp-library.github.io#recursivestructure)

and 

- [Structure](https://psbp-library.github.io#structure) 

for more details.

Note that recursion is dealt with using parameter `R[+_[+ _]]`.

See

- [Recursion](https://psbp-library.github.io#recursion)

Note that `areAllPositive` is not fully defined at external specification level.

`areAllPositive` makes use of `List` which is defined at external implementation level.

`List` is an example of a *polynomial data structure*.

`List` is used to define the *recursive polynomial data structure* `Recursive[R][List]`

See

- [List](https://psbp-library.github.io#list)

and

- [Recursive](https://psbp-library.github.io#recursive)

for more details.

## Main programs

### `mainFactorial`

```scala
package examples.specification.program.effectful

import psbp.external.specification.program.Program

import examples.specification.program.factorial

def mainFactorial[
  >-->[- _, + _]: Program
]: Unit >--> Unit =
  factorial toMainWith (
    producer = intProducer,
    consumer = factorialConsumer
  )
```

where

```scala
package examples.specification.program.effectful

import scala.language.postfixOps

import psbp.external.specification.program.Program

object producerFunction {

  val intProducer: Unit => BigInt =
    _ =>
      println("Please type an integer")
      BigInt(scala.io.StdIn.readInt)

}

def intProducer[
  >-->[- _, + _]: Program
]: Unit >--> BigInt =
  producerFunction.intProducer asProgram

// ...    
```

and where

```scala
package examples.specification.program.effectful

import scala.language.postfixOps

import psbp.external.specification.types.&&

import psbp.external.specification.program.Program

object consumerFunction {

  val factorialConsumer: (BigInt && BigInt) => Unit =
    (i, j) =>
      println(s"applying factorial to the integer argument $i yields result $j")

  // ...    

}

def factorialConsumer[
  >-->[- _, + _]: Program
]: (BigInt && BigInt) >--> Unit =
  consumerFunction.factorialConsumer asProgram

// ...  
```

`mainFactorial` is a main program that, for now, makes use of an *effectful* producer and an *effectful* consumer.

`toMain` is an `extension` of `Program`.

See [Program](https://psbp-library.github.io#program) for more details.

### `mainFibonacci`

```scala
package examples.specification.program.effectful

import psbp.external.specification.program.Program

import examples.specification.program.fibonacci

def mainFibonacci[
  >-->[- _, + _]: Program
]: Unit >--> Unit =
  fibonacci toMainWith (
    producer = intProducer,
    consumer = fibonacciConsumer
  )
```

where

```scala
package examples.specification.program.effectful

// ...

object consumerFunction {

  // ...

  val fibonacciConsumer: (BigInt && BigInt) => Unit =
    (i, j) =>
      println(s"applying fibonacci to the integer argument $i yields result $j")

}

// ...

def fibonacciConsumer[
  >-->[- _, + _]: Program
]: (BigInt && BigInt) >--> Unit =
  consumerFunction.fibonacciConsumer asProgram

// ...  
```

`mainFibonacci` is a main program that, for now, makes use of an effectful producer and an effectful consumer.

### `mainOptimizedFactorial`

```scala
package examples.specification.program.effectful

import psbp.external.specifcation.program.Program

import examples.specification.program.optimizedFactorial

def mainOptimizedFactorial[
  >-->[- _, + _]: Program
]: Unit >--> Unit =
  optimizedFactorial toMainWith (
    producer = intProducer,
    consumer = factorialConsumer
  )
```

### `mainOptimizedFibonacci`

```scala
package examples.specification.program.effectful

import psbp.external.specifcation.program.Program

import examples.specification.program.optimizedFibonacci

def mainOptimizedFibonacci[
  >-->[- _, + _]: Program
]: Unit >--> Unit =
  optimizedFibonacci toMainWith (
    producer = intProducer,
    consumer = fibonacciConsumer
  )
```

### `mainTwoRandoms`

```scala
package examples.specification.programWithState.effectful

import psbp.external.specification.programWithState.ProgramWithState

import examples.specification.programWithState.{ 
  Seed
  , twoRandoms
}  

def mainTwoRandoms[
  >-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithState[Seed, >-->]
]: Unit >--> Unit =
  twoRandoms toMainWith (
    producer = unitProducer,
    consumer = twoRandomsConsumer
  )
```

where

```scala
package examples.specification.programWithState.effectful

import scala.language.postfixOps

import psbp.external.specification.program.Program

object producerFunction {

  val unitProducer: Unit => Unit = 
    _ =>
      ()

  // ...    

}

def unitProducer[
  >-->[- _, + _]: Program
]: Unit >--> Unit = 
  producerFunction.unitProducer asProgram 

// ...  
```

and where

```scala
package examples.specification.programWithState.effectful

import scala.language.postfixOps

import psbp.external.specification.types.&&

import psbp.external.specification.program.Program

object consumerFunction {

  val twoRandomsConsumer: (Unit && (BigInt && BigInt)) => Unit =
    case (_, (i, j)) =>
      println(s"generating two random numbers yields result ${(i, j)}")

  // ...    

}

def twoRandomsConsumer[
  >-->[- _, + _]: Program
]: (Unit && (BigInt && BigInt)) >--> Unit =
  consumerFunction.twoRandomsConsumer asProgram

// ...  
```

`mainTwoRandoms` is a main program that, makes use of a trivial producer and, for now, makes use of an effectful consumer.

### `mainParallelFibinacci`

```scala
package examples.specification.programWithParallel.effectful

import psbp.external.specification.programWithParallel.ProgramWithParallel

import examples.specification.programWithParallel.parallelFibonacci 

import examples.specification.program.effectful.intProducer

def mainParallelFibonacci[
  >-->[- _, + _]: ProgramWithParallel
]: Unit >--> Unit =
  parallelFibonacci toMainWith (
    producer = intProducer,
    consumer = parallelFibonacciConsumer
  )
```

where

```scala
package examples.specification.programWithParallel.effectful

import scala.language.postfixOps

import akka.actor.typed.{ 
  ActorSystem
  , Behavior
}

import akka.actor.typed.scaladsl.{ 
  ActorContext
  , Behaviors 
}
  
import Behaviors.{ 
  receive
  , stopped
}
  
import ch.qos.logback.classic.{ 
  Logger
  , LoggerContext
  , Level 
}

import Level.{ 
  INFO
  , ERROR
}
  
import org.slf4j.LoggerFactory

import psbp.external.specification.types.&&

import psbp.external.specification.program.Program 

val packageName = "examples.specification.programWithParallel.effectful"
  
val loggerContext: LoggerContext = 
  LoggerFactory.getILoggerFactory().asInstanceOf[LoggerContext]
val logger: Logger = 
  loggerContext.getLogger(packageName)
  
def logInfo[Z](actorContext: ActorContext[Z])(message: String): Unit =
  logger.setLevel(INFO)
  actorContext.log.info(message)
  logger.setLevel(ERROR)
  
object consumerFunction {

  def parallelFibonacciConsumer[
    >-->[- _, + _]: Program
  ]: (BigInt && BigInt) => Unit =
    (i, j) =>

      object Consumer {
  
        case class Consume(i: BigInt, j: BigInt)
    
        def apply(): Behavior[Consume] = 
          receive { (context, message) =>
            message match {
              case Consume(i, j) =>
                val info = logInfo(context)
                info(s"applying parallel fibonacci to argument $i yields result $j")
                stopped
            }
          }
    
      }
    
      import Consumer.Consume

      val consumer = ActorSystem(Consumer(), "consumer")  
           
      consumer ! Consume(i, j)
  
  // ...

} 

def parallelFibonacciConsumer[
  >-->[- _, + _]: Program
]: (BigInt && BigInt) >--> Unit =

  consumerFunction.parallelFibonacciConsumer asProgram 

// ...  
```

`mainParallelFibonacci` is a main program that, for now, makes use of the same effectful producer as `fibonacci` and makes use of an *akka actor* and *logback logging* based effectful consumer.

Actor `consumer` is sent a message `Consume(i, j)` which causes `info` to do its logging work.

### `mainAreAllPositive`

```scala
package examples.specification.program.recursive.structure.implementation.list.effectful

import psbp.external.specification.program.Program 

import psbp.external.specification.recursion.Recursion

import psbp.external.specification.structure.recursive.RecursiveStructure

import psbp.external.implementation.list.List

import examples.specification.program.recursive.structure.implementation.list.areAllPositive

def mainAreAllPositive[
  R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Program
                  : [>-->[- _, + _]] =>> RecursiveStructure[List, R, >-->]
]: Unit >--> Unit =
  areAllPositive toMainWith (
    producer = recursiveIntListProducer,
    consumer = areAllPositiveConsumer
  )
```

where

```scala
package examples.specification.program.recursive.structure.implementation.list.effectful

import scala.language.postfixOps

import scala.collection.immutable.Seq

import psbp.external.specification.program.Program 

import psbp.external.specification.recursion.Recursion

import psbp.external.implementation.list.List

import psbp.external.specification.structure.recursive.{ 
  Recursive
  , RecursiveStructure 
}

import examples.specification.program.recursive.structure.implementation.list.seqToRecursiveList

def recursiveIntListProducer[
  Z
  , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Program
                  : [>-->[- _, + _]] =>> RecursiveStructure[List, R, >-->]
]: Unit >--> Recursive[R][List][BigInt] =

  object producerFunction {

    val intSeqProducer: Unit => Seq[BigInt] =
      _ =>
        println("Please type a sequence of integers separated by a blank")
        Seq(scala.io.StdIn.readLine.split(" ").map(s => BigInt(s.toInt)).toSeq: _*)

  }

  val intSeqProducer: Unit >--> Seq[BigInt] =
    producerFunction.intSeqProducer asProgram

  intSeqProducer >--> seqToRecursiveList
```

and where

```scala
package examples.specification.program.recursive.structure.implementation.list.effectful

import scala.language.postfixOps

import scala.collection.immutable.Seq

import psbp.external.specification.types.&&

import psbp.external.specification.program.Program 

import psbp.external.specification.recursion.Recursion

import psbp.external.implementation.list.List

import psbp.external.specification.aggregatable.recursive.{ 
  Recursive
  , RecursivelyAggregatable 
}

import examples.specification.program.recursive.aggregatable.implementation.list.recursiveListToSeq

def areAllPositiveConsumer[
  Z,
  R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->],
  >-->[- _, + _]: Program
                : [>-->[- _, + _]] =>> RecursivelyAggregatable[List, R, >-->]
  ]: (Recursive[R][List][BigInt] && Boolean) >--> Unit =

  val program: Program[>-->] = 
    summon[Program[>-->]]
  import program.identity

  object consumerFunction {

    val areAllOfSeqPositiveConsumer: (Seq[BigInt] && Boolean) => Unit =
      (is, j) =>
        println(s"applying areAllPositive to the list of integer arguments ${is.mkString(" ")} yields result $j")

  }

  val areAllOfSeqPositiveConsumer: (Seq[BigInt] && Boolean) >--> Unit =
    consumerFunction.areAllOfSeqPositiveConsumer asProgram  
  
  (recursiveListToSeq &&& identity) >--> areAllOfSeqPositiveConsumer
```

where

```scala
package examples.specification.program.recursive.structure.implementation.list

import scala.collection.immutable.Seq

import psbp.external.specification.program.Program 

import psbp.external.specification.recursion.Recursion

import psbp.external.specification.structure.recursive.{
  Recursive
  , RecursiveStructure
}

import psbp.external.implementation.list.List

import examples.specification.functional.recursiveUnfolderType.fromSeqUnfolder
  
def seqToRecursiveList[
  Z
  , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Program
                  : [>-->[- _, + _]] =>> RecursiveStructure[List, R, >-->]
]: Seq[Z] >--> Recursive[R][List][Z]  = 
    
  val structure: RecursiveStructure[List, R, >-->] = summon[RecursiveStructure[List, R, >-->]]
  import structure.unfold

  unfold(fromSeqUnfolder[Z, >-->]) 
```

and where

```scala
package examples.specification.program.recursive.structure.implementation.list

import scala.collection.immutable.Seq

import psbp.external.specification.program.Program 

import psbp.external.specification.recursion.Recursion

import psbp.external.specification.structure.recursive.{
  Recursive
  , RecursiveStructure
}

import psbp.external.implementation.list.List

import examples.specification.functional.recursiveFolderType.toSeqFolder

def recursiveListToSeq[
  Z
  , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Program
                  : [>-->[- _, + _]] =>> RecursiveStructure[List, R, >-->]
]: Recursive[R][List][Z] >--> Seq[Z] = 
    
  val structure: RecursiveStructure[List, R, >-->] = summon[RecursiveStructure[List, R, >-->]]
  import structure.fold

  fold(toSeqFolder[Z, >-->])
```

are utilities that make use of the following programming ingredients


- `unfold` from `RecursiveStructure`
- `fold` from `RecursiveStructure`

See 

- [RecursiveStructure](https://psbp-library.github.io#recursivestructure)

and 

- [Structure](https://psbp-library.github.io#structure) 

for more details,

where

```scala
package examples.specification.functional.recursiveUnfolderType

import scala.language.postfixOps

import scala.collection.immutable.Seq

import psbp.external.specification.program.Functional

import psbp.external.specification.structure.recursive.RecursiveUnfolderType

import psbp.external.implementation.list.List

import examples.specification.function 

def fromSeqUnfolder[
  Z
  , >-->[- _, + _]: Functional
  ](
    using recursiveUnfolderType: RecursiveUnfolderType[List, >-->]
  ): recursiveUnfolderType.Unfolder[Seq[Z], Z] = 

  function.fromSeqUnfolder asProgram 
```

is a functional utility, used by `unfold`

and where

```scala
package examples.specification.functional.recursiveFolderType

// ...

import scala.collection.immutable.Seq

// ...

def toSeqFolder[
  Z
  , >-->[- _, + _]: Functional
  ](
    using recursiveFolderType: RecursiveFolderType[List, >-->]
  ): recursiveFolderType.Folder[Z, Seq[Z]] = 

  function.toSeqFolder asProgram  
```

is a functional utility, used by `fold`,

where

```scala
package examples.specification.function

import psbp.external.specification.types.{
  &&
  , ||
}

import ||.{Left, Right}

// ...

def fromSeqUnfolder[Z]: Seq[Z] => List[Z, Seq[Z]] =
  case Seq() => Left(())
  case z +: zs => Right((z, zs))

def toSeqFolder[Z]: List[Z, Seq[Z]] => Seq[Z] = 
  foldSum({
    case () =>
      Seq[Z]()
      }, { 
    case (z, zs) => 
      z +: zs
      }
  )
```

are function utilities.

## Running programs

### Running `active` `factorial` (effectful producer and consumer)

```scala
package examples.implementation.active.program.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.active.{ 
  given Program
  , given Materialization
}

import examples.specification.program.effectful.mainFactorial

@main def factorial(args: String*): Unit =
  mainFactorial materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.active.program.effectful.factorial 
Please type an integer
10
applying factorial to the integer argument 10 yields result 3628800
[success] ...
```

`@main def factorial` materializes `mainFactorial` and uses dependency injection by `import` of `active` `given` implementations.

See 

- [mainFactorial](https://psbp-library.github.io#mainfactorial)
- [activeProgram](https://psbp-library.github.io#activeprogram)
- [activeMaterialization](https://psbp-library.github.io#activematerialization) 

for more details.

### Running `active` `fibonacci` (effectful producer and consumer)

```scala
package examples.implementation.active.program.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.active.{ 
  given Program
  , given Materialization
}

import examples.specification.program.effectful.mainFibonacci

@main def fibonacci(args: String*): Unit =
  mainFibonacci materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.active.program.effectful.fibonacci 
Please type an integer
10
applying fibonacci to the integer argument 10 yields result 55
[success] ...
```

Running `active` `fibonacci` is extremely inefficient. 

Time for *optimization*!

### Running `active` `optimizedFactorial` (effectful producer and consumer)

```scala
package examples.implementation.active.program.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.active.{ 
  given Program
  , given Materialization
}

import examples.specification.program.effectful.mainOptimizedFactorial

@main def optimizedFactorial(args: String*): Unit =
  mainOptimizedFactorial materialized ()
```

See 

- [mainOptimizedFactorial](https://psbp-library.github.io#mainoptimizedfactorial) 
- [optimize](https://psbp-library.github.io#optimize) 

for more details.

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.active.program.effectful.optimizedFactorial 
Please type an integer
10
applying factorial to the integer argument 10 yields result 3628800
[success] ...
```

### Running `active` `optimizedFibonacci` (effectful producer and consumer)

```scala
package examples.implementation.active.program.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.active.{ 
  given Program
  , given Materialization
}

import examples.specification.program.effectful.mainOptimizedFibonacci

@main def optimizedFibonacci(args: String*): Unit =
  mainOptimizedFibonacci materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.active.program.effectful.optimizedFibonacci 
Please type an integer
10
applying fibonacci to the integer argument 10 yields result 89
[success] ...
```
See 

- [mainOptimizedFibonacci](https://psbp-library.github.io#mainoptimizedfibonacci) 
- [optimize](https://psbp-library.github.io#optimize) 

for more details.

Running `active` `optimizedFibonacci` can also be done with `1000` instead of `10`.

Running `active` `optimizedFibonacci` with `10000` produces a stack overflow.

Time for *tail recursive optimization*!

But, let's first show how easy it is to go *reactive*!

### Running `reactive` `factorial` (effectful producer and consumer)

```scala
package examples.implementation.reactive.program.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.reactive.{ 
  given Program
  , given Materialization
}

import examples.specification.program.effectful.mainFactorial

@main def factorial(args: String*): Unit =
  mainFactorial materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.reactive.program.effectful.factorial 
Please type an integer
10
applying factorial to the integer argument 10 yields result 3628800
[success] ...
```

The only difference with the `active` version is the usage of a different dependency injection by `import`.

This will be a recurring theme when running program examples.

`@main def factorial` uses dependency injection by `import` of `reactive` `given` implementations.

See 

- [reactiveProgram](https://psbp-library.github.io#reactiveprogram)
- [reactiveMaterialization](https://psbp-library.github.io#reactivematerialization)

for more details.

### Running `reactive` `fibonacci` (effectful producer and consumer)

```scala
package examples.implementation.reactive.program.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.reactive.{ 
  given Program
  , given Materialization
}

import examples.specification.program.effectful.mainFibonacci

@main def fibonacci(args: String*): Unit =
  mainFibonacci materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.reactive.program.effectful.fibonacci 
Please type an integer
10
applying fibonacci to the integer argument 10 yields result 55
[success] ...
```

Again, the only difference with the `active` version is the usage of a different dependency injection by `import`.

`@main def factorial` uses dependency injection by `import` of `reactive` `given` implementations.

See 

- [reactiveProgram](https://psbp-library.github.io#reactiveprogram)
- [reactiveMaterialization](https://psbp-library.github.io#reactivematerialization)

for more details.

### Running `reactive` `optimizedFactorial` (effectful producer and consumer)

```scala
package examples.implementation.reactive.program.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.reactive.{ 
  given Program
  , given Materialization
}

import examples.specification.program.effectful.mainOptimizedFactorial

@main def optimizedFactorial(args: String*): Unit =
  mainOptimizedFactorial materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.reactive.program.effectful.optimizedFactorial 
Please type an integer
10
applying factorial to the integer argument 10 yields result 3628800
[success] ...
```

### Running `reactive` `optimizedFibonacci` (effectful producer and consumer)

```scala
package examples.implementation.reactive.program.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.reactive.{ 
  given Program
  , given Materialization
}

import examples.specification.program.effectful.mainOptimizedFibonacci

@main def optimizedFibonacci(args: String*): Unit =
  mainOptimizedFibonacci materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.reactive.program.effectful.optimizedFibonacci 
Please type an integer
10
applying fibonacci to the integer argument 10 yields result 89
[success] ...
```

Running `reactive` `optimizedFibonacci` can also be done with `250` instead of `10`.

Running `reactive` `optimizedFibonacci` with `1000` produces a stack overflow.

Even more time for tail recursive optimization!

### Running tail recursive `freeActive` `factorial` (effectful producer and consumer)

```scala
package examples.implementation.freeActive.program.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.freeActive.{ 
  given Program
  , given Materialization
}

import examples.specification.program.effectful.mainFactorial

@main def factorial(args: String*): Unit =
  mainFactorial materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.freeActive.program.effectful.factorial 
Please type an integer
10
applying factorial to the integer argument 10 yields result 3628800
[success] ...
```

Again, the only difference with other versions is the usage of a different dependency injection by `import`.

`@main def factorial` uses dependency injection by `import` of `freeActive` `given` implementations.

See 

- [freeActiveProgram](https://psbp-library.github.io#freeactiveprogram)
- [freeActiveMaterialization](https://psbp-library.github.io#freeactivematerialization)

for more details.

Running `reactive` `factorial` can also be done with `10000` instead of `10`.

### Running tail recursive `freeActive` `optimizedFibonacci` (effectful producer and consumer)

```scala
package examples.implementation.freeActive.program.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.freeActive.{ 
  given Program
  , given Materialization
}

import examples.specification.program.effectful.mainOptimizedFibonacci

@main def optimizedFibonacci(args: String*): Unit =
  mainOptimizedFibonacci materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.freeActive.program.effectful.optimizedFibonacci 
Please type an integer
10
applying fibonacci to the integer argument 10 yields result 89
[success] ...
```

Running `freeActive` `optimizedFibonacci` can also be done with `10000` instead of `10`.

Running `freeActive` `optimizedFibonacci` with `10000` does not produce a stack overflow.

### Running `stateActive` `twoRandoms` (effectful consumer)

```scala
package examples.implementation.active.programWithState.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.program.state.State

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.stateActive.{
  given Program
  , given State
  , given Materialization
}

// given
import psbp.external.specification.programWithState.programWithState

import examples.specification.programWithState.effectful.mainTwoRandoms

// given
import examples.specification.programWithState.initialSeedState

@main def twoRandoms(args: String*): Unit =
  mainTwoRandoms materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.active.programWithState.effectful.twoRandoms 
generating two random numbers yields result (384748,1151252339)
[success] ...
```

Again, an important difference with other examples is the usage of different injections by `import`.

`@main def twoRandoms` uses dependency injection by `import` of `stateActive` `given` implementations.

See 

- [stateActiveProgram](https://psbp-library.github.io#stateactiveprogram)
- [stateActiveState](https://psbp-library.github.io#stateactivestate)
- [stateActiveMaterialization](https://psbp-library.github.io#stateactivematerialization) 

and

- [programWithState](https://psbp-library.github.io#programwithstate)

for more details.

`@main def twoRandoms` also uses a `given` implementation of `Initial` for `Seed`.

See

- [initialSeedState](https://psbp-library.github.io#initialseedstate)

foe more details.


When running the materialized main program implementation, the implementation of program `twoRandoms`, somehow, transforms its argument, in this case no argument, to a result, in this case a product result.

This product result consists of two *different* random numbers, `384748` and `1151252339`. 

They are different because, while transforming, internal side effects are happening along the way.

More precisely, the `Seed` computation state (on which random number generation depends) modifies.

The important takeway is that programming with state can be achieved *without using any* `var`*'s*.

Instead, state manifests itself, internally, in the function type `Z => (S => [(S, Y)])` of program implementations.

### Running `reactive` `parallelFibonacci` (effectful producer and consumer)

```scala
package examples.implementation.reactive.programWithParallel.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.program.parallel.Parallel

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.reactive.{
  given Program
  , given Parallel
  , given Materialization
}

// given
import psbp.external.specification.programWithParallel.programWithParallel

import examples.specification.programWithParallel.effectful.mainParallelFibonacci

@main def parallelFibonacci(args: String*): Unit =
  mainParallelFibonacci materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.reactive.programWithParallel.effectful.parallelFibonacci 
Please type an integer
5
[2021-04-07 18:43:04,110] - leftActor received LeftAct 
[2021-04-07 18:43:04,135] - rightActor received RightAct 
[2021-04-07 18:43:04,161] - leftActor received LeftAct 
[2021-04-07 18:43:04,198] - leftActor received LeftAct 
[2021-04-07 18:43:04,201] - rightActor received RightAct 
[2021-04-07 18:43:04,249] - leftActor received LeftAct 
[2021-04-07 18:43:04,306] - rightActor received RightAct 
[2021-04-07 18:43:04,346] - rightActor received RightAct 
[2021-04-07 18:43:04,363] - leftActor received LeftAct 
[2021-04-07 18:43:04,400] - leftActor received LeftAct 
[2021-04-07 18:43:04,420] - leftActor received LeftAct 
[2021-04-07 18:43:04,421] - rightActor received RightAct 
[2021-04-07 18:43:04,424] - reactor received both RightReact(0) and LeftReact(1) 
[2021-04-07 18:43:04,426] - reactor received both LeftReact(1) and RightReact(1) 
[2021-04-07 18:43:04,444] - rightActor received RightAct 
[2021-04-07 18:43:04,444] - reactor received both RightReact(0) and LeftReact(1) 
[2021-04-07 18:43:04,523] - rightActor received RightAct 
[2021-04-07 18:43:04,523] - reactor received both RightReact(0) and LeftReact(1) 
[2021-04-07 18:43:04,525] - reactor received both LeftReact(1) and RightReact(1) 
[2021-04-07 18:43:04,526] - reactor received both LeftReact(2) and RightReact(1) 
[2021-04-07 18:43:04,528] - reactor received both LeftReact(3) and RightReact(2) 
[2021-04-07 18:43:04,590] - applying parallel fibonacci to argument 5 yields result 5 
[success] ...
```

Again, an important difference with other examples is the usage of different injections by `import`.

`@main def parallelFibonacci` uses dependency injection by `import` of `reactive` `given` implementations.

See 

- [reactiveProgram](https://psbp-library.github.io#reactiveprogram)
- [reactiveParallel](https://psbp-library.github.io#reactiveparallel)
- [reactiveMaterialization](https://psbp-library.github.io#reactivematerialization) 

and

- [programWithParallel](https://psbp-library.github.io#programwithparallel)

for more details.

### Running `active` `areAllPositive` (effectful producer and consumer)

```scala
package examples.implementation.active.program.rec.recursion.list.structure.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.active.{
  given Program
  , given Materialization
}

// given
import psbp.external.implementation.rec.recRecursion

// given
import psbp.external.implementation.list.listRecursiveStructure

import examples.specification.program.recursive.structure.implementation.list.effectful.mainAreAllPositive

@main def areAllPositive(args: String*): Unit =
  mainAreAllPositive materialized ()
```

Again, an important difference with other examples is the usage of different injections by `import`.

`@main def areAllPositive` uses dependency injection by `import` of `active` `given` implementations.

See 

- [activeProgram](https://psbp-library.github.io#activeprogram)
- [activeMaterialization](https://psbp-library.github.io#activematerialization) 

and, for dealing with the polynomial recursive data structure `List`

- [recRecursion](https://psbp-library.github.io#recrecursion)
- [listRecursiveStructure](https://psbp-library.github.io#listrecursivestructure)

for more details.

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.active.program.rec.recursion.list.structure.effectful.areAllPositive 
Please type a sequence of integers separated by a blank
1 2 3
applying areAllPositive to the list of integer arguments 1 2 3 yields result true
[success]
```

# Library development

This section explains the inner workings of the `PSBP` library.

Feel free to add 

- new programming ingredients as specification `trait`'s,
- other implementations `given`'s of existing programming ingredients.

## Specification `trait`'s

### `Condition`

```scala
package psbp.external.specification.program

import psbp.external.specification.types.||

trait Condition[>-->[- _, + _]]:

  // declared

  private[psbp] def conditionally[Z, Y, X](
    `y>-->z`: => Y >--> Z
    , `x>-->z`: => X >--> Z
  ): (Y || X) >--> Z

  // defined extensions
  
  extension [Z, Y, X] (`y>-->z`: => Y >--> Z) 
    def ||(`x>-->z`: => X >--> Z): (Y || X) >--> Z =
      conditionally(`y>-->z`, `x>-->z`)
```

where

```scala
package psbp.external.specification.types

// ...

// sum

enum ||[+Z, +Y]:
  case Left(z: Z) extends (Z || Y)
  case Right(y: Y) extends (Z || Y)

  def foldSum[X](`z=>x`: => Z => X, `y=>x`: => Y => X): X =
    this match
      case Left(z) =>
        `z=>x`(z)
      case Right(y) =>
        `y=>x`(y)  
```

`Condition` specifies that *programs can deconstruct sum based composite data to perform conditional logic*.

The member `||` is an `extension` that can be used as infix operator.

The types of the arguments of `conditionally` are call-by-name ones.

`foldSum` hides the `enum` representation of the sum type.

Again, names like `` `y>-->z` `` and `` `x>-->z` `` can be thought of as typeful generic names for programs.

Typeful generic names, hopefully, are illustrative when writing code like `` val `(y||x)>-->z` = `y>-->z` && `x>-->z` ``.

### `Program`

```scala
package psbp.external.specification.program

import scala.language.postfixOps

import psbp.external.specification.types.{ 
  &&
  , || 
}

import psbp.external.specification.functional.{ 
  `(z&&y)>-->z`
  , `(z&&y)>-->y`
  , `z>-->(z||y)`
  , `y>-->(z||y)`
  , `(z&&b)>-->(z||z)` 
}

trait Program[>-->[- _, + _]]
  extends FunctionalClassification[>-->]
  with Construction[>-->]
  with Condition[>-->]:

    private given program: Program[>-->] = this

    // defined extensions

    extension [Z, Y, X, W] (`z>-->x`: Z >--> X) 
      def &&&(`y>-->w`: => Y >--> W): (Z && Y) >--> (X && W) =
        (`(z&&y)>-->z` >--> `z>-->x`) && (`(z&&y)>-->y` >--> `y>-->w`)  

    extension [Z, Y, X, W] (`x>-->z`: => X >--> Z) 
      def |||(`w>-->y`: => W >--> Y): (X || W) >--> (Z || Y) =
        (`x>-->z` >--> `z>-->(z||y)`) || (`w>-->y` >--> `y>-->(z||y)`)  

    extension [Z, Y] (program: Z >--> Y) 
      def toMainWith(
        producer: Unit >--> Z
        , consumer: (Z && Y) >--> Unit
      ): Unit >--> Unit =
        producer 
        >--> 
          {
            Let { 
              program
            } In { 
              consumer 
            }
          }
    
    // defined
    
    def Let[Z, Y, X](`z>-->y`: Z >--> Y): In[Z, Y, X] =
      new {
        def In(`(z&&y)>-->x`: => (Z && Y) >--> X): Z >--> X =
          (`z>-->z` && `z>-->y`) >--> `(z&&y)>-->x`
      } 

    private[psbp] trait In[Z, Y, X]:
      def In(`(z&&y)>-->x`: => (Z && Y) >--> X): Z >--> X
    
    def If[Z, Y](`z>-->b`: Z >--> Boolean): Apply[Z, Y] =
      new {
        override def apply(`z>-t->y`: => Z >--> Y): Else[Z, Y] =
          new {
            override def Else(`z>-f->y`: => Z >--> Y): Z >--> Y =
              Let {
                `z>-->b`
              } In {
                `(z&&b)>-->(z||z)`
              } >--> {
                `z>-t->y` || `z>-f->y`
              }  
          }
      } 

    private[psbp] trait Apply[Z, Y]:
      def apply(`z>-t->y`: => Z >--> Y): Else[Z, Y]

    private[psbp] trait Else[Z, Y]:
      def Else(`z>-f->y`: => Z >--> Y): Z >--> Y
```

where

```scala
package psbp.external.specification.functional

import scala.language.postfixOps

import psbp.external.specification.types.{ 
  &&
  , ||
}

import psbp.external.specification.program.Functional

import psbp.external.specification.function

// ...

// construction

// construction

// construction

def `(z&&y)>-->z`[
  Z, Y
  , >-->[- _, + _]: Functional
]: (Z && Y) >--> Z =
  function.`(z&&y)=>z` asProgram
    
def `(z&&y)>-->y`[
  Z, Y
  , >-->[- _, + _]: Functional
]: (Z && Y) >--> Y =
  function.`(z&&y)=>y` asProgram

// ...  

// condition

def `z>-->(z||y)`[
  Z, Y
  , >-->[- _, + _]: Functional
]: Z >--> (Z || Y) =
  function.`z=>(z||y)` asProgram
  
def `y>-->(z||y)`[
  Z, Y
  , >-->[- _, + _]: Functional
]: Y >--> (Z || Y) =
  function.`y=>(z||y)` asProgram 

// ...  

// construction and condition
  
// construction and condition
  
def `(z&&b)>-->(z||z)`[
  Z
  , >-->[- _, + _]: Functional
]: (Z && Boolean) >--> (Z || Z) =
  function.`(z&&b)=>(z||z)` asProgram 

// ...  
```

are functional utilities,

where

```scala
package psbp.external.specification.function

// ...

// construction

def `(z&&y)=>z`[Z, Y]: (Z && Y) => Z =
  (z, _) => 
    z

def `(z&&y)=>y`[Z, Y]: (Z && Y) => Y =
  (_, y) => 
    y  

// ...

// condition

import ||.{ Left, Right }

def `z=>(z||y)`[Z, Y]: Z => (Z || Y) =
  z =>
    Left(z)

def `y=>(z||y)`[Z, Y]: Y => (Z || Y) =
  y =>
    Right(y)   

// ...

// construction and condition

def `(z&&b)=>(z||z)`[Z]: (Z && Boolean) => (Z || Z) =
  (z, b) => 
    (z, b) match {
      case (_, true) => Left(z)
      case (_, false) => Right(z) 
    } 
 
// ... 
```

are function utilities.

`Program` combines the essential programming ingredients and defines a few extra ones.

The members `&&&` resp. `|||` are `extension`'s that are more complex versions of `&&` resp. `||`.

The `Let {} In {}` programming ingredient specifies that *programs can define local values*.

The `If() {} Else {}` programming ingredient specifies that programs *can perform if-then-else based logic*.

`Let {} In {}` and `If() {} Else {}` are perfect examples of the **sca**lable **la**nguage capability of `Scala`.

The member `toMainWith` is an `extension` that combines a program with a producer and a consumer to a main program.

Let's explain the pointfree definition of `toMainWith`, thinking of programs as functions, in a pointful way using an argument `()`.

At level `1`, `toMain` looks like

```scala
      // level 1.1 
        >--> 
          // level 1.2
```

at level `1.1`, `toMain` looks like

```scala
    producer
```

A result value `z` of type `Z`, which equals `producer()`, a by `producer` produced value of type `Z`, is constructed, which is a to be transformed argument of `program`.

At level `1.2`, `toMain` looks like

```scala
        Let { 
          // level 2.1
        } In { 
          // level 2.2
        }
      }  
```

At level `2.1`, `toMain` looks like

```scala
          program
```

a local value `y` of type `Y`, which equals `program(z)`, a by `program` transformed value of type `Y`, which is a to be consumed argument of `consumer`, available as the second component of a product-value `(z, y)`.

At level `2.2`, `toMain` looks like

```scala
          consumer 
```

The result is `consumer(z, y)`.

So `consumer` can consume both `z`, which equals `producer()` the result value produced by `producer` and `y`, which equals `program(z)` the local result value transformed by `program.`

### About power of expression of `&&&` and `Let {} In {}`

```scala
package examples.specification.program

import psbp.external.specification.types.&&

import psbp.external.specification.program.Program

import psbp.external.specification.functional.{ 
  `z>-->(z&&z)`
  , `(z&&y)>-->z` 
  , `(z&&y&&x)>-->(y&&x)` }

def `construct using &&&`[
  Z, Y, X
  , >-->[- _, + _]: Program
](
  `z>-->y`: Z >--> Y
  , `z>-->x`: => Z >--> X
): Z >--> (Y && X) =
  `z>-->(z&&z)` >--> (`z>-->y` &&& `z>-->x`)

def constructUsingLet[
  Z, Y, X
  , >-->[- _, + _]: Program
](
  `z>-->y`: Z >--> Y
  , `z>-->x`: => Z >--> X
): Z >--> (Y && X) =
 
  val program: Program[>-->] = 
    summon[Program[>-->]]
  import program.Let

  Let {
    `z>-->y`
  } In {
    Let {
      `(z&&y)>-->z` >--> `z>-->x`
    } In {
      `(z&&y&&x)>-->(y&&x)`
    }
  }
```

where

```scala
package psbp.external.specification.functional

// ...

// construction

// ...

def `z>-->(z&&z)`[
  Z
  , >-->[- _, + _]: Functional
]: Z >--> (Z && Z) =
  function.`z=>(z&&z)` asProgram  

def `(z&&y&&x)>-->(y&&x)`[
  Z, Y, X  
  , >-->[- _, + _]: Functional
]: (Z && Y && X) >--> (Y && X) =
  function.`(z&&y&&x)=>(y&&x)` asProgram 

// ...  
```

are functional utilities,

where

```scala
package psbp.external.specification.program

// ...

// construction

// ...

def `z=>(z&&z)`[Z]: Z => (Z && Z) =
  z =>
    (z, z)   
    
def `(z&&y&&x)=>(y&&x)`[Z, Y, X]: (Z && Y && X) => (Y && X) =
  case ((_, y), x) =>
    (y, x)    
```

are function utilities.

`&&&` and `Let {} In {}` have the same power of expression as `construct` and `&&`.

### About power of expression of `|||` and `If() {} Else {}`

```scala
package examples.specification.program

import psbp.external.specification.types.||

import psbp.external.specification.program.Program

import psbp.external.specification.functional.{ 
  `(z||z)>-->z`
  , `(y||x)>-->b`
  , `(y||x)>-->y`
  , `(y||x)>-->x` 
}

def `conditionally using |||`[
  Z, Y, X
  , >-->[- _, + _]: Program
](
  `y>-->z`: => Y >--> Z
  , `x>-->z`: => X >--> Z
): (Y || X) >--> Z =
  (`y>-->z` ||| `x>-->z`) >--> `(z||z)>-->z`

def conditionallyUsingIf[
  Z, Y, X
  ,  >-->[- _, + _]: Program
](
  `y>-->z`: => Y >--> Z
  , `x>-->z`: => X >--> Z
): (Y || X) >--> Z =

  val program: Program[>-->] = 
    summon[Program[>-->]]
  import program.If

  If(`(y||x)>-->b`) {
    `(y||x)>-->y` >--> `y>-->z`
  } Else {
    `(y||x)>-->x` >--> `x>-->z`
  }
```
where

```scala
package psbp.external.specification.program

// ...

// condition

def `(z||z)>-->z`[
  Z
  , >-->[- _, + _]: Functional
]: (Z || Z) >--> Z =
  function.`(z||z)=>z` asProgram  
  
def `(y||x)>-->b`[
  Y, X
  , >-->[- _, + _]: Functional
]: (Y || X) >--> Boolean =
  function.`(y||x)=>b` asProgram

def `(y||x)>-->y`[
  Y, X
  , >-->[- _, + _]: Functional
]: (Y || X) >--> Y =
  function.`(y||x)=>y` asProgram

def `(y||x)>-->x`[
  Y, X
  , >-->[- _, + _]: Functional
]: (Y || X) >--> X =
  function.`(y||x)=>x` asProgram  

// ...
```

are functional utilities,

where

```scala
package psbp.external.specification.function

// ...

// condition

// ...

def foldSum[Z, Y, X]: ((Y => Z) && (X => Z)) => (Y || X) => Z =
  (`y=>z`, `x=>z`) =>
    _.foldSum(`y=>z`, `x=>z`)

def `(z||z)=>z`[Z]: (Z || Z) => Z =
  foldSum(z => z, z => z)  
  
def `(y||x)=>b`[Y, X]: (Y || X) => Boolean =
  foldSum(_ => true, _ => false)

def `(y||x)=>y`[Y, X]: (Y || X) => Y =
  foldSum(y => y, _ => ???) 

def `(y||x)=>x`[Y, X]: (Y || X) => X =
  foldSum(_ => ???, x => x) 

// ...  
```

are function utilities.

`|||` and `If() {} Else {}` have the same power of expression as `conditionally` and `||`.

### `Materialization`

```scala
package psbp.external.specification.materialization

trait Materialization[>-->[- _, + _], -Z, +Y]:

  // declared

  private[psbp] val materialize: (Unit >--> Unit) => Z => Y

  // defined extensions

  extension (`u>-->u`: Unit >--> Unit) def materialized: Z => Y =
    materialize(`u>-->u`)
```

Main programs, programs of type `Unit >--> Unit` are materialized to functions.

The function type `Z => Y` is generic to allow for various materializations.

The member `materialized` is an `extension` that can be used as postfix operator.

### `State`

```scala
package psbp.external.specification.program.state

trait State[S, >-->[- _, + _]]:

  // declared

  private[psbp] def `u>-->s`: Unit >--> S

  private[psbp] def `s>-->u`: S >--> Unit

trait Initial[S]:
 
  // declared

  val s: S
```

`State` specifies that *programs can read and write state*.

`Initial` specifies that `S` has an *initial state*. 

Statefulness is an example of how basic programming ingredients can be *extended* with extra ones by need.

### `ProgramWithState`

```scala
package psbp.external.specification.programWithState

import psbp.external.specification.program.Program

import psbp.external.specification.program.state.State

import psbp.external.specification.functional.`z>-->u`

trait ProgramWithState[S, >-->[- _, + _]] 
  extends Program[>-->] 
  with State[S, >-->]:

  private given program: Program[>-->] = this

  // defined

  def readState[Z]: Z >--> S =
    `z>-->u` >--> `u>-->s`  

  def writeState: S >--> Unit =
    `s>-->u` 
    
  def modifyStateWith[Z]: (S >--> S) => (Z >--> Unit) =
    `s>-->s` =>
      readState >--> `s>-->s` >--> writeState
  
  def readStateModifiedWith[Z]: (S >--> S) => (Z >--> S) =
    `s>-->s` =>
      modifyStateWith(`s>-->s`) >--> readState
```

where

```scala
package psbp.external.specification.functional

import scala.language.postfixOps

import psbp.external.specification.types.{ 
  &&
  , ||
}

import psbp.external.specification.program.Functional

import psbp.external.specification.function

// functional

def `z>-->u`[
  Z
  , >-->[- _, + _]: Functional
]: Z >--> Unit =
  function.`z=>u` asProgram

// ...  
```
is a functional utility

where

```scala
package psbp.external.specification.function

// ...

// functional

// ...

def `z=>u`[Z]: Z => Unit = 
  z =>
    ()   

// ...    
```

is a function utility.

`readState`, `writeState`, `modifyStateWith` and `readStateModifiedWith` are useful state handling programs.

They are examples of how basic programming ingredients can be *combined* with extra ones.

`readStateModifiedWith` is used in [random](https://psbp-library.github.io#random)

### `Parallel`

```scala
package psbp.external.specification.program.parallel

import psbp.external.specification.types.&&

trait Parallel[>-->[- _, + _]]:
  
  // declared

  private[psbp] def parallel[Z, Y, X, W](
    `z>-->x`: Z >--> X
    , `y>-->w`: Y >--> W
  ): (Z && Y) >--> (X && W)

  private[psbp] def asynchronous[Z, Y](`z>-->y`: Z >--> Y): Z >--> Y 
  
  // defined extensions

  extension [Z, Y, X, W] (`z>-->x`: Z >--> X)
    def |&&&|(`y>-->w`: Y >--> W): (Z && Y) >--> (X && W) =
      parallel(`z>-->x`, `y>-->w`)

  extension [Z, Y] (`z>-->y`: Z >--> Y) def async: Z >--> Y  =
    asynchronous(`z>-->y`)
```

`Parallel` specifies that *programs can run in parallel* and that *prograns can run asynchronously*.

The member `|&&&|` is an `extension` that can be used as infix operator.

For example, `|&&&|` is used in [parallelFibonacci](https://psbp-library.github.io#parallelfibonacci)

The member `async` is an `extension` that can be used as postfix operator.

### `ProgramWithParallel`

```scala
package psbp.external.specification.programWithParallel

import psbp.external.specification.types.&&

import psbp.external.specification.program.Program

import psbp.external.specification.program.parallel.Parallel

import psbp.external.specification.functional.{ 
  `z>-->(z&&u)`
  , `(y&&u)>-->y`
  , `z>-->(z&&z)`
}

trait ProgramWithParallel[>-->[- _, + _]] 
  extends Program[>-->] 
  with Parallel[>-->]:

  private given program: Program[>-->] = this

  // defined
  
  override private[psbp] def asynchronous[Z, Y](`z>-->y`: Z >--> Y): Z >--> Y =
    `z>-->(z&&u)` >--> (`z>-->y` |&&&| `u>-->u`) >--> `(y&&u)>-->y`

  // defined extensions

  extension [Z, Y, X] (`z>-->y`: Z >--> Y) 
    def |&&|(`z>-->x`: Z >--> X): Z >--> (Y && X) =
      `z>-->(z&&z)` >--> (`z>-->y` |&&&| `z>-->x`)
```

where

```scala
package psbp.external.specification.program

// ...

// construction

// ...
    
def `z>-->(z&&u)`[
  Z 
  , >-->[- _, + _]: Functional
]: Z >--> (Z && Unit) =
  function.`z=>(z&&u)` asProgram

def `(y&&u)>-->y`[
  Y
  , >-->[- _, + _]: Functional
]: (Y && Unit) >--> Y =
  function.`(y&&u)=>y` asProgram 

// ...
```

are functional utilities

where

```scala
package psbp.external.specification.function

// ...  

// construction

// ...  

def `z=>(z&&u)`[Z]: Z => (Z && Unit) =
  z =>
    (z, ()) 

def `(y&&u)=>y`[Y]: (Y && Unit) => Y =
  (y, _) => 
    y

// ...    
```

are function utilities.

`asynchronous` can, by default, be defined, albeit in a somewhat sub-optimal way, in terms of `parallel`. 

The member `|&&|` is an `extension` that is a simpler version of `|&&&|`.

### `Traversable`

```scala
package psbp.external.specification.structure

trait Traversable[
  A[+ _]
  , >-->[- _, + _]
]:

  // declared

  def traverse[Z, Y]: (Z >--> Y) => A[Z] >--> A[Y]
```

`Traversable` specifies that *programs can traverse data structures*.

Note that `traverse` is a programming + data structure ingredient.

Actually, it is more of a data structure ingredient than a programming ingredient, hence its name.

More of them follow.

### `InitialTraverser`

```scala
package psbp.external.specification.structure

import psbp.external.specification.program.Classification

private[psbp] trait InitialTraverser[>-->[- _, + _]: Classification]:

  private val classification: Classification[>-->] =
    summon[Classification[>-->]]
  import classification.`y>-->y`  

  // defined

  private[psbp] def initialTraverser[Y]: Y >--> Y =
    `y>-->y`
```

`InitialTraverser` implements an *initial traverser* that comes with every classification type.

### `Foldable`

```scala
package psbp.external.specification.structure

trait Foldable[
  A[+ _]
  , >-->[- _, + _]
] extends FolderType: 

  // declared
  
  def fold[Y, X]: Folder[Y, X] => A[Y] >--> X
```

where

```scala
package psbp.external.specification.structure

private[psbp] trait FolderType:

  // declared

  private[psbp] type Folder[Y, X]
```

`Foldable` specifies that *programs can fold data structures to values with a folder*.

`FolderType` specifies the *type of the folder*.

### `InitialFolder`

```scala
package psbp.external.specification.structure

private[psbp] trait InitialFolder[A[+ _]] 
  extends FolderType:

  // declared

  private[psbp] def initialFolder[Y]: Folder[Y, A[Y]]
```

`InitialFolder` specifies an *initial folder* that comes with every folder type.

### `Unfoldable`

```scala
package psbp.external.specification.structure

trait Unfoldable[
  A[+ _]
  , >-->[- _, + _]
] extends UnfolderType: 

  // declared
  
  def unfold[X, Y]: Unfolder[X, Y] => X >--> A[Y]
```

where

```scala
package psbp.external.specification.structure

private[psbp] trait UnfolderType:

  // declared

  private[psbp] type Unfolder[X, Y]
```

`Unfoldable` specifies that *programs can unfold values to data structures with an unfolder*.

`UnfolderType` specifies the *type of the unfolder*.

### `InitialUnfolder`

```scala
package psbp.external.specification.structure

private[psbp] trait InitialUnfolder[A[+ _]] extends UnfolderType:

  // declared

  private[psbp] def initialUnfolder[Y]: Unfolder[A[Y], Y]
```

`InitialUnfolder` specifies an *initial unfolder* that comes with every unfolder type.

### `Structure`

```scala
package psbp.external.specification.structure

import scala.annotation.targetName

import psbp.external.specification.types.&&

private[psbp] trait Structure[
  A[+ _]
  , >-->[- _, + _]
] extends Traversable[A, >-->] 
  with InitialTraverser[>-->]
  with Foldable[A, >-->] 
  with InitialFolder[A] 
  with Unfoldable[A, >-->]
  with InitialUnfolder[A]:

  // declared

  @targetName("fuseFolder")
  private[psbp] def fuse[Z, Y, X]: (Z >--> Y && Folder[Y, X]) => Folder[Z, X]

  
  @targetName("fuseUnfolder")
  private[psbp] def fuse[X, Y, Z]: (Unfolder[X, Y] && Y >--> Z) => Unfolder[X, Z]

  // defined

  def aggregate[Z, Y, X]: (Z >--> Y && Folder[Y, X]) => A[Z] >--> X =
    fuse andThen fold

  def unaggregate[X, Y, Z]: (Unfolder[X, Y] && Y >--> Z) => X >--> A[Z] =
    fuse andThen unfold    

  override def traverse[Z, Y]: Z >--> Y => A[Z] >--> A[Y] =
    aggregate(_, initialFolder)

  /**
   * can be defined but is overridden 
   * for recursive poynomial data structures 
   */
  override def fold[Y, X]: Folder[Y, X] => A[Y] >--> X =
    aggregate(initialTraverser, _)
    
  /**
   * can be defined but is overridden 
   * for recursive poynomial data structures 
   */
  override def unfold[X, Y]: Unfolder[X, Y] => X >--> A[Y] =
    unaggregate(_, initialTraverser)
```

`Structure` combines the essential programming + data structure ingredients and defines a few extra ones.

`aggregate` folds with a folder that is obtained by *fusing* a traverser and a folder.

`unaggregate` unfolds with a unfolder that is obtained by *fusing* an unfolder with a traverser.

### `Recursion`

```scala
package psbp.external.specification.recursion

import psbp.external.specification.program.Program

trait Recursion[
  R[+_[+ _]]
  , >-->[- _, + _]
]:
  private[psbp] def `r[a]>-->a[r[a]]`[A[+ _]]: R[A] >--> A[R[A]]
  private[psbp] def `a[r[a]]>-->r[a]`[A[+ _]]: A[R[A]] >--> R[A]
```

`Recursion` specifies the essential data structure ingredients of *recursion*.

Given `R[+_[+ _]]` and `A[+ _]`, think of 

- `R[+_[+ _]]` as modeling the recursion of a recursive data structure,
- `A[+ _]` as modeling the structure of a recursive data structure.

- `` `a[r[a]]>-->r[a]` `` *folds* a data structure value of type `A[R[A]]` *one* time to a value of type `R[A]`.
- `` `r[a]>-->a[r[a]]` `` *unfolds* a value of type `R[A]` *one* time to data structure value of type `A[R[A]]`.

Note that 

- the folded data structure value is a value that can be folded.
- the unfolded value is a data structure value that can be folfded.

### `RecursiveFoldingAndUnfolding`

```scala
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
```

`RecursiveFoldingAndUnfolding` specifies the essential programming ingredients of *recursion*.

Using a program of type `A[Y] >--> Y`, think of it as *folding once*, a recursive data structure value of type `R[A]` can, recursively, be *folded many times* to a value of type `Y`.

Using a program of type `Z >--> A[Z]`, think of it as *unfolding once*, a value of type `Z` can , recursively, be *unfolded many times* to a recursive data structure value of type `R[A]`.

Note that folding many times can be done starting from `R[A]`, although it has no `A[+ _]` structure.

Note that unfolding many times can be done ending to `R[A]`, although it has no `A[+ _]` structure.

Magic!

### `RecursiveStructure`

```scala
package psbp.external.specification.structure.recursive

import psbp.external.specification.types.&&

import psbp.external.specification.recursion.Recursion 

import psbp.external.specification.structure.Structure

type Recursive[R[+_[+ _]], A[+ _, + _]] = [Z] =>> R[[X] =>> A[Z, X]]

trait RecursiveStructure[
  A[+ _, + _]
  , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]
] extends RecursiveFolderType[A, >-->]
  with RecursiveUnfolderType[A, >-->]
  with Structure[Recursive[R, A], >-->]
```

where

```scala
package psbp.external.specification.structure.recursive

import psbp.external.specification.structure.FolderType

trait RecursiveFolderType[
  A[+ _, + _]
  , >-->[- _, + _]
  ] extends FolderType:

  // defined

  override type Folder[Y, X] = A[Y, X] >--> X
```

and where

```scala
package psbp.external.specification.structure.recursive

import psbp.external.specification.structure.UnfolderType

trait RecursiveUnfolderType[
  A[+ _, + _]
  , >-->[- _, + _]
  ] extends UnfolderType:

  // defined

  override type Unfolder[X, Y] = X >--> A[Y, X]
```

`RecursiveStructure` specifies the essential programming + recursive data structure ingredients for *collection-like* recursive data structure values of type `Recursive[R, A][Z] = R[[X] =>> A[Z, X]]`.

It is instructive to think of them as having type `R[A[Z, _]]`, with a `Z` at the left and with the `X` at the right *lost in recursion*.

## External specification level implementation `given`'s

### `programWithState`

```scala
package psbp.external.specification.programWithState

import psbp.external.specification.program.Program

import psbp.external.specification.program.state.State

given programWithState[
  S, 
  >-->[- _, + _]: Program
                : [>-->[- _, + _]] =>> State[S, >-->]
]: ProgramWithState[S, >-->] with
 
  private val program: Program[>-->] = 
    summon[Program[>-->]]

  export program.toProgram
  export program.andThen
  export program.construct
  export program.conditionally

  private val state: State[S, >-->] = 
    summon[State[S, >-->]]

  export state.`u>-->s`
  export state.`s>-->u`
```

Using injection by `import` of `programWithState`, a specification level `given` implementation of `ProgramWithState`, only `given` implementations of  `Program` and `State` need to be injected by `import`.

### `programWithParallel`

```scala
package psbp.external.specification.programWithParallel

import psbp.external.specification.program.Program

import psbp.external.specification.program.parallel.Parallel

given programWithParallel[
  >-->[- _, + _]: Program
                : Parallel
]: ProgramWithParallel[>-->] with
 
  private val program: Program[>-->] = summon[Program[>-->]]

  export program.toProgram
  export program.andThen
  export program.construct
  export program.conditionally

  private val parallel: Parallel[>-->] = summon[Parallel[>-->]]

  export parallel.parallel
```

Using injection by `import` of `programWithParallel`, a specification level `given` implementation of `ProgramWithParallel`, only `given` implementations of `Program` and `Parallel` need to be injected by `import`.

### `recursiveFoldingAndUnfoldingFromTraversable`

```scala
package psbp.external.specification.structure.recursive

import scala.language.postfixOps

import psbp.external.specification.types.&&

import psbp.external.specification.program.Classification

import psbp.external.specification.structure.Traversable

import psbp.external.specification.recursion.Recursion

import psbp.external.specification.recursion.RecursiveFoldingAndUnfolding

private[psbp] given recursiveFoldingAndUnfoldingFromTraversable[
  A[+ _]: [A[+ _]] =>> Traversable[A, >-->]
  , >-->[- _, + _]: Classification
]: RecursiveFoldingAndUnfolding[A, >-->] with
  
  val traversable: Traversable[A, >-->] =
    summon[Traversable[A, >-->]]
  import traversable.traverse  
  
  // defined

  private[psbp] def recursiveFold[
    Y 
    , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  ]: (A[Y] >--> Y) => (R[A] >--> Y) =
    `a[y]>-->y` =>
      val recursion: Recursion[R, >-->] = 
        summon[Recursion[R, >-->]]
      import recursion.`r[a]>-->a[r[a]]`

      lazy val `a[r[a]]>-->a[y]` = (recursiveFold andThen traverse)(`a[y]>-->y`)

      `r[a]>-->a[r[a]]` >--> `a[r[a]]>-->a[y]` >--> `a[y]>-->y`

  private[psbp] def recursiveUnfold[
    Z
    , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  ]: (Z >--> A[Z]) => (Z >--> R[A]) =
    `z>-->a[z]` =>
      val recursion: Recursion[R, >-->] = 
        summon[Recursion[R, >-->]]
      import recursion.`a[r[a]]>-->r[a]`

      lazy val `a[z]>-->a[r[a]]` = (recursiveUnfold andThen traverse)(`z>-->a[z]`)

      `z>-->a[z]` >--> `a[z]>-->a[r[a]]` >--> `a[r[a]]>-->r[a]`

  private[psbp] def recursiveUnfoldAndThenFold[
    Z, Y
    , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  ]: ((Z >--> A[Z]) && (A[Y] >--> Y)) => (Z >--> Y) =
    (`z>-->a[z]`, `a[y]>-->y`) =>

      val `z=>r[a]` = recursiveUnfold apply `z>-->a[z]`
      val `r[a]=>y` = recursiveFold apply `a[y]>-->y`

      `z=>r[a]` >--> `r[a]=>y`
```

Using injection by `import` of `recursiveFoldingAndUnfoldingFromTraversable`, a specification level `given` implementation of `RecursiveFoldingAndUnfolding`, only a `given` implementation of `Traversablle ` needs to be injected by `import`.

Local values with typeful names, `` `a[r[a]]>-->a[y]` `` and `` `a[z]>-->a[r[a]]` ``, are used to help understanding the *getting the types right* puzzle.

Note that `` `a[r[a]]>-->a[y]` `` and `` `a[z]>-->a[r[a]]` `` are `lazy val`'s in order to avoid a stack overflow.

The second paramater of the `>-->` extension of `Classification` is a call by name one, so the expressions defining the local values `` `a[r[a]]>-->a[y]` `` and `` `a[z]>-->a[r[a]]` `` can be substituded in the defining expressions of `recursiveUnfold` and `recursiveFold`.

## Internal specification `trait`'s

### `Resulting`

```scala
package psbp.internal.specification.computation

private[psbp] trait Resulting[C[+ _]]:

  // declared

  private[psbp] def result[Z]: Z => C[Z]
```

The `result` member of `Resulting` specifies that *a computation yields a result*.

### `Binding`

```scala
package psbp.internal.specification.computation

private[psbp] trait Binding[C[+ _]]:

  // declared

  private[psbp] def bind[Z, Y] (
    cz: C[Z]
    , `z=>cy`: => Z => C[Y]
  ): C[Y]

  // defined

  private[psbp] def join[Z] (ccz: C[C[Z]]): C[Z] =
    bind(ccz, identity)

  // defined extensions

  extension [Z, Y] (cz: C[Z]) 
    def >=(`z=>cy`: => Z => C[Y]): C[Y] =
      bind(cz, `z=>cy`)

  extension [Z, Y] (ccz: C[C[Z]]) 
    def joined =
      join(ccz)
```

The `bind` member of `Binding` specifies that *the result yielded by an inner computation can be used as an argument of a continuation function transforming it to a result that is an outer computation*.

The `join` member specifies *computation nesting*.

Compare this with *expression nesting*.

The member `>=` is an `extension` that can be used as infix operator.

The member `joined` is an `extension` that can be used as postfix operator.

### `Computation`

```scala
package psbp.internal.specification.computation

private[psbp] trait Computation[C[+ _]] 
  extends Resulting[C] 
  with Binding[C]
```

`Computation` combines the essential computation ingredients.

### Natural transformation `~>`

```scala
package psbp.internal.specification.naturalTransformation

private[psbp] trait ~>[F[+ _], T[+ _]]:

  // declared

  private[psbp] def apply[Z]: F[Z] => T[Z] 
```

A *natural transformation* of type `F ~> T` is similar to a generic function of type `F[Z] => T[Z]`.

Instead of *transforming at type level* it *transforms at unary type constructor level*.

### `Transformation`

```scala
package psbp.internal.specification.computation.transformation

import psbp.internal.specification.naturalTransformation.~>
  
private[psbp] trait Transformation[F[+ _], T[+ _]]:

  // declared

  private[psbp] val `f~>t`: F ~> T
```

A *transformation* has a natural transformation member `` `f~>t` `` it can delegate its transformation work to.

### `CoResulting`

```scala
package psbp.internal.specification.computation

private[psbp] trait CoResulting[C[+ _]]:

  // declared

  private[psbp] def coResult[Z]: C[Z] => Z
```

`CoResulting` specifies the dual of `Resulting`.

It turns out to be useful for reactive materialization.

Just like `Resulting` is the way to go form values to computations, `CoResulting` is, sometimes, useful as a way to go the other way rouns, from computations to values.

See [reactiveTransformedMaterialization](https://psbp-library.github.io#reactivetransformedmaterialization) for an example.

### `BiTraversable`

```scala
package psbp.internal.specification.structure

import psbp.external.specification.structure.Traversable

private[psbp] trait BiTraversable[A[+ _, + _], >-->[- _, + _]]:

  private[psbp] def leftTraversable[X]: Traversable[[Y] =>> A[Y, X], >-->] 

  private[psbp] def rightTraversable[Y]: Traversable[[X] =>> A[Y, X], >-->] 
```

`BiTraversable`, delagating to `leftTraversable` resp. `rightTraversable` to traverse at left resp. at right turns out to be important for recursive structures.

See [recursiveStructureFromBiTraversable](https://psbp-library.github.io#recursivestructurefrombitraversable) for more details.

### About the `FunctionLifting` related sections

*Lifting* value level ingredients to computation level ingredients is an important in the pointful world of computing.

In the pointfree programming world this lifting is less important.

Nevertheless this bot the `PSBP` library and its document pay attention to lifting.

### Function application

```scala
def functionApplication[Z, Y]: ((Z => Y) && Z) => Y =
  (`z=>y`, z) =>
    `z=>y` apply z
```

The definition of function application above uses

- a value (`z`)
- a function (`` `z=>y` ``)
- a product (`` (`z=>y`, z) ``)
- application (`` `z=>y` apply z ``)

The product `` (`z=>y`, z) `` is a specific version of a generic one `` (z, y) `` so, essentially the definition of function application above uses

- a value 
- a product
- application

where both the product and application involve

- a function

### `ValueLifting`

```scala
package psbp.internal.specification.lifting

private[psbp] trait ValueLifting[C[+ _]]:

  // declared

  private[psbp] def liftValue[Z]: Z => C[Z]
```

`liftValue` specifies that *values can be lifted to computations*.

### `ProductLifting`

```scala
package psbp.internal.specification.lifting

import psbp.external.specification.types.&&

import psbp.external.specification.function.`(z=>y)=>((z&&x)=>(y&&x)))`

private[psbp] trait ProductLifting[C[+ _]]:

  // declared

  private[psbp] def liftProduct2[Z, Y]: (C[Z] && C[Y]) => C[Z && Y]

  // defined
  
  private[psbp] def liftProduct3[Z, Y, X, W]: (C[Z] && C[Y] && C[X]) => (C[Z && Y && X]) = 
    `(z=>y)=>((z&&x)=>(y&&x)))`(liftProduct2) andThen liftProduct2

  // liftProduct4, ...  
```

where

```scala
package psbp.external.specification.function

// ...

// construction

// ...

def `(z=>y)=>((z&&x)=>(y&&x)))`[Z, Y, X]: (Z => Y) => ((Z && X) => (Y && X)) =
  `z=>y` => 
    (z, x) =>
      (`z=>y`(z), x)
```

is a function utility.

`liftProduct2`, `liftProduct3`, ... specify that *value products can be lifted to computations products*.

### `ApplicationLifting`

```scala
package psbp.internal.specification.lifting

import psbp.external.specification.types.&&

private[psbp] trait ApplicationLifting[C[+ _]]:

  // declared

  private[psbp] def liftApply[Z, Y]: (C[Z => Y] && C[Z]) => C[Y]
```

`liftApply` specifies that *value function application can be lifted to computation function application*.

### `FunctionApplicationLifting`

```scala
package psbp.internal.specification.lifting

import psbp.external.specification.types.&&

private[psbp] trait FunctionApplicationLifting[C[+ _]] 
  extends ValueLifting[C] 
  with ProductLifting[C] 
  with ApplicationLifting[C]
```

`FunctionApplicationLifting` combines the essential function application lifting ingredients.

### `Function0Lifting`

```scala
package psbp.internal.specification.lifting

private[psbp] trait Function0Lifting[C[+ _]]:

  // declared

  private[psbp] def lift0[Z]: Z => C[Z]
```

`lift0` specifies that *values, `0`-ary value functions, can be lifted to computations, `0`-ary computation functions*.

### `Function1Lifting`

```scala
package psbp.internal.specification.lifting

private[psbp] trait Function1Lifting[C[+ _]]:

  // declared

  private[psbp] def lift1[Z, Y]: (Z => Y) => C[Z] => C[Y]
```

`lift1` specifies that `1`-ary value functions, can be lifted to `1`-ary computation functions*.

### `Function2Lifting`

```scala
package psbp.internal.specification.lifting

import psbp.external.specification.types.&&

private[psbp] trait Function2Lifting[C[+ _]]:

  // declared

  private[psbp] def lift2[Z, Y, X]: ((Z && Y) => X) => (C[Z] && C[Y]) => C[X]
```

`lift2` specifies that `2`-ary value functions, can be lifted to `2`-ary computation functions*.

### `Function3Lifting`

```scala
package psbp.internal.specification.lifting

import psbp.external.specification.types.&&

private[psbp] trait Function3Lifting[C[+ _]]:

  // declared

  private[psbp] def lift3[Z, Y, X, W]: ((Z && Y && X) => W) => (C[Z] && C[Y] && C[X]) => C[W] 
```

`lift3` specifies that `3`-ary value functions, can be lifted to `3`-ary computation functions*.

### `FunctionLifting`

```scala
package psbp.internal.specification.lifting

import psbp.external.specification.types.{ 
  &&
  , || 
}

import psbp.external.specification.function.{ 
  `(z&&y)=>(z&&y)`
  , foldSum
  , `z=>(z||y)`
  , `y=>(z||y)`
  , and
  , or 
}

private[psbp] trait FunctionLifting[C[+ _]] 
  extends Function0Lifting[C] 
  with Function1Lifting[C] 
  with Function2Lifting[C] 
  with Function3Lifting[C]: // ...

  // defined
  
  private[psbp] def liftProduct[Z, Y]: (C[Z] && C[Y]) => C[Z && Y] =
    lift2(`(z&&y)=>(z&&y)`)

  private[psbp] def liftSum[Z, Y]: (C[Z] || C[Y]) => C[Z || Y] =
    foldSum(lift1(`z=>(z||y)`), lift1(`y=>(z||y)`)) 

  private[psbp] def liftAnd[Z, Y, X, W]: ((Z => C[X]) && (Y => C[W])) => ((Z && Y) => C[X && W]) =
      (`z=>cx`, `y=>cw`) =>
        and(`z=>cx`, `y=>cw`) andThen liftProduct    

  private[psbp] def liftOr[Z, Y, X, W]: ((Z => C[X]) && (Y => C[W])) => ((Z || Y) => C[X || W]) =
    (`z=>cx`, `y=>cw`) =>
      or(`z=>cx`, `y=>cw`) andThen liftSum
```

where

```scala
package psbp.external.specification.function

//

// construction

// ...

def `(z&&y)=>(z&&y)`[Z, Y]: (Z && Y) => (Z && Y) =
  identity     
    
def unfoldProduct[Z, Y, X](`z=>y`: Z => Y, `z=>x`: => Z => X): Z => (Y && X) =
  z =>
    (`z=>y`(z), `z=>x`(z))

def and[Z, Y, X, W]: ((Z => X) && (Y => W)) => (Z && Y) => (X && W) =
  (`z=>x`, `y=>w`) =>
    unfoldProduct(`(z&&y)=>z` andThen `z=>x`, `(z&&y)=>y` andThen `y=>w`)

// condition

// ...

def or[Z, Y, X, W]: ((X => Z) && (W => Y)) => (X || W) => (Z || Y) =
  (`x=>z`, `w=>y`) =>
    foldSum(`x=>z` andThen `z=>(z||y)`, `w=>y` andThen `y=>(z||y)`) 
```

are function utilties

`FunctionLifting` is simply a combination of `Function0Lifting`, `Function1Lifting`, `Function2Lifting`, `Function3Lifting` and ... , where some extra members have been added to.

## External specification level internal implementaton `given`'s

### `programFromComputation`

```scala
package psbp.external.implementation.computation

import psbp.external.specification.types.{ 
  &&
  , ||
}

import psbp.external.specification.function.foldSum

import psbp.external.specification.program.Program

import psbp.internal.specification.computation.Computation

private[psbp] given programFromComputation[
  C[+ _]: Computation]: Program[[Z, Y] =>> Z => C[Y]
] with
  
  private val computation: Computation[C] = 
    summon[Computation[C]]
  import computation.result

  private type `=>C`[-Z, +Y] = Z => C[Y]

  // defined

  private[psbp] override def toProgram[Z, Y]: (Z => Y) => Z `=>C` Y = 
    `z=>y` => 
      z =>
        result(`z=>y`(z))

  private[psbp] override def andThen[Z, Y, X]
    (`z>-->y`: Z `=>C` Y, `y>-->x`: => Y `=>C` X): Z `=>C` X =
    z =>
      `z>-->y`(z) >= 
        `y>-->x`

  private[psbp] override def construct[Z, Y, X]
    (`z>-->y`: Z `=>C` Y, `z>-->x`: => Z `=>C` X): Z `=>C` (Y && X) =
    z =>
      `z>-->y`(z) >= { y => 
        `z>-->x`(z) >= { x =>
          result(y, x)
        }
      }

  private[psbp] override def conditionally[Z, Y, X]
    (`y>-->z`: => Y `=>C` Z, `x>-->z`: => X `=>C` Z): (Y || X) `=>C` Z =
    foldSum(`y>-->z`, `x>-->z`)
```

`programFromComputation` is a `given` that defines the basic programming ingredients of a program of type `Z => C[Y]` in terms of the basic computing ingredients of a computation of type `C[Y]`.

Compare this with defining functions in terms of expressions.

Using injection by `import` of `programFromComputation`, a specification level `given` implementation of `Program`, only `given` implementations of `Computation` need to be injected by `import`.

## Internal specification level implementaton `given`'s

### `computationFromResultingAndBinding`

```scala
package psbp.internal.specification.computation

private[psbp] given computationFromResultingAndBinding[
  C[+ _]: Resulting
        : Binding
]: Computation[C] with

  private val resulting: Resulting[C] = summon[Resulting[C]]

  export resulting.result

  private val binding: Binding[C] = summon[Binding[C]]

  export binding.bind
```

Using injection by `import` of `computationFromResultingAndBinding`, a specification level `given` implementation of `Computation`, only `given` implementations of `Resulting` and `Binding` need to be injected by `import`.

### `functionApplicationLiftingFromComputation`

```scala
package psbp.internal.specification.computation

import psbp.external.specification.types.&&

import psbp.internal.specification.computation.Computation

import psbp.internal.specification.lifting.FunctionApplicationLifting

private[psbp] given functionApplicationLiftingFromComputation[
  C[+ _]: Computation
]: FunctionApplicationLifting[C] with

  private val computation: Computation[C] = 
    summon[Computation[C]]
  import computation.{ 
    result
    , bind
  }

  // defined

  private[psbp] def liftValue[Z]: Z => C[Z] = 
    z => 
      result(z)

  private[psbp] def liftProduct2[Z, Y]: (C[Z] && C[Y]) => C[Z && Y] =
    (cz, cy) =>
      bind(cz, { z => 
        bind(cy, { y => 
          result(z, y) 
        }) 
      })

  private[psbp] def liftApply[Z, Y]: (C[Z => Y] && C[Z]) => C[Y] =
    (cz2y, cz) =>
      bind(cz2y, { z2y => 
        bind(cz, { z => 
          result(z2y(z)) 
        })
      })
```

`functionApplicationLiftingFromComputation` is a `given` that defines the basic function application lifting ingredients in terms of the basic computing ingredients.

Compare this with function application being an expressions.

Using injection by `import` of `functionApplicationLiftingFromComputation`, a specification level `given` implementation of `FunctionApplicationLifting`, only `given` implementations of `Computation` need to be injected by `import`.

### `resultingFromTransformation`

```scala
package psbp.internal.specification.computation.transformation

import psbp.internal.specification.computation.Resulting

import psbp.internal.specification.naturalTransformation.~>
  
private[psbp] given resultingFromTransformation[
  F[+ _]: Resulting
  , T[+ _]: [T[+ _]] =>> Transformation[F, T]
]: Resulting[T] with   

  private val transformation: Transformation[F, T] = 
    summon[Transformation[F, T]]
  import transformation.`f~>t`

  // defined
  
  override private[psbp] def result[Z]: Z => T[Z] = 

    import `f~>t`.{ apply => `fz=>tz` }

    val resultingF: Resulting[F] = 
      summon[Resulting[F]]
    import resultingF.{ 
      result => `z=>fz` 
    }
    
    `z=>fz` andThen `fz=>tz
```

The `result` member of the `T[+ _]` computation is defined in terms of the `result` member of the `F[+ _]` computation and the `apply` member of the natural transformation `` `f~>t` ``.

Using injection by `import` of `resultingFromTransformation`, a specification level `given` implementation of `Resulting`, only `given` implementations of `Binding` need to be injected by `import`.

### `functionLiftingFromFunctionApplicationLifting`

```scala
package psbp.internal.specification.lifting

import psbp.external.specification.types.&&

private[psbp] given functionLiftingFromFunctionApplicationLifting[
  C[+ _]: FunctionApplicationLifting
]: FunctionLifting[C] with

  private val functionApplicationLifting: FunctionApplicationLifting[C] = 
    summon[FunctionApplicationLifting[C]]
  import functionApplicationLifting.{ 
    liftValue
    , liftProduct2
    , liftProduct3
    , liftApply
  }
  
  // defined
  
  override private[psbp] def lift0[Z]: Z => C[Z] =
    z =>
      liftValue(z)

  override private[psbp] def lift1[Z, Y]: (Z => Y) => C[Z] => C[Y] =
    `z=>y` => 
      cz =>
        liftApply(liftValue(`z=>y`), cz)  

  override private[psbp] def lift2[Z, Y, X]: ((Z && Y) => X) => (C[Z] && C[Y]) => C[X] =
    `(z&&y)=>x` =>
      `cz&&cy` =>
        liftApply(liftValue(`(z&&y)=>x`), liftProduct2(`cz&&cy`))      

  override private[psbp] def lift3[Z, Y, X, W]: ((Z && Y && X) => W) => (C[Z] && C[Y] && C[X]) => C[W] =
    (`((z&&y)&&x)=>w`: ((Z && Y && X) => W)) =>
      (`cz&&cy&&cx`: (C[Z] && C[Y] && C[X])) =>
        liftApply(liftValue(`((z&&y)&&x)=>w`), liftProduct3(`cz&&cy&&cx`))   

  // ...
```

Using injection by `import` of `functionLiftingFromFunctionApplicationLifting`, a specification level `given` implementation of `FunctionLifting`, only a `given` implementation of `FunctionApplicationLifting` needs to be injected by `import`.

### `recursiveStructureFromBiTraversable`

```scala
package psbp.internal.specification.structure.recursive

import scala.annotation.targetName

import scala.language.postfixOps

import psbp.external.specification.types.&&

import psbp.external.specification.program.Classification

import psbp.external.specification.structure.Traversable

import psbp.external.specification.recursion.Recursion

import psbp.external.specification.structure.recursive.RecursiveStructure

import psbp.internal.specification.structure.BiTraversable

private[psbp] given recursiveStructureFromBiTraversable[
  A[+ _, + _]: [A[+ _, + _]] =>> BiTraversable[A, >-->]
  , R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Classification
] : RecursiveStructure[A, R, >-->] with

  val recursion: Recursion[R, >-->] = 
    summon[Recursion[R, >-->]]
  import recursion.{ 
    `a[r[a]]>-->r[a]`
    , `r[a]>-->a[r[a]]`
  }

  val biTraversableAtLeft: BiTraversable[A, >-->] =
    summon[BiTraversable[A, >-->]]

  override private[psbp] def initialFolder[Y]: Folder[Y, R[[X] =>> A[Y, X]]] =
    `a[r[a]]>-->r[a]`

  override private[psbp] def initialUnfolder[Y]: Unfolder[R[[X] =>> A[Y, X]], Y] =
    `r[a]>-->a[r[a]]`  

  @targetName("fuseFolder")
  override private[psbp] def fuse[Z, Y, X]: (Z >--> Y && Folder[Y, X]) => Folder[Z, X] =
    (`z>-->y`, `a[y,x]>-->x`) =>
      val `a[z,x]>-->a[y,x]`: A[Z, X] >--> A[Y, X] = 
        biTraversableAtLeft.leftTraversable.traverse(`z>-->y`) 
      `a[z,x]>-->a[y,x]`>-->`a[y,x]>-->x`  
      
  @targetName("fuseUnfolder")
  override private[psbp] def fuse[X, Y, Z]: (Unfolder[X, Y] && Y >--> Z) => Unfolder[X, Z] =
    (`x>-->a[y,x]`, `y>-->z`) =>
      val `a[y,x]>-->a[z,x]`: A[Y, X] >--> A[Z, X] = 
        biTraversableAtLeft.leftTraversable.traverse(`y>-->z`) 
      `x>-->a[y,x]`>-->`a[y,x]>-->a[z,x]` 

  override def fold[Y, X]: Folder[Y, X] => R[[X] =>> A[Y, X]] >--> X =
    `a[y,x]>-->x` =>
      given traversable: Traversable[[X] =>> A[Y, X], >-->] = 
        biTraversableAtLeft.rightTraversable
      import psbp.external.specification.structure.recursive.{
        recursiveFoldingAndUnfoldingFromTraversable => recursiveFoldingAndUnfolding
      }  
      recursiveFoldingAndUnfolding.recursiveFold apply `a[y,x]>-->x` 

  override def unfold[X, Y]: Unfolder[X, Y] => X >--> R[[X] =>> A[Y, X]] =
    `x>-->a[y,x]` =>
      given traversable: Traversable[[X] =>> A[Y, X], >-->] = 
        biTraversableAtLeft.rightTraversable
      import psbp.external.specification.structure.recursive.{
        recursiveFoldingAndUnfoldingFromTraversable => recursiveFoldingAndUnfolding
      }  
      recursiveFoldingAndUnfolding.recursiveUnfold apply `x>-->a[y,x]`
```

At pointfree programming level `Traversable` in general and `BiTraversable` in particular play an essential role.

See [Traversable](https://psbp-library.github.io#traversable) for more details.

See [Bitraversable](https://psbp-library.github.io#bitraversable) for more details.

The lifting computing capabilities that play an essential role at the pointful computing level do not play an essential role.

Using injection by `import` of `recursiveStructureFromBiTraversable`, a specification level `given` implementation of `RecursiveStructure`, only a `given` implementation of `BiTraversable` needs to be injected by `import`.

## Internal implementation level implementation `given`'s

### `ReactiveTransformed`

```scala
package psbp.internal.implementation.computation.transformation

// ReactiveTransformed

private[psbp] type ReactiveTransformed[C[+ _]] = [Z] =>> (C[Z] => Unit) => Unit

// ...
```

A reactive transformed computation is a *computation callback handler* of type `(C[Z] => Unit) => Unit` handling a *computation callback*.

A computation callback is a callback of type `C[Z] => Unit` expecting a computation of type `C[Z]` to handle.

### `reactiveTransformedBinding`

```scala
package psbp.internal.implementation.computation.transformation

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.computation.Computation

import psbp.internal.specification.naturalTransformation.~>

import psbp.internal.specification.computation.transformation.Transformation

private[psbp] given reactiveTransformedBinding[
  C[+ _]: Computation]: 
  Transformation[C, ReactiveTransformed[C]] 
  with Binding[ReactiveTransformed[C]] with 

  private type F[+Z] = C[Z]
  private type T[+Z] = ReactiveTransformed[F][Z]
  
  private val computationF: Computation[F] = 
    summon[Computation[F]]
  import computationF.{ 
    result => resultF
    , bind => bindF
  }
    
  override private[psbp] val `f~>t`: F ~> T = new {
    def apply[Z]: F[Z] => T[Z] =
      fz => 
        _ apply fz
  }
  
  override private[psbp] def bind[Z, Y](
    tz: T[Z]
    , `z=>ty` : => Z => T[Y]
  ): T[Y] =
    `fy=>u` =>
      tz { 
        fz =>
          bindF(fz, { z =>
            resultF(`z=>ty`(z)(`fy=>u`))
            })
      }
```

Any computation of type `C[Z]` can, using `reactiveTransformedBinding`, be transformed to a `Binding` instance of type `(C[Z] => Unit) => Unit`. 

There is really only one meaningful way to define the `` `f~>t` `` and `bind` members in a way to get the types right.

This is a recurring theme in pure functional programming and, as a generalization, in program specification based programming.

Often an almost trivial choice for a function component, resp. programming component does the trick to get the types right.

For `` `f~>t` `` the trivial choice for a function component is the `Function` member `apply`. 

For `bind` the trivial choice for a programming component is a combination of the member `result` and `bind` of `F[+ _]`. 

### `reactiveTransformedMaterialization`

```scala
package psbp.internal.implementation.computation.transformation

import psbp.internal.specification.computation.Computation

import psbp.external.specification.materialization.Materialization

import psbp.internal.specification.computation.CoResulting

import psbp.internal.implementation.computation.transformation.ReactiveTransformed

private[psbp] given reactiveTransformedMaterialization[
  C[+ _]: Computation
        : CoResulting
        : [C[+ _]] =>> Materialization[[Z, Y] =>> Z => C[Y], Z, Y]
  , Z, Y
]: Materialization[[Z, Y] =>> Z => ReactiveTransformed[C][Y], Unit, Unit] with

  private type F[+Z] = C[Z]
  private type T[+Z] = ReactiveTransformed[F][Z] 

  private type `=>F`[-Z, +Y] = Z => F[Y]
  private type `=>T`[-Z, +Y] = Z => T[Y]

  private val coResultingF: CoResulting[F] = 
    summon[CoResulting[F]]
  import coResultingF.coResult

  val materialize: (Unit `=>T` Unit) => Unit => Unit =
    `u>-->u` =>
      u =>
        `u>-->u`(u)(coResult)
```

Transforming materialization of `[Z, Y] =>> Z => C[Y], Z, Y`, to materialization, of `[Z, Y] =>> Z => (C[Y] => Unit) => Unit, Unit, Unit`, is done using `reactiveTransformedMaterialization`. 

Again, there is really only one meaningful way to define the `materialize` member in a way to get the types right.

For `materialize` the trivial choice for a programming component is the member `coResult` of `CoResulting[F]`. 

Note that, since main programs of type `Unit >--> Unit` are meterialized, the reactive type `(C[Z] => Unit) => Unit`, involving `Unit`, is not sitting in the way.

### `FreeTransformed`

```scala
package psbp.internal.implementation.computation.transformation

//...

// FreeTransformed

private[psbp] enum Free[C[+ _], +Z]:

  private[psbp] case Transform[C[+ _], +Z](cz: C[Z]) extends Free[C, Z]

  private[psbp] case Result[C[+ _], +Z](z: Z) extends Free[C, Z]
  
  private[psbp] case Bind[C[+ _], -Z, ZZ <: Z, +Y]
    (fczz: Free[C, ZZ], `z=>fcy`: Z => FreeTransformed[C][Y]) extends Free[C, Y]

private[psbp] type FreeTransformed[C[+ _]] = [Z] =>> Free[C, Z]

// ...
```

A free transformed computation is an *algebraic data type* of type `Free[C[+ _], +Z]` representing a computation of type `C[Z]`, also referred to as a *computation ADT*.

It is defined as an `enum` that has the following `case`'s 

- `Transform` for transforming,
- `Result` for resulting,
- `Bind` for binding.

### `freeTransformedComputation`

```scala
package psbp.internal.implementation.computation.transformation

import psbp.external.specification.program.Program

import psbp.internal.specification.computation.Computation

import psbp.internal.specification.naturalTransformation.~>

import psbp.internal.specification.computation.transformation.Transformation

import Free._

private[psbp] given freeTransformedComputation[
  C[+ _]]: Transformation[C, FreeTransformed[C]
] with Computation[FreeTransformed[C]] with

  private type F[+Z] = C[Z]
  private type T[+Z] = FreeTransformed[F][Z] 
    
  override private[psbp] val `f~>t`: F ~> T = new {
    def apply[Z]: F[Z] => T[Z] =
      fz => 
        Transform(fz)
  }    

  override private[psbp] def result[Z]: Z => T[Z] =
    z =>
      Result(z)

  override private[psbp] def bind[Z, Y](
    tz: T[Z]
    , `z=>ty` : => Z => T[Y]
  ): T[Y] = 
    Bind(tz, `z=>ty`) 
```

Any computation of type `C[Z]` can, using `freeTransformedComputation`, be transformed to a `Computation` instance of type `Free[C, Y]`.

There is really only one meaningful way to define the `` `f~>t` ``, `result` and `bind` members in a way to get the types right.

The `` `f~>t` `` member unfolds a computation of type `F[Z]` in a computation ADT of type `T[Z]`. 

The `result` member unfolds a value of type `Z` in a computation ADT of type `T[Z]`. 

The  `bind` members unfolds a computation ADT of type `T[Z]` and a continuation function of type `Z => T[Y]` in a computation ADT of type `T[Z]`. 

`freeTransformedComputation` is defined in a completely different way as `reactiveTransformedComputation`. 

`freeTransformedComputation` does not make use of the computing ingredients of `F[+ _]` at all.

### `foldFree`

```scala
package psbp.internal.implementation.computation.transformation

// ...

import Free._

import psbp.internal.specification.computation.Computation

private[psbp] def foldFree[
  Z
  , C[+ _]: Computation
](
  fcz: FreeTransformed[C][Z]
 ): C[Z] =

  type F[+Z] = C[Z]
  type T[+Z] = FreeTransformed[F][Z]
 
  val computationF: Computation[F] = 
    summon[Computation[F]]
  import computationF.{ 
    result => resultF
    , bind => bindF
  } 

  fcz match {
    case Transform(fz) => 
      fz
    case Result(z) =>
      resultF(z)
    case Bind(Transform(fy), y2tz) =>
      bindF(resultF(fy), y2tz andThen foldFree)
    case Bind(Result(y), y2tz) =>
      foldFree(y2tz(y))
    case Bind(Bind(tx, x2ty), y2tz) =>
      foldFree(Bind(tx, { x => Bind(x2ty(x), y2tz) }))
    case any =>
      sys.error(s"Impossible, since, 'foldFree' eliminates the case for $any")
  }

// ...  
```

`foldFree` *folds* a computation ADT of type `T[Z]`, which is an unfolded computation of type `F[Z]`, back to computation of type `F[Z]`.

Although `foldFree` is not fully tail recursive, the `Scala` compiler performs tail recursive optimization for those cases where it is tail recursive (how cool is that!).

The one but last `case` in `foldFree` corresponds to the *associativity law of binding*.

Only one law is used to perform generic tail recursive optimization.

Performing specific tail recursive optimization involves using one or more laws.

### `freeTransformedMaterialization`

```scala
package psbp.internal.implementation.materialization

import psbp.external.specification.materialization.Materialization

import psbp.internal.specification.computation.Computation

import psbp.internal.implementation.computation.transformation.{ 
  FreeTransformed
  , foldFree
}

private[psbp] given freeTransformedMaterialization[
  C[+ _]: Computation
        : [C[+ _]] =>> Materialization[[Z, Y] =>> Z => C[Y], Z, Y]
  , Z, Y]: 
  Materialization[[Z, Y] =>> Z => FreeTransformed[C][Y], Z, Y] with

  private type F[+Z] = C[Z]
  private type T[+Z] = FreeTransformed[F][Z] 

  private type `=>F`[-Z, +Y] = Z => F[Y]
  private type `=>T`[-Z, +Y] = Z => T[Y]

  private val materializationF: Materialization[`=>F`, Z, Y] = 
    summon[Materialization[`=>F`, Z, Y]]
  import materializationF.{ 
    materialize => materializeF
  }  

  private def `tu=>fu`: T[Unit] => F[Unit] =
    foldFree

  override val materialize: (Unit `=>T` Unit) => Z => Y =
    `u=>tu` =>
      materializeF(`u=>tu` andThen `tu=>fu`)
```

Transforming materialization of `[Z, Y] =>> Z => C[Y], Z, Y`, to materialization, of `[Z, Y] =>> Z => Free[C, Y], Z, Y`, is done using `freeTransformedMaterialization`.

Again, there is really only one meaningful way to define the `materialize` member in a way to get the types right.

For `materialize` the trivial choice for a programming component is a combination of `foldFree` and the `materialize` member of `F[+ _]`. 

`freeTransformedMaterialization` is defined in a completely different way as `reactiveTransformedMaterialization`. 

`reactiveTransformedMaterialization` does not make use of the materialization ingredients of `F[+ _]` at all.

### `freeTransformedCoResulting`

```scala
package psbp.internal.implementation.computation.transformation

import psbp.internal.specification.computation.{ 
  Computation
  , CoResulting 
}

private[psbp] given freeTransformedCoResulting[
  C[+ _]: Computation
        : CoResulting
]: CoResulting[FreeTransformed[C]] with

  private type F[+Z] = C[Z]
  private type T[+Z] = FreeTransformed[F][Z] 
  
  private val coResultingF : CoResulting[F] = 
    summon[CoResulting[F]]
  import coResultingF.{ 
    coResult => `fz=>z` 
  }

  private def `tz=>fz`[Z]: T[Z] => F[Z] =
    foldFree

  override def coResult[Z]: T[Z] => Z =
    `tz=>fz` andThen `fz=>z`
```

Transforming co-resulting to free co-resulting is done using `freeTransformedCoResulting` which also makes use of `foldFree`.

### `StateTransformed`

```scala
package psbp.internal.implementation.computation.transformation

// StateTransformed

private[psbp] type StateTransformed[S, C[+ _]] = [Z] =>> S => C[(S, Z)]

// ...
```

A state transformed computation is a *computation state handler* of type `S => C[(S, Z)]` handling *computation state* of type `S` while computing.

### `stateTransformedBinding`

```scala
package psbp.internal.implementation.computation.transformation

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.computation.Computation

import psbp.internal.specification.naturalTransformation.~>

import psbp.internal.specification.computation.transformation.Transformation

private[psbp] given stateTransformedBinding[
  S
  , C[+ _]: Computation
]: Transformation[C, StateTransformed[S, C]] 
   with Binding[[Z] =>> StateTransformed[S, C][Z]] with
  
  private type F[+Z] = C[Z]
  private type T[+Z] = StateTransformed[S, C][Z]

  private type `=>T` = [Z, Y] =>> Z => T[Y]

  private val computationF: Computation[F] = 
    summon[Computation[F]]
  import computationF.{ 
    result => resultF
    , bind => bindF
  }

  override private[psbp] val `f~>t`: F ~> T = new {
    def apply[Z]: F[Z] => T[Z] =
      fz =>
        s =>
          bindF(fz, z => resultF((s, z)))
  }

  override private[psbp] def bind[Z, Y](
    tz: T[Z]
    , `z=>ty`: => Z => T[Y]
  ): T[Y] =
    s =>
      bindF(tz(s), (s, z) => `z=>ty`(z)(s)) 
```

### `stateTransformedState`

```scala
package psbp.internal.implementation.computation.transformation

import psbp.external.specification.program.state.State

import psbp.internal.specification.computation.Computation

private[psbp] given stateTransformedState[
  S
  , C[+ _]: Computation
]: State[
  S
  , [Z, Y] =>> Z => StateTransformed[S, C][Y]
] with 

  private type F[+Z] = C[Z]
  private type T[+Z] = StateTransformed[S, C][Z]

  private type `=>T` = [Z, Y] =>> Z => T[Y]

  private val computationF: Computation[F] = 
    summon[Computation[F]]
  import computationF.{ 
    result => resultF
    , bind => bindF 
  }

  override private[psbp] def `u>-->s`: Unit `=>T` S =
    _ => 
      s =>
        resultF((s, s))

  override private[psbp] def `s>-->u`: S `=>T` Unit =
    s => 
      _ =>
        resultF((s, ()))
```

Any computation of type `C[Z]` can, using `stateTransformedBinding`, be transformed to a `Binding` and `State` instance of type `S => C[(S, Z)]`.

There is really only one meaningful way to define the `` `f~>t` ``, `bind` `` `u>-->s` `` and `` `s>-->u` `` members in a way to get the types right.

`` `f~>t` `` trivially uses a computation as a computation that handles computation state, not doing any computing and not doing any computation state handling. 

`bind` member binds a computation that handles computation state not doing any state handling along the way. 

`` `u>-->s` `` member reads the state not doing any computing.

`` `s>-->u` `` member writes the state not doing any computing.

### `stateTransformedMaterialization`

```scala
package psbp.internal.implementation.materialization

import psbp.external.specification.materialization.Materialization

import psbp.external.specification.program.state.Initial

import psbp.internal.specification.computation.Computation

import psbp.internal.implementation.computation.transformation.StateTransformed

private[psbp] given stateTransformedMaterialization[
  S: Initial
  , C[+ _]: Computation
          : [C[+ _]] =>> Materialization[[Z, Y] =>> Z => C[Y], Z, Y]
  , Z, Y
]: Materialization[[Z, Y] =>> Z => StateTransformed[S, C][Y], Z, C[Y]] with

  private type F[+Z] = C[Z]
  private type T[+Z] = StateTransformed[S, C][Z]

  private type `=>F`[-Z, +Y] = Z => F[Y]
  private type `=>T`[-Z, +Y] = Z => T[Y]

  private val Materialization: Materialization[`=>F`, Z, Y] = 
    summon[Materialization[`=>F`, Z, Y]]
  import Materialization.{ 
    materialize => materializeF 
  }

  private val computation: Computation[C] = 
    summon[Computation[F]]
  import computation.{ 
    result => resultF
    , bind => bindF 
  }  

  private val initial: Initial[S] = 
    summon[Initial[S]]
  import initial.{ 
    s => initialS 
  }

  override val materialize: (Unit `=>T` Unit) => Z => C[Y] =
    `u=>tu` =>
      z =>
        bindF(
          `u=>tu`(())(initialS)
          , (s, _) => resultF(materializeF(resultF)(z))
        )
```

Transforming materialization of `[Z, Y] =>> Z => C[Y], Z, Y`, to materialization, of `[Z, Y] =>> Z => (S => C[(S, Y)]), Z, C[Y]`, is done using `stateTransformedMaterialization` which makes use of `initialS`. 

Again, there is really only one meaningful way to define the `materialize` member in a way to get the types right.

For `materialize` the trivial choice for a programming component is a combination of the `result`, `bind` and `materialize` members of `F[+ _]`. 

`stateTransformedMaterialization` is defined in a completely different way as `reactiveTransformedMaterialization`. 

`reactiveTransformedMaterialization` does not make use of the materialization ingredients of `F[+ _]` at all.

### `reactiveTransformedParallel`

```scala
package psbp.internal.implementation.computation.transformation

import akka.actor.typed.{ 
  ActorSystem
  , ActorRef
  , Behavior 
}

import akka.actor.typed.scaladsl.{ 
  ActorContext
  , Behaviors
}

import Behaviors.{ 
  receive
  , stopped 
}

import ch.qos.logback.classic.{ 
  Logger
  , LoggerContext, Level
}

import Level.{ 
  INFO
  , ERROR
}

import org.slf4j.LoggerFactory.getILoggerFactory

import language.implicitConversions

import psbp.external.specification.types.&&

import psbp.external.specification.program.parallel.Parallel

import psbp.internal.specification.computation.Computation

import psbp.internal.implementation.computation.transformation.ReactiveTransformed

val packageName = "psbp.internal.implementation.computation.transformation"

def logInfo[Z](actorContext: ActorContext[Z])(message: String): Unit = {
  val logger: Logger = getILoggerFactory().asInstanceOf[LoggerContext].getLogger(packageName);
  logger.setLevel(INFO);
  actorContext.log.info(message)
  logger.setLevel(ERROR);  
}

private[psbp] given reactiveTransformedParallel[
  C[+ _]: Computation]
        : Parallel[[Z, Y] =>> Z => ReactiveTransformed[C][Y]
] with

  private type F[+Z] = C[Z]
  private type T[+Z] = ReactiveTransformed[C][Z]
  
  private val computation: Computation[F] = 
    summon[Computation[F]]
  import computation.{ 
    result => resultF
    , bind => bindF 
  }
  
  override private[psbp] def parallel[Z, Y, X, W](
    `z=>tx`: Z => T[X]
    , `y=>tw`: Y => T[W]
  ): (Z && Y) => T[X && W] =
    (z, y) => 
      `f(x,w)=>u` =>
        val `(x,w)=>f(x,w)` = resultF[(X && W)]
        val `(x,w)=>u` = `(x,w)=>f(x,w)` andThen `f(x,w)=>u`
  
        lazy val reactor = ActorSystem(Reactor(), s"reactor")
        lazy val leftActor = ActorSystem(LeftActor(reactor), s"leftActor")
        lazy val rightActor = ActorSystem(RightActor(reactor), s"rightActor")
  
        import Reactor.React
        import React.{ LeftReact, RightReact }
  
        import LeftActor.LeftAct
        import RightActor.RightAct
  
        object LeftActor:
  
          case object LeftAct
  
          def apply(reactor: ActorRef[React[X, W]]): Behavior[LeftAct.type] =
            receive { (context, message) =>
              val info = logInfo(context)
              info(s"leftActor received LeftAct")
              val tx: T[X] = `z=>tx`(z)
              tx { 
                fx =>
                  bindF (fx, { 
                    x =>
                      resultF(reactor ! LeftReact(x))
                  })
              }
              stopped
            }
          
        object RightActor:
  
          case object RightAct
  
          def apply(reactor: ActorRef[React[X, W]]): Behavior[RightAct.type] =
            receive { (context, message) =>
              val info = logInfo(context)
              info(s"rightActor received RightAct")
              val tw: T[W] = `y=>tw`(y)
              tw { 
                fw =>
                  bindF( fw, { 
                    w =>
                      resultF(reactor ! RightReact(w))
                  })
              }
              stopped
            }
  
        object Reactor:
  
          enum React[X, W]:
            case LeftReact(x: X) extends React[X, W]
            case RightReact(w: W) extends React[X, W]
  
          def react(`option[x]`: Option[X], `option[w]`: Option[W]): Behavior[React[X, W]] =
            receive { (context, message) =>
              message match {
                case LeftReact(x) => 
                  `option[w]` match {
                    case Some(w) =>
                      val info = logInfo(context)
                      info(s"reactor received both LeftReact($x) and RightReact($w)")
                      `(x,w)=>u`(x,w)
                      stopped
                    case None => 
                      react(Some(x), None)
                  }
                case RightReact(w) => 
                  `option[x]` match {
                    case Some(x) => 
                      val info = logInfo(context)
                      info(s"reactor received both RightReact($w) and LeftReact($x)")
                      `(x,w)=>u`(x,w)
                      stopped
                    case None => 
                      react(None, Some(w))
                  }              
              }
            }  
  
          def apply(): Behavior[React[X, W]] =
            react(None, None)      
  
        leftActor ! LeftAct
        rightActor ! RightAct
  
  override private[psbp] def asynchronous[Z, Y](`z=>ty`: Z => T[Y]): Z => T[Y] =
    z => 
      `fy=>u` =>
        val `y=>fy` = resultF[Y]
        val `y=>u` = `y=>fy` andThen `fy=>u`
    
        lazy val reactor = ActorSystem(Reactor(), s"reactor")
        lazy val actor = ActorSystem(Actor(reactor), s"actor")        
         
        import Reactor.React
        import Actor.Act
     
        object Actor:
    
          case object Act
    
          def apply(reactor: ActorRef[React[Y]]): Behavior[Act.type] =
            receive { (context, message) =>
              val ty: T[Y] = `z=>ty`(z)
              ty { 
                fy =>
                  bindF(fy, { 
                    y =>
                      resultF(reactor ! React(y))
                  })
              }
              stopped
            }
            
        object Reactor:
    
          case class React[Y](y: Y)
    
          def react(`option[y]`: Option[Y]): Behavior[React[Y]] =
            receive { (context, message) =>
              message match {
                case React(y) => 
                  val info = logInfo(context)
                  info(s"reactor received React($y)")
                  `y=>u`(y)
                  stopped
              }            
            }
    
          def apply(): Behavior[React[Y]] =
            react(None)
        
        actor ! Act       
```

Any computation of type `C[Z]` can, using `reactiveTransformedParallel`, be transformed to a `Parallel` instance of type `(C[Z] => Unit) => Unit`. 

Implementing parallelism makes use of an akka actors and logback logging.

Actor `leftActor` and actor `rightActor` act together in parallel sending their results to actor `reactor` to react to.

Some `logging` has been added to illustrate how `leftActor` and `rightActor` act, resp. `reactor` reacts.

### `noneBiTraversable`

```scala
package psbp.internal.implementation.structure.recursive

import scala.language.postfixOps

import psbp.external.specification.program.Functional

import psbp.external.specification.structure.Traversable

import psbp.internal.specification.structure.BiTraversable

given noneBiTraversable[>-->[- _, + _]: Functional]: BiTraversable[None, >-->] with

  private val functional: Functional[>-->] = summon[Functional[>-->]]

  override private[psbp] def leftTraversable[X]: Traversable[[Y] =>> None[Y, X], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => Unit >--> Unit =
        _ =>
          functional.identity
    }

  override private[psbp] def rightTraversable[X]: Traversable[[Y] =>> None[X, Y], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => Unit >--> Unit =
        _ =>
          functional.identity
    }
```

where

```scala
package psbp.internal.implementation.structure.recursive

private[psbp] type None[+Y, +X] = Unit

// ...
```

### `identityBiTraversable`

```scala
package psbp.internal.implementation.structure.recursive

import scala.language.postfixOps

import scala.{ 
  Predef => function 
}

import psbp.external.specification.program.Functional

import psbp.external.specification.structure.Traversable 

import psbp.internal.specification.structure.BiTraversable

given identityBiTraversable[>-->[- _, + _]: Functional]: BiTraversable[Identity, >-->] with

  private val functional: Functional[>-->] = summon[Functional[>-->]]

  override private[psbp] def leftTraversable[X]: Traversable[[Y] =>> Identity[Y, X], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => X >--> X =
        _ =>
          functional.identity
    }
   
  override private[psbp] def rightTraversable[X]: Traversable[[Y] =>> Identity[X, Y], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => Z >--> Y =
        function.identity
    }
```

where

```scala
package psbp.internal.implementation.structure.recursive

// ...

private[psbp] type Identity[+Y, +X] = X

// ...
```

### `constantBiTraversable`

```scala
package psbp.internal.implementation.structure.recursive

import scala.{ 
  Predef => function
}

import scala.language.postfixOps

import psbp.external.specification.program.Functional

import psbp.external.specification.structure.Traversable

import psbp.internal.specification.structure.BiTraversable

given constantBiTraversable[>-->[- _, + _]: Functional]: BiTraversable[Constant, >-->] with

  override private[psbp] def leftTraversable[X]: Traversable[[Y] =>> Constant[Y, X], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => Z >--> Y =
        function.identity
    }
   
  private val functional: Functional[>-->] = summon[Functional[>-->]]

  override private[psbp] def rightTraversable[X]: Traversable[[Y] =>> Constant[X, Y], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => X >--> X =
        _ =>
          functional.identity
    }
```

where

```scala
package psbp.internal.implementation.structure.recursive

// ...

private[psbp] type Constant[+Y, +X] = Y

// ...
```

### `productBiTraversable`

```scala
package psbp.internal.implementation.structure.recursive

import scala.language.postfixOps

import psbp.external.specification.program.Program

import psbp.external.specification.structure.Traversable

import psbp.internal.specification.structure.BiTraversable

given productBiTraversable[
  L[+ _, + _]: [L[+ _, + _]] =>> BiTraversable[L, >-->]
  , R[+ _, + _]: [R[+ _, + _]] =>> BiTraversable[R, >-->]
  , >-->[- _, + _]: Program
]: BiTraversable[Product[L, R], >-->] with

  private val leftBiTraversable =
    summon[BiTraversable[L, >-->]]
  private val rightBiTraversable = 
    summon[BiTraversable[R, >-->]]

  import leftBiTraversable.{ 
    leftTraversable => leftLeftTraversable 
  }
  import rightBiTraversable.{ 
    leftTraversable => rightLeftTraversable
  }

  // todo: also try out with parallelism

  private[psbp] def leftTraversable[X] : Traversable[[Y] =>> Product[L, R][Y, X], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => Product[L, R][Z, X] >--> Product[L, R][Y, X] =
          `z>-->y` =>
            leftLeftTraversable.traverse(`z>-->y`) &&& rightLeftTraversable.traverse(`z>-->y`)
   }

  import leftBiTraversable.{ 
    rightTraversable => leftRightTraversable 
  }
  import rightBiTraversable.{ 
    rightTraversable => rightRightTraversable 
  }

  // todo: also try out with parallelism

  private[psbp] def rightTraversable[X] : Traversable[[Y] =>> Product[L, R][X, Y], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => Product[L, R][X, Z] >--> Product[L, R][X, Y] =
          `z>-->y` =>
            leftRightTraversable.traverse(`z>-->y`) &&& rightRightTraversable.traverse(`z>-->y`)
   } 
```

where

```scala
package psbp.internal.implementation.structure.recursive

import psbp.external.specification.types.&&

// ...

private[psbp] type Product[L[+ _, + _], R[+ _, + _]] = [Y, X] =>> L[Y, X] && R[Y, X] 

// ...
```

### `sumBiTraversable`

```scala
package psbp.internal.implementation.structure.recursive

import scala.language.postfixOps

import psbp.external.specification.program.Program

import psbp.external.specification.structure.Traversable

import psbp.internal.specification.structure.BiTraversable

given sumBiTraversable[
  L[+ _, + _]: [L[+ _, + _]] =>> BiTraversable[L, >-->]
  , R[+ _, + _]: [R[+ _, + _]] =>> BiTraversable[R, >-->]
  , >-->[- _, + _]: Program
]: BiTraversable[Sum[L, R], >-->] with

  private val leftBiTraversable =
    summon[BiTraversable[L, >-->]]
  private val rightBiTraversable =
    summon[BiTraversable[R, >-->]]

  import leftBiTraversable.{ 
    leftTraversable => leftLeftTraversable 
  }
  import rightBiTraversable.{ 
    leftTraversable => rightLeftTraversable 
  }

  private[psbp] def leftTraversable[X] : Traversable[[Y] =>> Sum[L, R][Y, X], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => Sum[L, R][Z, X] >--> Sum[L, R][Y, X] =
          `z>-->y` =>
            leftLeftTraversable.traverse(`z>-->y`) ||| rightLeftTraversable.traverse(`z>-->y`)
   }

  import leftBiTraversable.{ 
    rightTraversable => leftRightTraversable 
  }
  import rightBiTraversable.{ 
    rightTraversable => rightRightTraversable 
  }

  private[psbp] def rightTraversable[X] : Traversable[[Y] =>> Sum[L, R][X, Y], >-->] =
    new {
      override def traverse[Z, Y]: (Z >--> Y) => Sum[L, R][X, Z] >--> Sum[L, R][X, Y] =
          `z>-->y` =>
            leftRightTraversable.traverse(`z>-->y`) ||| rightRightTraversable.traverse(`z>-->y`)
   }
```

where

```scala
package psbp.internal.implementation.structure.recursive

import psbp.external.specification.types.{ 
  &&
  , ||
}

// ...
   
private[psbp] type Sum[L[+ _, + _], R[+ _, + _]] = [Y, X] =>> L[Y, X] || R[Y, X] 
```

### `activeResulting`

```scala
package psbp.external.implementation.active

import psbp.internal.specification.computation.Resulting

given activeResulting: Resulting[Active] with

  private[psbp] def result[Z]: Z => Active[Z] =
    z =>
      z
```

### `activeBinding`

```scala
package psbp.external.implementation.active

import psbp.internal.specification.computation.Binding

given activeBinding: Binding[Active] with

  private[psbp] def bind[Z, Y](
    cz: Active[Z]
    , `z=>cy`: => Z => Active[Y]
  ): Active[Y] =
    `z=>cy`(cz)
```

### `activeCoResulting`

```scala
package psbp.external.implementation.active

import psbp.internal.specification.computation.CoResulting

given activeCoResulting: CoResulting[Active] with
  override def coResult[Z]: Active[Z] => Z =
    identity
```

## External specification internal implementation `given`'s

### `activeProgram` 

```scala
package psbp.external.implementation.active

import psbp.external.specification.program.Program

import psbp.external.implementation.computation.programFromComputation

import psbp.internal.specification.computation.computationFromResultingAndBinding

given activeProgram: Program[`=>A`] = programFromComputation[Active]
```

where

```scala
package psbp.implementation.active

type Active[+Y] = Y

type `=>A`[-Z, +Y] = Z => Active[Y]
```

A completely trivial way to implement computations of type `C[Y]` is as values of type `Y`, referred to as *active computations*.

As a consequence, programs of type `Z => C[Y]`, are implemented as functions of type `Z => Y`, referred to as *active programs*.

`Active` is a  `given` implementation of `Computation`.

As a consequence, `` `=>A` `` is an `given` implementation of `Program`.

### `activeMaterialization` 

```scala
package psbp.implementation.active

import psbp.external.specification.materialization.Materialization

given activeMaterialization: Materialization[`=>A`, Unit, Unit] with

  val materialize: (Unit `=>A` Unit) => Unit => Unit =
    identity
```

Materialization of `` `=>A` `` is completely trivial.

### Pure functional programming

Think of active programming as *pure functional programming*.

Of course, using `Scala`, it is possible to write functions that perform side effects along the way of transforming their argument to a result.

For example, *inside* of the active materialized implementation of `mainFactorial` , `intProducer` and `factorialConsumer` perform side effects.

See [mainFactorial](https://psbp-library.github.io#mainfactorial) for more details.

The intention is to replace those side effects by effects and to push them to the outside border.

### `reactiveProgram`

```scala
package psbp.external.implementation.reactive

import psbp.external.specification.program.Program

import psbp.external.implementation.computation.programFromComputation

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.specification.computation.transformation.resultingFromTransformation

import psbp.internal.implementation.computation.transformation.reactiveTransformedBinding

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ activeResulting, activeBinding }

given reactiveBinding: Binding[Reactive] = reactiveTransformedBinding[Active]

given reactiveProgram: Program[`=>R`] = programFromComputation[Reactive]
```

where

```scala
package psbp.implementation.reactive

import psbp.internal.implementation.computation.transformation.ReactiveTransformed

import psbp.implementation.active.Active

type Reactive = [Y] =>> ReactiveTransformed[Active][Y] 

type `=>R` = [Z, Y] =>> Z => Reactive[Y]
```

Transforming from `active` programming with `` `=>A` `` to `reactive` programming with `` `=>R` `` is done by using a combination of `programFromComputation`, `reactiveTransformedBinding`, and friends `computationFromResultingAndBinding` and `resultingFromTransformation`.

See [programFromComputation](https://psbp-library.github.io#programfromcomputation), [reactiveTransformedBinding](https://psbp-library.github.io#reactivetransformedbinding), [computationFromResultingAndBinding](https://psbp-library.github.io#computationfromresultingandbinding) and [resultingFromTransformation](https://psbp-library.github.io#resultingfromtransformation) for more details.

### `reactiveMaterialization`

```scala
package psbp.external.implementation.reactive

import psbp.external.specification.materialization.Materialization

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.implementation.computation.transformation.reactiveTransformedMaterialization

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  activeResulting
  , activeBinding
  , activeMaterialization
  , activeCoResulting
}

given reactiveMaterialization: Materialization[`=>R`, Unit, Unit] =
  reactiveTransformedMaterialization[Active, Unit, Unit]
```

Transforming from `active` materialization of `` `=>A` `` to `reactive` materialization of `` `=>R` `` is done by using `reactiveTransformedMaterialization` and friend `computationFromResultingAndBinding`.

See [reactiveTransformedMaterialization](https://psbp-library.github.io#reactivetransformedmaterialization) and [computationFromResultingAndBinding](https://psbp-library.github.io#computationfromresultingandbinding) for more details.

### `freeActiveProgram`

```scala
package psbp.external.implementation.freeActive

import psbp.external.specification.program.Program

import psbp.external.implementation.active.Active

import psbp.external.implementation.computation.programFromComputation

import psbp.internal.specification.computation.Computation

import psbp.internal.implementation.computation.transformation.freeTransformedComputation

given freeActiveComputation: Computation[FreeActive] = freeTransformedComputation[Active]

given freeActiveProgram: Program[`=>FA`] = programFromComputation[FreeActive]
```

where

```scala
package psbp.implementation.freeActive

import psbp.internal.implementation.computation.transformation.FreeTransformed

import psbp.implementation.active.Active

type FreeActive = [Y] =>> FreeTransformed[Active][Y] 

type `=>FA`= [Z, Y] =>> Z => FreeActive[Y]
```

Transforming from `active` programming with `` `=>A` `` to `freeActive` programming with `` `=>FA` `` is done by using a combination of `programFromComputation` and `freeTransformedComputation`.

See [programFromComputation](https://psbp-library.github.io#programfromcomputation) and [freeTransformedComputation](https://psbp-library.github.io#freetransformedcomputation) for more details.

### `freeActiveMaterialization`

```scala
package psbp.external.implementation.freeActive

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  activeResulting
  , activeBinding
  , activeMaterialization 
}

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.implementation.materialization.freeTransformedMaterialization

given freeActiveMaterialization: Materialization[`=>FA`, Unit, Unit] =
  freeTransformedMaterialization[Active, Unit, Unit]
```

Transforming from `active` materialization of `` `=>A` `` to `freeActive` materialization of `` `=>FA` `` is done by using `freeTransformedMaterialization` and friend `computationFromResultingAndBinding`.

See [freeTransformedMaterialization](https://psbp-library.github.io#freetransformedmaterialization) and [computationFromResultingAndBinding](https://psbp-library.github.io#computationfromresultingandbinding) for more details.

### `stateActiveProgram`

```scala
package psbp.external.implementation.stateActive

import psbp.external.specification.program.Program

import psbp.external.implementation.computation.programFromComputation

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  activeResulting
  , activeBinding
}

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.specification.computation.transformation.resultingFromTransformation

import psbp.internal.implementation.computation.transformation.stateTransformedBinding

given stateActiveBinding[S]: Binding[StateActive[S]] = stateTransformedBinding[S, Active]

given stateActiveProgram[S]: Program[`=>SA`[S]] = programFromComputation[StateActive[S]]
```

where

```scala
package psbp.implementation.stateActive

import psbp.internal.implementation.computation.transformation.StateTransformed

import psbp.implementation.active.Active

type StateActive[S] = [Y] =>> StateTransformed[S, Active][Y] 

type `=>SA`[S] = [Z, Y] =>> Z => StateActive[S][Y]
```

### `stateActiveState`

```scala
package psbp.external.implementation.stateActive

import psbp.external.specification.program.state.State

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ activeResulting, activeBinding }

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.implementation.computation.transformation.stateTransformedState

given stateActiveState[S]: State[S, `=>SA`[S]] = stateTransformedState[S, Active]
```
Transforming from `active` programming with `` `=>A` `` to `active` programming with state `` `=>SA[S]` `` is done by using a combination of `programFromComputation`, `stateTransformedBinding`, `stateTransformedState`, and friends `computationFromResultingAndBinding` and `resultingFromTransformation`.

See [programFromComputation](https://psbp-library.github.io#programfromcomputation), [stateTransformedBinding](https://psbp-library.github.io#statetransformedbinding), [stateTransformedState](https://psbp-library.github.io#statetransformedstate), [computationFromResultingAndBinding](https://psbp-library.github.io#computationfromresultingandbinding) and [resultingFromTransformation](https://psbp-library.github.io#resultingfromtransformation) for more details.

### `stateActiveMaterialization`

```scala
package psbp.external.implementation.stateActive

import psbp.external.specification.types.&&

import psbp.external.specification.program.state.Initial

import psbp.external.specification.materialization.

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  activeResulting
  , activeBinding
  , activeMaterialization 
}

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.implementation.materialization.stateTransformedMaterialization

given stateActiveMaterialization[S: Initial]: Materialization[`=>SA`[S], Unit, Unit] =
  stateTransformedMaterialization[S, Active, Unit, Unit]
```

Transforming from `active` materialization of `` `=>A` `` to `active` materialization with state of `` `=>SA`[S] `` is done by using `stateTransformedMaterialization` and friend `computationFromResultingAndBinding`.

See [stateTransformedMaterialization](https://psbp-library.github.io#stateTransformedmaterialization) and [computationFromResultingAndBinding](https://psbp-library.github.io#computationfromresultingandbinding) for more details.

### `reactiveParallel`

```scala
package psbp.external.implementation.reactive

import psbp.external.specification.program.parallel.Parallel

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.external.implementation.active.Active

import psbp.external.implementation.active.{ 
  activeResulting
  , activeBinding 
}

import psbp.internal.implementation.computation.transformation.reactiveTransformedParallel

given reactiveParallel: Parallel[`=>R`] = reactiveTransformedParallel[Active]
```

Transforming from `active` programming with `` `=>A` `` to `active` programming with parallelism  of`` `=>R` `` is done by using `reactiveTransformedParallel` and friend `computationFromResultingAndBinding`.

[reactiveTransformedParallel](https://psbp-library.github.io#reactivetransformedparallel) and [computationFromResultingAndBinding](https://psbp-library.github.io#computationfromresultingandbinding) for more details.

## TODO

```scala
package psbp.external.implementation.rec

import scala.language.postfixOps

import psbp.external.specification.program.Program

import psbp.external.specification.recursion.Recursion

given recRecursion[>-->[- _, + _]: Program]: Recursion[Rec, >-->] with
  override private[psbp] def `r[a]>-->a[r[a]]`[A[+ _]]: Rec[A] >--> A[Rec[A]] =
    `rec[a]=>a[rec[a]]` asProgram
  override private[psbp] def `a[r[a]]>-->r[a]`[A[+ _]]: A[Rec[A]] >--> Rec[A] =
    `a[rec[a]]=>rec[a]` asProgram
```

where

```scala
package psbp.external.implementation.rec

case class Rec[+A[+ _]](`a[rec[a]]`: A[Rec[A]])

private[psbp] def `rec[a]=>a[rec[a]]`[A[+ _]]: Rec[A] => A[Rec[A]] = _.`a[rec[a]]`

private[psbp] def `a[rec[a]]=>rec[a]`[A[+ _]]: A[Rec[A]] => Rec[A] = Rec(_)
```

and

```scala

```

where

```scala
package psbp.external.implementation.list

import psbp.internal.implementation.structure.recursive.{ 
  Sum
  , None
  , Product
  , Constant
  , Identity
}

type List[+Y, +X] = Sum[None, Product[Constant, Identity]][Y, X]
```

with

```scala
package psbp.external.implementation.list

import psbp.external.specification.program.Program

import psbp.internal.specification.structure.BiTraversable 

import psbp.internal.implementation.structure.recursive.{ 
  Sum, None, Product, Constant, Identity,  
  sumBiTraversable, 
  noneBiTraversable, 
  productBiTraversable, 
  constantBiTraversable, 
  identityBiTraversable
  }

private[psbp] given listBiTraversable[>-->[- _, + _]: Program]: BiTraversable[List, >-->] = 
  given BiTraversable[Product[Constant, Identity], >-->] = productBiTraversable[Constant, Identity, >-->]
  sumBiTraversable[None, Product[Constant, Identity], >-->]
```

and

```scala
package psbp.external.implementation.list

import psbp.external.specification.program.Program

import psbp.external.specification.structure.recursive.RecursiveStructure

import psbp.internal.specification.structure.recursive.recursiveStructureFromBiTraversable

import psbp.external.specification.recursion.Recursion

given listRecursiveStructure[
  R[+_[+ _]]: [R[+_[+ _]]] =>> Recursion[R, >-->]
  , >-->[- _, + _]: Program
  ]: RecursiveStructure[List, R, >-->] = 
    recursiveStructureFromBiTraversable[List, R, >-->]
```




