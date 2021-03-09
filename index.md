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

# Program Specification Based Programming

This document describes `PSBP`, a `Scala` library for *Program Specification Based Programming*.

```scala
//          ________    ________     __         _______
//         / ___   /\  / ______/\   / /\       / ___  /\
//        / /  /  / / / /\_____\/  / / /      / /  / / /
//       / /__/  / / / /_/____    / /_/__    / /__/ / /
//      / ______/ / /______  /\  / __   /\  / _____/ /   
//     / / _____\/  \____ / / / / /  / / / / / ____\/
//    / / /        ______/ / / / /__/ / / / / /  
//   /_/ /        /_______/ / /______/ / /_/ /
//   \_\/         \_______\/  \______\/  \_\/
//          
```

[source code](https://github.com/PSBP-Library/psbp-library.github.io)

## Warning

This document uses vocabulary that you may not be used to.

Hopefully you can relate the vocabulary to the one you are used to.

## Introduction

The `PSBP` library strictly separates *specifications* from *implementations*.

Specifications *declare* members. 

Implementations *define* members.

The `PSBP` library also strictly separates *external* specifications and corresponding implementations from *internal* specifications and corresponding implementations.

Specifications, both internal and external, are defined as `trait`'s that declare members for *binary type constructors* and *unary type constructors* for them to belong to *type constructor classes*. 

Implementations, both internal and external, are defined as `given`'s that define members for binary type constructors and unary type constructors such that they belong to type constructor classes. 

In what follows, external specifications resp. external implementations are simply referred to as specifications resp. implementations.

### Programs

The specification part of the `PSBP` library models *programming*.

Think about it as explaining to `Scala` what the *essense of programming* is all about.

Members of specification `trait`'s are referred to as *programming ingredients*.

A `val` that is defined in terms of programming ingredients is referred to as a *program*. 

Think of a program of type `Z >--> Y` as specifying some generalization of a *function* of type `Z => Y`, *transforming* an *argument* of type `Z` to a *result* of type `Y` and, maybe, *performing side effects* along the way.

A program, just like a function, is a *denotational* artifact.

Formulated somewhat informally, the basic denotational programming ingredients are

- *functions can be used as programs*
- *programs can be composed sequentially*
- *programs can construct product based composite data*
- *programs can perform sum based conditional logic*

A program specifies *what* generalized functions *do* : somehow transforming an argument of type to a result.

Programs, typically, can be a given meaningful name.

The last three programming ingredients are *pointfree*, they only involve programs.

The first programming ingredient also involve functions.

Programming with functions, is also referred to as *functional programming*.

Functional programming is, essentially, also pointfree.

Agreed, *function application* is, almost by definition, a pointful functional programming ingredient.

Function application connects the denotational pointfree world of functions to the operational pointful world of evaluation of expressions.

*This pointful ingredient of the world of functions is, deliberately, not exposed to the pointfree world of programs*.

### Computations

The internal specification part of the `PSBP` library models *computing*, also .

Think about it as explaining to `Scala` what the *essense of computing* is all about.

Members of internal specification `trait`'s are referred to as *computing ingredients*.

A `val` that is defined in terms of computing ingredients is referred to as a *computation*. 

Think of a computation of type `C[Y]` as specifying some generalization of the *evaluation of an expression* of type `Y` *yielding* a *result* of type `Y` and, maybe, *performing side effects* along the way.

A computation, just like the evaluation of an expression, is an *operational* artifact.

Formulated somewhat informally, the basic operational computing ingredients are

- *a computation yields a result*
- *the result yielded by an inner computation can be the argument of a continuation function transforming it to a result that is an outer computation*

A computation specifies *how* generalized evaluation of expressions *is done*.

Computations, typically, cannot be given a meaningful name.

Computation ingredients are *pointful*, they do not only involve computations but also the results they yield.

### Main programs

A *main program* is a program of type `Unit >--> Unit`.

A program of type `Z >--> Y` is, somehow, combined with a *producer* of type `Unit >--> Z` and a *consumer* of type `(Z && Y) >--> Unit` to a main program.

Main programs of type `Unit >--> Unit` are *materialized* to functions of type `Z => Y`.

Materialized main program implementations are, eventually, used in *main* `Scala` *programs* where they, typically, somehow, behave as follows

- the producer produces an argument from an *input source*, possibly performing side effects along the way, 
- the program transforms the being produced argument to a to be consumed result, possibly performing side effects along the way,
- the consumer consumes the argument and result to an *output sink*, possibly performing side effects along the way.

Eventually, side effects are unavoidable. 

The intention is to push them to the *outside* of materialized main program implementations to allow for maximum flexibility.

Side effects can be *external* or *internal*.

External side effects interact with the external world.

Internal side effects typically manifest themselves in the function types of program implementations.

### Application development

The program level specification `trait`'s define an *application developer API*.

Application level development is in terms of *program examples* that are

1. defined using programming ingredients of specification `trait`'s
2. run as part of materialized main program implementations, used in a main `Scala` programs that use *dependency injection* by `import` of implementation `given`'s

### Library development

The computation level internal specification `trait`'s define a *library developer API*. 

Library level development is in terms of 

1. specification `trait`'s that declare programming ingredients
2. specification level implementation `given`'s that define programming ingredients
3. internal specification `trait`'s that declare computing ingredients
4. one important specification level implementation `given` that defines programming ingredients in terms of computation ingredients
5. internal specification level implementation `given`'s that define computing ingredients
5. internal implementation level implementation `given`'s that define computing ingredients
6. specific implementation `given`'s that define programming ingredients for specific types

# Application development

## Defining program examples

### `factorial`

```scala
package examples.specification.program

import psbp.specification.program.Program

import examples.specification.functional.{ isZero, one, subtractOne, multiply }

def factorial[>-->[- _, + _]: Program]: BigInt >--> BigInt =

  val program: Program[>-->] = summon[Program[>-->]]
  import program.{ Let, If }

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

import psbp.specification.types.&&

import psbp.specification.program.Functional

import examples.specification.function

def isZero[>-->[- _, + _]: Functional]: BigInt >--> Boolean =  
  function.isZero asProgram

def one[>-->[- _, + _]: Functional, Z]: Z >--> BigInt =
  function.one asProgram

def subtractOne[>-->[- _, + _]: Functional]: BigInt >--> BigInt =
  function.subtractOne asProgram

def multiply[>-->[- _, + _]: Functional]: (BigInt && BigInt) >--> BigInt =  
  function.multiply asProgram

// ...
```

are program utilities,

where

```scala
package examples.specification.function

import psbp.specification.types.&&

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

`factorial` is a program that makes use of programming ingredients 

- `asProgram` (from `Functional`)
- `>-->` (from `Composition`)
- `Let {} In {}` (local values, equivalent to `&&` from `Construction`)
- `If() {} Else {}` (if-then-else logic, equivalent to `||` from `Condition`)

See 

- [Functional](https://psbp-library.github.io#functional)
- [Composition](https://psbp-library.github.io#program)
- [Construction](https://psbp-library.github.io#program)
- [Condition](https://psbp-library.github.io#program) 

and 

- [Program](https://psbp-library.github.io#program) 

for more details.

### Complexity versus abstraction

`factorial` could also have been 

defined as

```scala
package examples

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
package examples

// ...

@main def mainFactorial(args: String*): Unit =
  println("Please type an integer")
  val i: BigInt = BigInt(scala.io.StdIn.readInt)
  println(s"applying factorial to the integer argument ${i} yields result ${factorial(i)}")
```

could have been run as

```scala
[info] running examples.mainFactorial 
Please type an integer
10
applying factorial to the integer argument 10 yields result 3628800
[success] 
```

The pointfree definition of `factorial` 

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

has, agreed, at the price of some auxiliary *functional* definitions and *library* level `Let {} In {}` syntax instead of *language* level `val z = e` syntax, left out some details of the pointful definition of `factorial`

```scala
  i =>
    if(i == 0) {
      1
    } else { 
      val j = factorial(i - 1)
      i * j
    }
```

which could also have been defined as 

```scala
package examples

import examples.specification.function.{ isZero, one, subtractOne, multiply }

val factorial: BigInt => BigInt =
  i =>
    if(isZero(i)) {
      one(i)
    } else { 
      val j = factorial(subtractOne(i))
      multiply(i, j)
    }
```

using the auxiliary function definitions used by the auxiliary functional ones.

On the one hand using abstraction is an *advantage* because it simplifies *complexity*.

Compexity is something that is *difficult to handle* by human beings.

On the other hand using abstraction is a *disadvantage*.

Astraction needs to be *understood* by human beings.

But here is the thing: many *different* definitions have *similar* complexity.

Handling the complexity of different definitions happens *over and over again*.

Understanding the abstraction of similar complexity of different definitions needs to be done *only once*.

### Explaining pointfree `factorial` in a pointful way

Let's explain the pointfree definition of `factorial`, thinking of programs as functions, in a pointful way using an argument `i`.

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

a local value `j`, which equals `(subtractOne >--> factorial)(i)`, which equals `factorial(subtractOne(i))`, which equals `factorial(i - 1)` is constructed, which is available as the second component of a product value `(i, j)`.

At level `3.2`, `factorial` looks like

```scala
      multiply
```

The result is `multiply(i, j)`, which equals `multiply(i, factorial(i - 1))`, which equals `i * factorial(i - 1)`.

### `fibonacci`

```scala
package examples.specification.program

import psbp.specification.program.Program

import examples.specification.functional.{ isZero, zero, isOne, one, subtractOne, subtractTwo, add }

def fibonacci[>-->[- _, + _]: Program]: BigInt >--> BigInt =

  val program: Program[>-->] = summon[Program[>-->]]
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
package examples.specification.program

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

are program utilities,

where

```scala
package examples.specification.functional

// ..

def isOne[>-->[- _, + _]: Functional]: BigInt >--> Boolean =  
  function.isOne asProgram  

def subtractTwo[>-->[- _, + _]: Functional]: BigInt >--> BigInt =
  function.subtractTwo asProgram  

def add[>-->[- _, + _]: Functional]: (BigInt && BigInt) >--> BigInt =  
  function.add asProgram 

// ...
```

are function utilities.

`fibonacci` is a program that makes use of programming ingredients 

- `asProgram` (being functional)
- `>-->` (sequential composition)
- `&&` and `&&&` (construction)
- `If() {} Else {}` (if-then-else logic, equivalent to `||` from `Condition`)

See 

- [Functional](https://psbp-library.github.io#functional)
- [Composition](https://psbp-library.github.io#program)
- [Construction](https://psbp-library.github.io#program)
- [Condition](https://psbp-library.github.io#program) 

and 

- [Program](https://psbp-library.github.io#program) 

for more details.

### Explaining pointfree `fibonacci` in a pointful way

Let's explain the pointfree definition of `fibonacci`, thinking of programs as functions, in a pointful way using an argument `i`.

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

Otherwise, in other words, if `i != 1` then, 

at level `3.2`, `fibonacci` looks like

```scala
      (subtractOne && subtractTwo) >-->
        (fibonacci &&& fibonacci) >-->
        add
```

A first product value `(subtractOne && subtractTwo)(i)`, which equals `(i - 1, i - 2)`, is constructed.

A second product value `(fibonacci &&& fibonacci)(i - 1, i - 2)` which equals `(fibonacci(i - 1), fibonacci(i - 2))` is constructed.

The result is `add(fibonacci(i - 1), fibonacci(i - 2))` which equals `fibonacci(i - 1) + fibonacci(i - 2)`.

### `mainFactorial`

```scala
package examples.specification.program.effectful

import psbp.specification.program.Program

import examples.specification.program.factorial

def mainFactorial[>-->[- _, + _]: Program]: Unit >--> Unit =
  factorial toMain (
    producer = intProducer,
    consumer = factorialConsumer
  )
```

where


```scala
package examples.specification.program.effectful

import scala.language.postfixOps

import psbp.specification.program.Program

def intProducer[>-->[- _, + _]: Program]: Unit >--> BigInt = 
  { (_: Unit) =>
      println("Please type an integer")
      BigInt(scala.io.StdIn.readInt)
  } asProgram
```

and where

```scala
package examples.specification.program.effectful

import scala.language.postfixOps

import psbp.specification.types.&&

import psbp.specification.program.Program 

def factorialConsumer[>-->[- _, + _]: Program]: (BigInt && BigInt) >--> Unit =
  {
    (`i&&j`: BigInt && BigInt) =>
      val i = `i&&j`._1
      val j = `i&&j`._2
      println(s"applying factorial to the integer argument $i yields result $j")
  } asProgram
```

`mainFactorial` is a main program that, for now, makes use of an *effectful* producer and an *effectful* consumer.

`toMain` is an extension of `Program`.

See [Program](https://psbp-library.github.io#program) for more details.

### `mainFibonacci`

```scala
package examples.specification.program.effectful

import psbp.specification.program.Program

import examples.specification.program.fibonacci

def mainFibonacci[>-->[- _, + _]: Program]: Unit >--> Unit =
  fibonacci toMain (
    producer = intProducer,
    consumer = fibonacciConsumer
  )
```

where

```scala
package examples.specification.program.effectful

// ...

def fibonacciConsumer[>-->[- _, + _]: Program]: (BigInt && BigInt) >--> Unit =
  {
    (`i&&j`: BigInt && BigInt) =>
      val i = `i&&j`._1
      val j = `i&&j`._2
      println(s"applying fibonacci to the integer argument $i yields result $j")
  } asProgram
```

`mainFibonacci` is a main program that, for now, makes use of an effectful producer and an effectful consumer.

### `optimize`

```scala
package examples.specification.program
  
import psbp.specification.types.&&

import psbp.specification.program.Program

import psbp.specification.functional.{ `(z&&y)>-->z`, `(z&&y)>-->y` }

// accumulator based optimization

def optimize[>-->[- _, + _]: Program, A, Z, Y](
  accumulatorInitializer: Z >--> A,
  argumentPredicate: Z >--> Boolean,
  updater: (Z && A) >--> (Z && A),
  resultExtractor: A >--> Y): Z >--> Y =

  val program: Program[>-->] = summon[Program[>-->]]
  import program.{ Let, If }

  val argument: (Z && A) >--> Z = `(z&&y)>-->z`
  val accumulator: (Z && A) >--> A = `(z&&y)>-->y`

  lazy val recursiveAccumulatorUpdater: (Z && A) >--> A =
    If(argument >--> argumentPredicate) {
      accumulator
    } Else {
      updater >--> recursiveAccumulatorUpdater
    }
    
  Let {
    accumulatorInitializer
  } In {
    recursiveAccumulatorUpdater >--> resultExtractor
  }
  
// ...  
```

Recursive programs like `factorial` (see [factorial](https://psbp-library.github.io#factorial)) and `fibonacci` (see [fibonacci](https://psbp-library.github.io#fibonacci)) can be optimized using an *accumulator* as `optimizedFactorial` (see [optimizedFactorial](https://psbp-library.github.io#optimizedfactorial)) and `optimizedFibonacci` (see [optimizedFibonacci](https://psbp-library.github.io#optimizedfibonacci)). Especially for `fibonacci` this is a relevant optimization.

### `optimizedFactorial`

```scala
package examples.specification.program

import psbp.specification.program.Program

import psbp.specification.functional.{ `(z&&y)>-->z` => argument, `z>-->z` => accumulator }

import examples.specification.functional.{ isZero, one, subtractOne, multiply }

import examples.specification.program.optimize

def optimizedFactorial[>-->[- _, + _]: Program]: BigInt >--> BigInt =
  optimize(
    accumulatorInitializer = one, 
    argumentPredicate = isZero, 
    updater = (argument >--> subtractOne) && multiply, 
    resultExtractor = accumulator
  )
```

See [optimize](https://psbp-library.github.io#optimize) for more details.

For `optimizedFactorial` the accumulator type is `BigInt`.

One `BigInt` optimizes one recursive occurrence.

This is not really an enormous optimization.

### `optimizedFibonacci`

```scala
package examples.specification.program

import psbp.specification.program.Program

import psbp.specification.functional.{ `(z&&y)>-->z` => firstAccumulator, `(z&&y)>-->y` => secondAccumulator }

import examples.specification.functional.{ isZero, zero, isOne, one, subtractOne, subtractTwo, add }

import examples.specification.program.optimize

def optimizedFibonacci[>-->[- _, + _]: Program]: BigInt >--> BigInt =
  optimize(
    accumulatorInitializer = one && one, 
    argumentPredicate = isZero, 
    updater = subtractOne &&& (secondAccumulator && add), 
    resultExtractor = firstAccumulator
  )
```

For `optimizedFibonacci` the accumulator type is `BigInt && BigInt`.

Two `BigInt`'s optimize two recursive occurrences. 

This is really an enormous optimization.

### `mainOptimizedFactorial`

```scala
package examples.specification.program.effectful

import psbp.specification.program.Program

import examples.specification.program.optimizedFactorial

def mainOptimizedFactorial[>-->[- _, + _]: Program]: Unit >--> Unit =
  optimizedFactorial toMain (
    producer = intProducer,
    consumer = factorialConsumer
  )
```

### `mainOptimizedFibonacci`

```scala
package examples.specification.program.effectful

import psbp.specification.program.Program

import examples.specification.program.optimizedFibonacci

def mainOptimizedFibonacci[>-->[- _, + _]: Program]: Unit >--> Unit =
  optimizedFibonacci toMain (
    producer = intProducer,
    consumer = fibonacciConsumer
  )
```

### `random`

```scala
package examples.specification.programWithState

import scala.language.postfixOps

import psbp.specification.programWithState.ProgramWithState

import examples.specification.program.negateIfNegative

type Seed = Long

def random[Z, >-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithState[Seed, >-->]]: Z >--> BigInt =

  val programWithSeedState: ProgramWithState[Seed, >-->] = summon[ProgramWithState[Seed, >-->]]
  import programWithSeedState.readStateModifiedWith
  
  object function {
    val seedModifier: Seed => Seed = 
      seed =>
        (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
  
    val seed2randomBigInt: Seed => BigInt = 
      seed =>
        BigInt((seed >>> 16).toInt)
        
    val moduloSomeLong: BigInt => BigInt =
      n =>
        n % 9876543210L     
  }
 
  val seedModifier: Seed >--> Seed =
    function.seedModifier asProgram

  val seed2randomBigInt =
    function.seed2randomBigInt asProgram

  val moduloSomeLong =
    function.moduloSomeLong asProgram

  val readModifiedSeed = readStateModifiedWith(seedModifier)   

  readModifiedSeed >--> seed2randomBigInt >--> negateIfNegative >--> moduloSomeLong 
```

where

```scala
package examples.specification.functional

// ...

def isNotNegative[>-->[- _, + _]: Functional]: BigInt >--> Boolean =  
  function.isNotNegative asProgram 

def negate[>-->[- _, + _]: Functional]: BigInt >--> BigInt =
  function.negate asProgram   
```
are program utilities,

where

```scala
package examples.specification.function

// ...

val isNotNegative: BigInt => Boolean =
  n =>
   n >= 0

val negate: BigInt => BigInt =
  n =>
    -n  
```

are function utilities

and where

```scala
package examples.specification.program
  
import psbp.specification.program.Program  

import psbp.specification.functional.identity

import examples.specification.functional.{ isNotNegative, negate }

def negateIfNegative[>-->[- _, + _]: Program]: BigInt >--> BigInt =

  val program: Program[>-->] = summon[Program[>-->]]
  import program.If

  If(isNotNegative) {
    identity
  } Else {
    negate
  }
```

is a program utility

The implementation details are not important.

While, somehow , transforming any argument to a random integer result, `random` internally modifies a seed computation state along the way.

`random` is a program that makes use of programming ingredients 

- `asProgram` (from `Functional`)
- `>-->` (from `Composition`)
- `If() {} Else {}` (if-then-else logic, equivalent to `||` from `Condition`)

and

- `readStateModifiedWith`

See

- [State](https://psbp-library.github.io#state)
- [ProgramWithState](https://psbp-library.github.io#programwithstate) 

for more details.

### `twoRandoms`

```scala
package examples.specification.programWithState

import psbp.specification.types.&&

import psbp.specification.programWithState.ProgramWithState

def twoRandoms[Z, >-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithState[Seed, >-->]]: Z >--> (BigInt && BigInt) =
  random && random
```

Illustrating statefulness can be done by using `twoRandoms`, somehow , transforming any argument to two random integer results.

When running materialized main `twoRandoms` implementations the two random integer results are *different*.

### `mainTwoRandoms`

```scala
package examples.specification.programWithState.effectful

import psbp.specification.types.&&

import psbp.specification.programWithState.ProgramWithState

import examples.specification.programWithState.{ Seed, twoRandoms }  

def mainTwoRandoms[>-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithState[Seed, >-->]]: Unit >--> Unit =
  twoRandoms toMainWith (
    producer = unitProducer,
    consumer = twoRandomsConsumer
  )
```

where

```scala
package examples.specification.programWithState.effectful

import scala.language.postfixOps

import psbp.specification.program.Program

def unitProducer[>-->[- _, + _]: Program]: Unit >--> Unit = 
  { (_: Unit) =>
      ()
  } asProgram 
```

and where

```scala
package examples.specification.programWithState.effectful

import scala.language.postfixOps

import psbp.specification.types.&&

import psbp.specification.program.Program

def twoRandomsConsumer[>-->[- _, + _]: Program]: (Unit && (BigInt && BigInt)) >--> Unit =
  {
    (`u&&(i&&j)`: Unit && (BigInt && BigInt)) =>
      val `i&&j` = `u&&(i&&j)`._2
      println(s"generating two random integers yields result ${`i&&j`}")
  } asProgram
```

`mainTwoRandoms` is a main program that, makes use of a trivial producer and, for now, makes use of an effectful consumer.

### `parallelFibonacci`

```scala
package examples.specification.programWithParallel

import psbp.specification.programWithParallel.ProgramWithParallel

import examples.specification.functional.{ isZero, zero, isOne, one, subtractOne, subtractTwo, add }

def parallelFibonacci[>-->[- _, + _]: ProgramWithParallel]: BigInt >--> BigInt =

  val programWithParallel: ProgramWithParallel[>-->] = summon[ProgramWithParallel[>-->]]
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

The definition of `parallelFibonacci` only differs from the definion of `fibonacci` by its usage of `|&&&|` instead of `&&&`.

See

- [Parallel](https://psbp-library.github.io#parallel)
- [ProgramWithParallel](https://psbp-library.github.io#programwithparallel) 

for more details.

### `mainParallelFibinacci`

```scala
package examples.specification.programWithParallel.effectful

import psbp.specification.types.&&

import psbp.specification.programWithParallel.ProgramWithParallel

import examples.specification.programWithParallel.parallelFibonacci 

import examples.specification.program.effectful.intProducer

def mainParallelFibonacci[>-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithParallel[>-->]]: Unit >--> Unit =
  parallelFibonacci toMainWith (
    producer = intProducer,
    consumer = parallelFibonacciConsumer
  )
```

where

```scala
package examples.specification.programWithParallel.effectful

import scala.language.postfixOps

import psbp.specification.types.&&

import psbp.specification.program.Program 

import akka.actor.typed.{ ActorSystem, Behavior }

import akka.actor.typed.scaladsl.{ ActorContext, Behaviors }
  
import Behaviors.{ receive, stopped }
  
import ch.qos.logback.classic.{Level, Logger, LoggerContext}
import Level.{ INFO, ERROR}
  
import org.slf4j.LoggerFactory

val packageName = "examples.specification.programWithParallel.effectful"
  
val loggerContext:  LoggerContext = LoggerFactory.getILoggerFactory().asInstanceOf[LoggerContext]
val logger: Logger = loggerContext.getLogger(packageName)
  
def log[Z](actorContext: ActorContext[Z])(message: String): Unit = {
  logger.setLevel(INFO);
  actorContext.log.info(message)
  logger.setLevel(ERROR);  
}
  
def parallelFibonacciConsumer[>-->[- _, + _]: Program]: (BigInt && BigInt) >--> Unit =
    
  object Consumer {

    case class Consume(i: BigInt, j: BigInt)

    def apply(): Behavior[Consume] = receive { (context, message) =>
      message match {
        case Consume(i, j) =>
          val logging = log(context)
          logging(s"applying parallel fibonacci to argument $i yields result $j")
          stopped
      }
    }

  }

  val consumer = ActorSystem(Consumer(), "consumer")  
   
  import Consumer.Consume

  {
    (`i&&j`: BigInt && BigInt) =>
      val i = `i&&j`._1
      val j = `i&&j`._2
      consumer ! Consume(i, j)
  } asProgram 

// ...  
```

`mainParallelFibonacci` is a main program that, for now, makes use of the same effectful producer as `fibonacci` and that makes use of an *akka actor* and *logback logging* based effectful consumer.

Actor `consumer` is sent a message `Consume(i, j)` which causes `logging` to do its work.

## Running program examples

### Running `active` `factorial` (effectful producer and consumer)

```scala
package examples.implementation.active.program.effectful

import psbp.implementation.active.given

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

`@main def factorial` uses injection by `import` of `active` `given` implementations.

See [activeProgram](https://psbp-library.github.io#activeprogram) and [activeMaterialization](https://psbp-library.github.io#activematerialization) for more details.

### Running `active` `fibonacci` (effectful producer and consumer)

```scala
package examples.implementation.active.program.effectful

import psbp.implementation.active.given

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

import psbp.implementation.active.given

import examples.specification.program.effectful.mainOptimizedFactorial

@main def optimizedFactorial(args: String*): Unit =
  mainOptimizedFactorial materialized ()
```

See [mainOptimizedFactorial](https://psbp-library.github.io#mainoptimizedfactorial) for more details.

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

import psbp.implementation.active.given

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

Running `active` `fibonacci` can also be done with `1000` instead of `10`.

Using `10000` produces a stack overflow.

### Running `reactive` `factorial` (effectful producer and consumer)

```scala
package examples.implementation.reactive.program.effectful

import psbp.implementation.reactive.given

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

The only difference with the active version is the usage of a different injection by `import`.

`@main def factorial` uses injection by `import` of `reactive` `given` implementations.

See [reactiveProgram](https://psbp-library.github.io#reactiveprogram) and [reactiveMaterialization](https://psbp-library.github.io#reactivematerialization) for more details.

### Running `reactive` `fibonacci` (effectful producer and consumer)

```scala
package examples.implementation.reactive.program.effectful

import psbp.implementation.reactive.given

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

### Running `reactive` `optimizedFactorial` (effectful producer and consumer)

```scala
package examples.implementation.reactive.program.effectful

import psbp.implementation.reactive.given

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

import psbp.implementation.reactive.given

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

Using `1000` produces a stack overflow.

Time for *tail recursive optimization* (for `active` `optimizedFibonacci` and certainly for `reactive` `optimizedFibonacci`)!

### Running tail recursive `freeActive` `factorial` (effectful producer and consumer)

```scala
package examples.implementation.freeActive.program.effectful

import psbp.implementation.freeActive.given

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

Again, the only difference with the active and reactive versions is the usage of a different injection by `import`.

`@main def factorial` uses injection by `import` of `freeActive` `given` implementations.

See [freeActiveProgram](https://psbp-library.github.io#freeactiveprogram) and [freeActiveMaterialization](https://psbp-library.github.io#freeactivematerialization) for more details.

### Running tail `freeActive` `optimizedFibonacci` (effectful producer and consumer)

```scala
package examples.implementation.freeActive.program.effectful

import psbp.implementation.freeActive.given

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

Using `10000` does not produce a stack overflow.

### Running `stateActive` `twoRandoms` (effectful consumer)

```scala
package examples.implementation.active.programWithState.effectful

import psbp.specification.program.state.Initial

import psbp.specification.programWithState.given

import psbp.implementation.stateActive.given

import examples.specification.programWithState.Seed

import examples.specification.programWithState.effectful.mainTwoRandoms

given initialSeedState: Initial[Seed] = 
  new { 
    override val s = 1L 
  }

@main def twoRandoms(args: String*): Unit =
  mainTwoRandoms materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.active.programWithState.effectful.twoRandoms 
generating two random integers yields result (384748,1151252339)
[success] ...
```

Again, the main difference with the active, reactive and free versions is the usage of a different injection by `import`.

`@main def twoRandoms` uses injection by `import` of `stateActive` `given` implementations.

See [stateActiveProgram](https://psbp-library.github.io#stateactiveprogram), [stateActiveState](https://psbp-library.github.io#stateactivestate) and [stateActiveMaterialization](https://psbp-library.github.io#stateactivematerialization) for more details.

`@main def twoRandoms` also a `given` implementation of `Initial` for `Seed`.

When running the materialized main program implementation, the implementation of program `twoRandoms`, somehow, transforms its argument, in this case no argument, to a result, in this case a product result.

This product result consists of two *different* random integers, `384748` and `1151252339`. 

They are different because, while transforming, internal side effects are happening along the way.

More precisely, the `Seed` computation state modifies.

The important takeway is that programming with state can be achieved *without using any* `var`*'s*.

Instead, state manifests itself, internally, in the function type `Z => (S => [(S, Y)])` of program implementations.

### Running `reactive` `parallelFibonacci` (effectful producer and consumer)

```scala
package examples.implementation.reactive.programWithParallel.effectful

import psbp.specification.programWithParallel.given

import psbp.implementation.reactive.given

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
[2021-03-03 11:36:00,472] - leftActor received LeftAct 
[2021-03-03 11:36:00,497] - rightActor received RightAct 
[2021-03-03 11:36:00,530] - leftActor received LeftAct 
[2021-03-03 11:36:00,571] - leftActor received LeftAct 
[2021-03-03 11:36:00,576] - rightActor received RightAct 
[2021-03-03 11:36:00,651] - rightActor received RightAct 
[2021-03-03 11:36:00,699] - leftActor received LeftAct 
[2021-03-03 11:36:00,735] - leftActor received LeftAct 
[2021-03-03 11:36:00,795] - rightActor received RightAct 
[2021-03-03 11:36:00,796] - rightActor received RightAct 
[2021-03-03 11:36:00,797] - leftActor received LeftAct 
[2021-03-03 11:36:00,797] - reactor received both RightReact(0) and LeftReact(1) 
[2021-03-03 11:36:00,911] - rightActor received RightAct 
[2021-03-03 11:36:00,911] - reactor received both RightReact(0) and LeftReact(1) 
[2021-03-03 11:36:00,912] - reactor received both LeftReact(1) and RightReact(1) 
[2021-03-03 11:36:00,933] - leftActor received LeftAct 
[2021-03-03 11:36:00,998] - rightActor received RightAct 
[2021-03-03 11:36:00,999] - reactor received both RightReact(0) and LeftReact(1) 
[2021-03-03 11:36:01,002] - reactor received both LeftReact(1) and RightReact(1) 
[2021-03-03 11:36:01,002] - reactor received both LeftReact(2) and RightReact(1) 
[2021-03-03 11:36:01,002] - reactor received both LeftReact(3) and RightReact(2) 
[2021-03-03 11:36:01,018] - applying parallel fibonacci to argument 5 yields result 5 
[success] ...
```

Again, the main difference with the active free and state versions is the usage of a different injection by `import`.

`@main def parallelFibonacci` uses injection by `import` of `reactive` `given` implementations.

See [reactiveProgram](https://psbp-library.github.io#reactiveprogram), [reactiveParallel](https://psbp-library.github.io#reactiveParallel) and [reactiveMaterialization](https://psbp-library.github.io#reactivematerialization) for more details.

# Library development

## Specification `trait`'s

### `Functional`

```scala
package psbp.specification.program

trait Functional[>-->[- _, + _]]:

  // declared

  private[psbp] def toProgram[Z, Y]: (Z => Y) => Z >--> Y

  // defined extensions

  extension [Z, Y] (`z=>y`: Z => Y) def asProgram: Z >--> Y =
    toProgram(`z=>y`)
```

`Functional` specifies that *functions can be used as programs*.

The member `asProgram` is an `extension` that can be used as postfix operator.

The full power that comes with the `PSBP` library is not available for functions that are used as programs using `asProgram`, so, which functions to use as programs using `asProgram` is an important choice.

Names like `` `z=>y` `` can be thought of as *typeful generic names* for functions.

### `Composition`

```scala
package psbp.specification.program

trait Composition[>-->[- _, + _]]:

  // declared

  private[psbp] def andThen[Z, Y, X](`z>-->y`: Z >--> Y, `y>-->x`: => Y >--> X): Z >--> X

  // defined extensions
  
  extension [Z, Y, X] (`z>-->y`: Z >--> Y) def >-->(`y>-->x`: => Y >--> X): Z >--> X =
    andThen(`z>-->y`, `y>-->x`)
```

`Composition` specifies that *programs can be composed sequentially*.

Sequential composition is simply referred to as composition.

The member `>-->` is an `extension` that can be used as infix operator.

The type of the second parameter of `andThen` is a *call-by-name* one.

Names like `` `z>-->y` `` can be thought of as typeful generic names for programs.

### `Construction`

```scala
package psbp.specification.program

import psbp.specification.types.&&

trait Construction[>-->[- _, + _]]:

  // declared

  private[psbp] def construct[Z, Y, X] (`z>-->y`: Z >--> Y, `z>-->x`: => Z >--> X): Z >--> (Y && X) 

  // defined extensions

  extension [Z, Y, X] (`z>-->y`: Z >--> Y) def &&(`z>-->x`: => Z >--> X): Z >--> (Y && X) =
    construct(`z>-->y`, `z>-->x`)
```

where

```scala
package psbp.specification.types

// product

type &&[+Z, +Y] = (Z, Y)

// ...
```

`Construction` specifies that *programs can construct product based composite data*.

The member `&&` is an `extension` that can be used as infix operator.

The type of the second argument of `construct` is a call-by-name one.

`&&` is, somewhat artificially, sequentially biased towards it's first argument.

### `Condition`

```scala
package psbp.specification.program

import psbp.specification.types.||

trait Condition[>-->[- _, + _]]:

  // declared

  private[psbp] def conditionally[Z, Y, X] (`y>-->z`: => Y >--> Z, `x>-->z`: => X >--> Z): (Y || X) >--> Z

  // defined extensions
  
  extension [Z, Y, X] (`y>-->z`: => Y >--> Z) def ||(`x>-->z`: => X >--> Z): (Y || X) >--> Z =
    conditionally(`y>-->z`, `x>-->z`)
```

where

```scala
package psbp.specification.types

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

`foldSum` hides the `enum` representation of the sum type.

`Condition` specifies that *programs can deconstruct sum based composite data to perform conditional logic*.

The member `||` is an extension that can be used as infix operator.

The types of the arguments of `conditionally` are call-by-name ones.

### `Program`

```scala
package psbp.specification.program

import psbp.specification.types.{ &&, || }

import psbp.specification.functional.{ 
  `z>-->z`, `(z&&y)>-->z`, `(z&&y)>-->y`, `z>-->(z||y)`, `y>-->(z||y)`, `(z&&b)>-->(z||z)` }

trait Program[>-->[- _, + _]]
  extends Functional[>-->]
  with Composition[>-->]
  with Construction[>-->]
  with Condition[>-->]:

    private implicit val program: Program[>-->] = this

    // defined extensions

    extension [Z, Y, X, W] (`z>-->x`: Z >--> X) def &&&(`y>-->w`: => Y >--> W): (Z && Y) >--> (X && W) =
      (`(z&&y)>-->z` >--> `z>-->x`) && (`(z&&y)>-->y` >--> `y>-->w`)  

    extension [Z, Y, X, W] (`x>-->z`: => X >--> Z) def |||(`w>-->y`: => W >--> Y): (X || W) >--> (Z || Y) =
      (`x>-->z` >--> `z>-->(z||y)`) || (`w>-->y` >--> `y>-->(z||y)`)  

    extension [Z, Y] (program: Z >--> Y) def toMain(producer: Unit >--> Z, consumer: (Z && Y) >--> Unit) =
      producer >--> {
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
package psbp.specification.functional

import scala.language.postfixOps

import psbp.specification.types.{ &&, || }

import psbp.specification.program.Functional

import psbp.specification.function._

// functional

def `z>-->z`[>-->[- _, + _]: Functional, Z]: Z >--> Z =
  `z=>z` asProgram

def identity[>-->[- _, + _]: Functional, Z]: Z >--> Z =
  `z=>z` asProgram  

// construction

def `(z&&y)>-->z`[>-->[- _, + _]: Functional, Z, Y]: (Z && Y) >--> Z =
  `(z&&y)=>z` asProgram
    
def `(z&&y)>-->y`[>-->[- _, + _]: Functional, Z, Y]: (Z && Y) >--> Y =
  `(z&&y)=>y` asProgram

// condition

def `z>-->(z||y)`[>-->[- _, + _]: Functional, Z, Y]: Z >--> (Z || Y) =
  `z=>(z||y)` asProgram
  
def `y>-->(z||y)`[>-->[- _, + _]: Functional, Z, Y]: Y >--> (Z || Y) =
  `y=>(z||y)` asProgram 

// construction and condition
  
def `(z&&b)>-->(z||z)`[>-->[- _, + _]: Functional, Z]: (Z && Boolean) >--> (Z || Z) =
  `(z&&b)=>(z||z)` asProgram 

// ...  
```

are program utilities,

where

```scala
package psbp.specification.function

// functional

def `z=>z`[Z]: Z => Z = 
  z =>
    z

// construction

def `(z&&y)=>z`[Z, Y]: (Z && Y) => Z =
  (z, _) => 
    z

def `(z&&y)=>y`[Z, Y]: (Z && Y) => Y =
  (_, y) => 
    y  

// condition

import ||.{ Left, Right }

def `z=>(z||y)`[Z, Y]: Z => (Z || Y) =
  z =>
    Left(z)

def `y=>(z||y)`[Z, Y]: Y => (Z || Y) =
  y =>
    Right(y)   

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

The members `&&&` resp. `|||` are `extension`'s that are more complex versions of `&&` resp. `||`.

The `Let {} In {}` programming ingredient specifies that *programs can define local values*.

The `If() {} Else {}` programming ingredient specifies that programs *can perform if-then-else based logic*.

`Let {} In {}` and `If() {} Else {}` are perfect examples of the **sca**lable **la**nguage capability of `Scala`.

The member `toMain` is an `extension` that combines a program with a producer and a consumer to a main program.

Let's explain the pointfree definition of `toMain`, thinking of programs as functions, in a pointful way using an argument `()`.

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

A local value `z`, which equals `producer()` is constructed, a produced result of type `Z` which is a to be transformed argument of `program`.

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

a local value `y`, which equals `program(z)` is constructed, a transformed value of type `Y` which is a to be consumed argument of `consumer`, available as the second component of a product value `(z, y)`.

At level `2.2`, `toMain` looks like

```scala
          consumer 
```

The result is `consumer(z, y)`.

So `consumer` can consume both `z`, which equals `producer()` the local value produced by `producer` and `y`, which equals `program(z)` the local value transformed by `program.`

### About power of expression of `&&&` and `Let {} In {}`

```scala
package examples.specification.program

import psbp.specification.types.&&

import psbp.specification.program.Program

import psbp.specification.functional.{ `z>-->(z&&z)`, `(z&&y)>-->z` , `(z&&y&&x)>-->(y&&x)` }

def `construct using &&&`[>-->[- _, + _]: Program, Z, Y, X] 
  (`z>-->y`: Z >--> Y, `z>-->x`: => Z >--> X): Z >--> (Y && X) =
  `z>-->(z&&z)` >--> (`z>-->y` &&& `z>-->x`)

def constructUsingLet[>-->[- _, + _]: Program, Z, Y, X] 
  (`z>-->y`: Z >--> Y, `z>-->x`: => Z >--> X): Z >--> (Y && X) =
 
  val program: Program[>-->] = summon[Program[>-->]]
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
package psbp.specification.program

// ...

// construction

// ...

def `z>-->(z&&z)`[>-->[- _, + _]: Functional, Z]: Z >--> (Z && Z) =
  `z=>(z&&z)` asProgram  

def `(z&&y&&x)>-->(y&&x)`[>-->[- _, + _]: Functional, Z, Y, X]: (Z && Y && X) >--> (Y && X) =
  `(z&&y&&x)=>(y&&x)` asProgram
```

are program utilities,

where

```scala
package psbp.specification.program

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

import psbp.specification.types.||

import psbp.specification.program.Program

import psbp.specification.functional.{ `(z||z)>-->z`, `(y||x)>-->b`, `(y||x)>-->y`, `(y||x)>-->x` }

def `conditionally using |||`[>-->[- _, + _]: Program, Z, Y, X]
  (`y>-->z`: => Y >--> Z, `x>-->z`: => X >--> Z): (Y || X) >--> Z =
  (`y>-->z` ||| `x>-->z`) >--> `(z||z)>-->z`

def conditionallyUsingIf[>-->[- _, + _]: Program, Z, Y, X]
  (`y>-->z`: => Y >--> Z, `x>-->z`: => X >--> Z): (Y || X) >--> Z =

  val program: Program[>-->] = summon[Program[>-->]]
  import program.If

  If(`(y||x)>-->b`) {
    `(y||x)>-->y` >--> `y>-->z`
  } Else {
    `(y||x)>-->x` >--> `x>-->z`
  }
```
where

```scala
package psbp.specification.program

// ...

// condition

def `(z||z)>-->z`[>-->[- _, + _]: Functional, Z]: (Z || Z) >--> Z =
  `(z||z)=>z` asProgram  
  
def `(y||x)>-->b`[>-->[- _, + _]: Functional, Y, X]: (Y || X) >--> Boolean =
  `(y||x)=>b` asProgram

def `(y||x)>-->y`[>-->[- _, + _]: Functional, Y, X]: (Y || X) >--> Y =
  `(y||x)=>y` asProgram

def `(y||x)>-->x`[>-->[- _, + _]: Functional, Y, X]: (Y || X) >--> X =
  `(y||x)=>x` asProgram

// ...
```

are program utilities,

where

```scala
package psbp.specification.function

// ...

// condition

// ...

def `(z||z)=>z`[Z]: (Z || Z) => Z =
  _.foldSum(z => z, z => z)  
  
def `(y||x)=>b`[Y, X]: (Y || X) => Boolean =
  _.foldSum(_ => true, _ => false)

def `(y||x)=>y`[Y, X]: (Y || X) => Y =
  _.foldSum(y => y, _ => ???) 

def `(y||x)=>x`[Y, X]: (Y || X) => X =
  _.foldSum(_ => ???, x => x)
```

are function utilities.

`|||` and `If() {} Else {}` have the same power of expression as `conditionally` and `||`.

### `Materialization`

```scala
package psbp.specification.materialization

trait Materialization[>-->[- _, + _], -Z, +Y]:

  // declared

  private[psbp] val materialize: (Unit >--> Unit) => Z => Y

  // defined extensions

  extension (`u>-->u`: Unit >--> Unit) def materialized: Z => Y =
    materialize(`u>-->u`)
```

Main programs are materialized to functions.

The function type `Z => Y` is generic to allow for various materializations.

The member `materialized` is an `extension` that can be used as postfix operator.

### `State`

```scala
package psbp.specification.program.state

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

### `ProgramWithState`

```scala
package psbp.specification.programWithState

import psbp.specification.program.Program

import psbp.specification.program.state.State

import psbp.specification.functional.`z>-->u`

trait ProgramWithState[S, >-->[- _, + _]] extends Program[>-->] with State[S, >-->]:

  private implicit val program: Program[>-->] = this

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
package psbp.specification.program

// ...
// functional

//...

def `z>-->u`[>-->[- _, + _]: Functional, Z]: Z >--> Unit =
  `z=>u` asProgram 

// ...  
```
is a program utility

where

```scala
package psbp.specification.function

// functional

// ...

def `z=>u`[Z]: Z => Unit = 
  z =>
    ()   

// ...    
```

is a function utility.

`readState`, `writeState`, `modifyStateWith` and `readStateModifiedWith` are useful state handling programs.

For example, `readStateModifiedWith` is used in [random](https://psbp-library.github.io#random)

### `Parallel`

```scala
package psbp.specification.program.parallel

import psbp.specification.types.&&

trait Parallel[>-->[- _, + _]]:
  
  // declared

  private[psbp] def parallel[Z, Y, X, W] (`z>-->x`: Z >--> X, `y>-->w`: Y >--> W): (Z && Y) >--> (X && W)

  private[psbp] def asynchronous[Z, Y](`z>-->y`: Z >--> Y): Z >--> Y 
  
  // defined extensions

  extension [Z, Y, X, W] (`z>-->x`: Z >--> X) def |&&&|(`y>-->w`: Y >--> W): (Z && Y) >--> (X && W) =
    parallel(`z>-->x`, `y>-->w`)

  extension [Z, Y] (`z>-->y`: Z >--> Y) def async: Z >--> Y  =
    asynchronous(`z>-->y`)
```

`Parallel` specifies that programs can *run in parallel* and can *run asynchronously*.

The member `|&&&|` is an extension that can be used as infix operator.

For example, `|&&&|` is used in [parallelFibonacci](https://psbp-library.github.io#parallelfibonacci)

The member `async` is an extension that can be used as postfix operator.

### `ProgramWithParallel`

```scala
package psbp.specification.programWithParallel

import psbp.specification.types.&&

import psbp.specification.program.Program

import psbp.specification.program.parallel.Parallel

import psbp.specification.functional.{ `u>-->u`, `z>-->(z&&u)`, `(y&&u)>-->y`, `z>-->(z&&z)` }

trait ProgramWithParallel[>-->[- _, + _]] extends Program[>-->] with Parallel[>-->]:

  private implicit val program: Program[>-->] = this

  // defined
  
  override private[psbp] def asynchronous[Z, Y](`z>-->y`: Z >--> Y): Z >--> Y =
    `z>-->(z&&u)` >--> (`z>-->y` |&&&| `u>-->u`[>-->]) >--> `(y&&u)>-->y`

  // defined extensions

  extension [Z, Y, X] (`z>-->y`: Z >--> Y) def |&&|(`z>-->x`: Z >--> X): Z >--> (Y && X) =
    `z>-->(z&&z)` >--> (`z>-->y` |&&&| `z>-->x`)
```

where

```scala
package psbp.specification.program

import scala.language.postfixOps

// functional

// ...  

def `u>-->u`[>-->[- _, + _]: Functional]: Unit >--> Unit =
  `u=>u` asProgram

// construction

// ...
    
def `z>-->(z&&u)`[>-->[- _, + _]: Functional, Z]: Z >--> (Z && Unit) =
  `z=>(z&&u)` asProgram

def `(y&&u)>-->y`[>-->[- _, + _]: Functional, Y]: (Y && Unit) >--> Y =
  `(y&&u)=>y` asProgram 

// ...
```

are program utilities

where

```scala
package psbp.specification.function

// functional

// ...
    
def `u=>u`: Unit => Unit = 
  `z=>z`[Unit]     

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

The member `|&&|` is an extension that can be used as infix operator.

## Specification level implementation `given`'s

### `programWithState`

```scala
package psbp.specification.programWithState

import psbp.specification.program.Program

import psbp.specification.program.state.State

given programWithState[
  S, 
  >-->[- _, + _]: Program: [>-->[- _, + _]] =>> State[S, >-->]]: ProgramWithState[S, >-->] with
 
  private val program: Program[>-->] = summon[Program[>-->]]

  private val state: State[S, >-->] = summon[State[S, >-->]]

  export program.toProgram
  export program.andThen
  export program.construct
  export program.conditionally

  export state.`u>-->s`
  export state.`s>-->u`
```

Using injection by `import` of `programWithState`, a specification level `given` implementation of `ProgramWithState`, only `given` implementations of  `Program` and `State` need to be injected by `import`.

### `programWithParallel`

```scala
package psbp.specification.programWithParallel

import psbp.specification.program.Program

import psbp.specification.program.parallel.Parallel

given programWithParallel[>-->[- _, + _]: Program: Parallel]: ProgramWithParallel[>-->] with
 
  private val program: Program[>-->] = summon[Program[>-->]]

  private val parallel: Parallel[>-->] = summon[Parallel[>-->]]

  export program.toProgram
  export program.andThen
  export program.construct
  export program.conditionally

  export parallel.parallel
```

Using injection by `import` of `programWithParallel`, a specification level `given` implementation of `ProgramWithParallel`, only `given` implementations of `Program` and `Parallel` need to be injected by `import`.

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

  private[psbp] def bind[Z, Y] (cz: C[Z], `z=>cy`: => Z => C[Y]): C[Y]

  // defined

  private[psbp] def join[Z] (ccz: C[C[Z]]): C[Z] =
    bind(ccz, identity)

  // defined extensions

  extension [Z, Y] (cz: C[Z]) def >=(`z=>cy`: => Z => C[Y]): C[Y] =
    bind(cz, `z=>cy`)

  extension [Z, Y] (ccz: C[C[Z]]) def joined =
    join(ccz) 
```

The `bind` member of `Binding` specifies that *the result yielded by an inner computation can be the argument of a continuation function transforming it to a result that is an outer computation*.

The `join` member somehow specifies *computation nesting*.

Compare this with *expression nesting*.

The member `>=` is an`extension` that can be used as infix operator.

The member `joined` is an`extension` that can be used as postfix operator.

### `Computation`

```scala
package psbp.internal.specification.computation

private[psbp] trait Computation[C[+ _]] 
  extends Resulting[C] 
  with Binding[C]
```

`Computation` is simply a combination of `Resulting` and `Binding`.

### Natural transformation `~>`

```scala
package psbp.internal.specification.naturalTransformation

private[psbp] trait ~>[F[+ _], T[+ _]]:

  // declared

  private[psbp] def apply[Z]: F[Z] => T[Z] 
```

A *natural transformation* of type `F ~> T` is similar to a generic function of type `F[Z] => T[Z]`, but, instead of *transforming at type level* it *transforms at unary type constructor level*.

### `Transformation`

```scala
package psbp.internal.specification.computation.transformation

import psbp.internal.specification.naturalTransformation.~>
  
private[psbp] trait Transformation[F[+ _], T[+ _]]:

  // declared

  private[psbp] val `f~>t`: F ~> T
```

A *transformation* has a natural transformation member `` `f~>t` `` it can delegate the transformation work to.

### `CoResulting`

```scala
package psbp.internal.specification.computation

private[psbp] trait CoResulting[C[+ _]]:

  // declared

  private[psbp] def coResult[Z]: C[Z] => Z
```

`CoResulting` specifies the dual of `Resulting`.

It turns out to be useful for reactive materialization.

See [reactiveTransformedMaterialization](https://psbp-library.github.io#reactivetransformedmaterialization) for more details.

## One important specification level implementaton `given`

### `programFromComputation`

```scala
package psbp.internal.specification.computation

import psbp.specification.types.{ &&, || }

import psbp.specification.program.Program

import psbp.internal.specification.computation.Computation

private[psbp] given programFromComputation[C[+ _]: Computation]: Program[[Z, Y] =>> Z => C[Y]] with
  
  private val computation: Computation[C] = summon[Computation[C]]
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
    _.foldSum(`y>-->z`, `x>-->z`) 
```

`programFromComputation` is a `given` that defines the basic programming ingredients of a program of type `Z => C[Y]` in terms of the basic computing ingredients of a computation of type `C[Y]`.

Compare this with defining functions in terms of expressions.

Using injection by `import` of `programFromComputation`, a specification level `given` implementation of `Program`, only `given` implementations of `Computation` need to be injected by `import`.

### Setting the scene

*Program implementation types are limited to types* `[Z, Y] =>> Z => C[Y]` *defined in terms of computation types* `[Y] =>> C[Y]`.

Program implementations are limited to functions of type `Z => C[Y]` transforming an argument of type `Z` to a result that is a computation of type `C[Y]`.

Formulated somewhat informally, programs, generalized functions, maybe performing side effects along the way of transforming their argument to a result, are limited to functions transforming an argument to a result that is a computation, a generalized evaluation of an expression, maybe performing side effects along the way.

*Although such programs can be used in a pointful way, the application developer API does not allow doing so, forcing application developers to think in a pointfree way.*

## Internal specification level implementation `given`'s

### `computationFromResultingAndBinding`

```scala
package psbp.internal.specification.computation

private[psbp] given computationFromResultingAndBinding[
  C[+ _]: Resulting: Binding]: Computation[C] with

  private val resulting: Resulting[C] = summon[Resulting[C]]

  private val binding: Binding[C] = summon[Binding[C]]

  export resulting.result
  export binding.bind
```

Using injection by `import` of `computationFromResultingAndBinding`, a specification level `given` implementation of `Computation`, only `given` implementations of `Resulting` and `Binding` need to be injected by `import`.

### `resultingFromTransformation`

```scala
package psbp.internal.specification.computation.transformation

import psbp.internal.specification.computation.Resulting

import psbp.internal.specification.naturalTransformation.~>
  
private[psbp] given resultingFromTransformation[
  F[+ _]: Resulting, T[+ _]: [T[+ _]] =>> Transformation[F, T]]: Resulting[T] with   

  private val transformation: Transformation[F, T] = summon[Transformation[F, T]]
  import transformation.`f~>t`

  // defined
  
  override private[psbp] def result[Z]: Z => T[Z] = 

    import `f~>t`.{ apply => `fz=>tz` }

    val resultingF: Resulting[F] = summon[Resulting[F]]
    import resultingF.{ result => `z=>fz` }
    
    `z=>fz` andThen `fz=>tz`
```

Using a natural transformation to define `result` of the `T[+ _]` computation in terms of `result` of the `F[+ _]` computation.

Using injection by `import` of `resultingFromTransformation`, a specification level `given` implementation of `Resulting`, only `given` implementations of `Binding` need to be injected by `import`.

## Internal implementation level implementation `given`'s

### `ReactiveTransformed`

```scala
package psbp.internal.implementation.computation.transformation

// ReactiveTransformed

private[psbp] type ReactiveTransformed[C[+ _]] = [Z] =>> (C[Z] => Unit) => Unit

// ...
```

A reactive transformed computation is a *computation callback handler* of type `(C[Z] => Unit) => Unit` handling a *computation callback*.

A computation callback is a callback of type `C[Z] => Unit` expecting a computation of type `C[Z]`.

### `reactiveTransformedBinding`

```scala
package psbp.internal.implementation.computation.transformation

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.computation.Computation

import psbp.internal.specification.naturalTransformation.~>

import psbp.internal.specification.computation.transformation.Transformation

private[psbp] given reactiveTransformedBinding[
  C[+ _]: Computation]: Transformation[C, ReactiveTransformed[C]] 
  with Binding[ReactiveTransformed[C]] with 

  private type F[+Z] = C[Z]
  private type T[+Z] = ReactiveTransformed[F][Z]
  
  private val computationF: Computation[F] = summon[Computation[F]]
  import computationF.{ result => resultF, bind => bindF }
    
  override private[psbp] val `f~>t`: F ~> T = new {
    def apply[Z]: F[Z] => T[Z] =
      fz => 
        _ apply fz
  }
  
  override private[psbp] def bind[Z, Y] (tz: T[Z], `z=>ty` : => Z => T[Y]): T[Y] =
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

Often an almost trivial choice for a functional component, resp. programming component does the trick to get the types right.

For `` `f~>t` `` the trivial choice for a functional component is the `Function` member `apply`. 

### `reactiveTransformedMaterialization`

```scala
package psbp.internal.implementation.computation.transformation

import psbp.internal.specification.computation.Computation

import psbp.specification.materialization.Materialization

import psbp.internal.specification.computation.CoResulting

import psbp.internal.implementation.computation.transformation.ReactiveTransformed

private[psbp] given reactiveTransformedMaterialization[
  C[+ _]: Computation: CoResulting:
  [C[+ _]] =>> Materialization[[Z, Y] =>> Z => C[Y], Z, Y], Z, Y]: 
  Materialization[[Z, Y] =>> Z => ReactiveTransformed[C][Y], Unit, Unit] with

  private type F[+Z] = C[Z]
  private type T[+Z] = ReactiveTransformed[F][Z] 

  private type `=>F`[-Z, +Y] = Z => F[Y]
  private type `=>T`[-Z, +Y] = Z => T[Y]

  private val coResultingF: CoResulting[F] = summon[CoResulting[F]]
  import coResultingF.coResult

  val materialize: (Unit `=>T` Unit) => Unit => Unit =
    `u>-->u` =>
      u =>
        `u>-->u`(u)(coResult)
```

Transforming materialization of `[Z, Y] =>> Z => C[Y], Z, Y`, to materialization, of `[Z, Y] =>> Z => (C[Y] => Unit) => Unit, Unit, Unit`, is done using `reactiveTransformedMaterialization`. 

Again, there is really only one meaningful way to define the `materialize` member in a way to get the types right.

For `materialize` the trivial choice for a programming component is the co-resulting member `coResult`. 

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

import psbp.specification.program.Program

import psbp.internal.specification.computation.Computation

import psbp.internal.specification.naturalTransformation.~>

import psbp.internal.specification.computation.transformation.Transformation

import Free._

private[psbp] given freeTransformedComputation[C[+ _]]: Transformation[C, FreeTransformed[C]] with Computation[FreeTransformed[C]] with

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

  override private[psbp] def bind[Z, Y] (tz: T[Z], `z=>ty` : => Z => T[Y]): T[Y] = 
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

private[psbp] def foldFree[Z, C[+ _]: Computation](fcz: FreeTransformed[C][Z]): C[Z] =

  type F[+Z] = C[Z]
  type T[+Z] = FreeTransformed[F][Z]
 
  val computationF: Computation[F] = summon[Computation[F]]
  import computationF.{ result => resultF, bind => bindF } 

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

`foldFree` *folds* a computation ADT of type `T[Z]`, which is an unfolded computation of type `C[Z]`, back to computation of type `C[Z]`.

Although `foldFree` is not fully tail recursive, the `Scala` compiler performs tail recursive optimization for those cases where it is tail recursive.

The one but last `case` in `foldFree` corresponds to the *associativity of binding*.

### `freeTransformedMaterialization`

```scala
package psbp.internal.implementation.materialization

import psbp.specification.materialization.Materialization

import psbp.internal.specification.computation.Computation

import psbp.internal.implementation.computation.transformation.{ FreeTransformed, foldFree }

private[psbp] given freeTransformedMaterialization[
  C[+ _]: Computation: 
  [C[+ _]] =>> Materialization[[Z, Y] =>> Z => C[Y], Z, Y], Z, Y]: 
  Materialization[[Z, Y] =>> Z => FreeTransformed[C][Y], Z, Y] with

  private type F[+Z] = C[Z]
  private type T[+Z] = FreeTransformed[F][Z] 

  private type `=>F`[-Z, +Y] = Z => F[Y]
  private type `=>T`[-Z, +Y] = Z => T[Y]

  private val materializationF: Materialization[`=>F`, Z, Y] = summon[Materialization[`=>F`, Z, Y]]
  import materializationF.{ materialize => materializeF }  

  private def `tu=>fu`: T[Unit] => F[Unit] =
    foldFree

  override val materialize: (Unit `=>T` Unit) => Z => Y =
    `u=>tu` =>
      materializeF(`u=>tu` andThen `tu=>fu`)
```

Transforming materialization of `[Z, Y] =>> Z => C[Y], Z, Y`, to materialization, of `[Z, Y] =>> Z => Free[C, Y], Z, Y`, is done using `freeTransformedMaterialization`.

Again, there is really only one meaningful way to define the `materialize` member in a way to get the types right.

For `materialize` the trivial choice for a programming component is `foldFree`. 

### `freeTransformedCoResulting`

```scala
package psbp.internal.implementation.computation.transformation

import psbp.internal.specification.computation.{ Computation, CoResulting }

private[psbp] given freeTransformedCoResulting[C[+ _]: Computation: CoResulting]: CoResulting[FreeTransformed[C]] with

  private type F[+Z] = C[Z]
  private type T[+Z] = FreeTransformed[F][Z] 
  
  private val coResultingF : CoResulting[F] = summon[CoResulting[F]]
  import coResultingF.{ coResult => `fz=>z` }

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

### `stateTransformedBinding` and `stateTransformedState`

```scala
package psbp.internal.implementation.computation.transformation

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.computation.Computation

import psbp.internal.specification.naturalTransformation.~>

import psbp.internal.specification.computation.transformation.Transformation

private[psbp] given stateTransformedBinding[
  S,
  C[+ _]: Computation]: Transformation[C, StateTransformed[S, C]] 
  with Binding[[Z] =>> StateTransformed[S, C][Z]] with
  
  private type F[+Z] = C[Z]
  private type T[+Z] = StateTransformed[S, C][Z]

  private type `=>T` = [Z, Y] =>> Z => T[Y]

  private val computationF: Computation[F] = summon[Computation[F]]
  import computationF.{ result => resultF, bind => bindF }

  override private[psbp] val `f~>t`: F ~> T = new {
    def apply[Z]: F[Z] => T[Z] =
      fz =>
        s =>
          bindF(fz, z => resultF((s, z)))
  }

  override private[psbp] def bind[Z, Y] (tz: T[Z], `z=>ty`: => Z => T[Y]): T[Y] =
    s =>
      bindF(tz(s), (s, z) => `z=>ty`(z)(s)) 
```

and

```scala
package psbp.internal.implementation.computation.transformation

import psbp.specification.program.state.State

import psbp.internal.specification.computation.Computation

private[psbp] given stateTransformedState[
  S,
  C[+ _]: Computation]: State[S, [Z, Y] =>> Z => StateTransformed[S, C][Y]] with 

  private type F[+Z] = C[Z]
  private type T[+Z] = StateTransformed[S, C][Z]

  private type `=>T` = [Z, Y] =>> Z => T[Y]

  private val computationF: Computation[F] = summon[Computation[F]]
  import computationF.{ result => resultF, bind => bindF }

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

import psbp.specification.materialization.Materialization

import psbp.specification.program.state.Initial

import psbp.internal.specification.computation.Computation

import psbp.internal.implementation.computation.transformation.StateTransformed

private[psbp] given stateTransformedMaterialization[
  S: Initial,
  C[+ _]: Computation: 
  [C[+ _]] =>> Materialization[[Z, Y] =>> Z => C[Y], Z, Y], Z, Y]: 
  Materialization[[Z, Y] =>> Z => StateTransformed[S, C][Y], Z, C[Y]] with

  private type F[+Z] = C[Z]
  private type T[+Z] = StateTransformed[S, C][Z]

  private type `=>F`[-Z, +Y] = Z => F[Y]
  private type `=>T`[-Z, +Y] = Z => T[Y]

  private val Materialization: Materialization[`=>F`, Z, Y] = summon[Materialization[`=>F`, Z, Y]]
  import Materialization.{ materialize => materializeF }

  private val computation: Computation[C] = summon[Computation[F]]
  import computation.{ result => resultF, bind => bindF }  

  private val initial: Initial[S] = summon[Initial[S]]
  import initial.{ s => initialS }

  override val materialize: (Unit `=>T` Unit) => Z => C[Y] =
    `u=>tu` =>
      z =>
        bindF(`u=>tu`(())(initialS), (s, _) => resultF(materializeF(resultF)(z)))
```

Transforming materialization of `[Z, Y] =>> Z => C[Y], Z, Y`, to materialization, of `[Z, Y] =>> Z => (S => C[(S, Y)]), Z, C[Y]`, is done using `stateTransformedMaterialization` which makes use of `initialS`. 

Again, there is really only one meaningful way to define the `materialize` member in a way to get the types right.

For `materialize` the trivial choice for a programming component is `materializeF`. 

`stateTransformedMaterialization` is defined in a completely different way as `freeTransformedMaterialization` and `reactiveTransformedMaterialization`. 

`freeTransformedMaterialization` and `reactiveTransformedMaterialization` do not make use of the materialization ingredients of `F[+ _]` at all.

### `reactiveTransformedParallel`

```scala
package psbp.internal.implementation.computation.transformation

import akka.actor.typed.{ ActorSystem, ActorRef, Behavior }

import akka.actor.typed.scaladsl.{ ActorContext, Behaviors }

import Behaviors.{ receive, stopped }

import ch.qos.logback.classic.{ Logger, LoggerContext, Level }

import Level.{ INFO, ERROR}

import org.slf4j.LoggerFactory.getILoggerFactory

import language.implicitConversions

import psbp.specification.types.&&

import psbp.specification.program.parallel.Parallel

import psbp.internal.specification.computation.Computation

import psbp.internal.implementation.computation.transformation.ReactiveTransformed

val packageName = "psbp.internal.implementation.computation.transformation"

def log[Z](actorContext: ActorContext[Z])(message: String): Unit = {
  val logger: Logger = getILoggerFactory().asInstanceOf[LoggerContext].getLogger(packageName);
  logger.setLevel(INFO);
  actorContext.log.info(message)
  logger.setLevel(ERROR);  
}

private[psbp] given reactiveTransformedParallel[
  C[+ _]: Computation]: Parallel[[Z, Y] =>> Z => ReactiveTransformed[C][Y]] with

  private type F[+Z] = C[Z]
  private type T[+Z] = ReactiveTransformed[C][Z]
  
  private val computation: Computation[F] = summon[Computation[F]]
  import computation.{ result => resultF, bind => bindF }
  
  override private[psbp] def parallel[Z, Y, X, W](`z=>tx`: Z => T[X], `y=>tw`: Y => T[W]): (Z && Y) => T[X && W] =
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
              val logging = log(context)
              logging(s"leftActor received LeftAct")
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
              val logging = log(context)
              logging(s"rightActor received RightAct")
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
                      val logging = log(context)
                      logging(s"reactor received both LeftReact($x) and RightReact($w)")
                      `(x,w)=>u`(x,w)
                      stopped
                    case None => 
                      react(Some(x), None)
                  }
                case RightReact(w) => 
                  `option[x]` match {
                    case Some(x) => 
                      val logging = log(context)
                      logging(s"reactor received both RightReact($w) and LeftReact($x)")
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
                  val logging = log(context)
                  logging(s"reactor received React($y)")
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

Some `logging` has been added in to illustrate how `leftActor` and `rightActor` act, resp. `reactor` reacts.

## Specific implementation `given`'s

### `activeProgram` 

```scala
package psbp.implementation.active

import psbp.specification.program.Program

import psbp.internal.specification.computation.Resulting

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.computation.Computation

import psbp.internal.specification.computation.programFromComputation

import psbp.internal.specification.computation.computationFromResultingAndBinding

given activeResulting: Resulting[Active] with

  private[psbp] def result[Z]: Z => Active[Z] =
    z =>
      z

given activeBinding: Binding[Active] with

  private[psbp] def bind[Z, Y] (cz: Active[Z], `z=>cy`: => Z => Active[Y]): Active[Y] =
    `z=>cy`(cz)

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

import psbp.specification.materialization.Materialization

given activeMaterialization: Materialization[`=>A`, Unit, Unit] with

  val materialize: (Unit `=>A` Unit) => Unit => Unit =
    identity
```

Materialization of `` `=>A` `` is completely trivial.

### Pure functional programming

Think of active programming as *pure functional programming*.

Of course, using `Scala`, it is possible to write functions that perform side effects along the way of transforming their argument to a result.

For example, *inside* of the active materialized implementation of `mainFactorial`, `intProducer` and`factorialConsumer` perform side effects.

The intention is to push them to the *outside*.

See [mainFactorial](https://psbp-library.github.io#mainfactorial) for more details.

### `activeCoResulting`

```scala
package psbp.implementation.active

import psbp.internal.specification.computation.CoResulting

given activeCoResulting: CoResulting[Active] with
  override def coResult[Z]: Active[Z] => Z =
    identity
```

Co-resulting of `Active` is completely trivial.

### `reactiveProgram`

```scala
package psbp.implementation.reactive

import psbp.specification.program.Program

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.computation.programFromComputation

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.specification.computation.transformation.resultingFromTransformation

import psbp.internal.implementation.computation.transformation.reactiveTransformedBinding

import psbp.implementation.active.Active

import psbp.implementation.active.{ activeResulting, activeBinding }

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

Transforming from active programming with `` `=>A` `` to reactive programming with `` `=>R` `` is done by using a combination of `programFromComputation`,`reactiveTransformedBinding`, and friends `computationFromResultingAndBinding` and `resultingFromTransformation`.

See [programFromComputation](https://psbp-library.github.io#programfromcomputation), [reactiveTransformedBinding](https://psbp-library.github.io#reactivetransformedbinding), [computationFromResultingAndBinding](https://psbp-library.github.io#computationfromresultingandbinding) and [resultingFromTransformation](https://psbp-library.github.io#resultingfromtransformation) for more details.

### `reactiveMaterialization`

```scala
package psbp.implementation.reactive

import psbp.specification.materialization.Materialization

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.implementation.computation.transformation.reactiveTransformedMaterialization

import psbp.implementation.active.Active

import psbp.implementation.active.{ activeResulting, activeBinding, activeMaterialization, activeCoResulting }

given reactiveMaterialization: Materialization[`=>R`, Unit, Unit] =
  reactiveTransformedMaterialization[Active, Unit, Unit]
```

Transforming from active materialization of `` `=>A` `` to reactive materialization of `` `=>R` `` is done by using `reactiveTransformedMaterialization` and friend `computationFromResultingAndBinding`.

See [reactiveTransformedMaterialization](https://psbp-library.github.io#reactiveTransformedmaterialization) and [computationFromResultingAndBinding](https://psbp-library.github.io#computationfromresultingandbinding) for more details.

### `freeActiveProgram`

```scala
package psbp.implementation.freeActive

import psbp.specification.program.Program

import psbp.internal.specification.computation.Computation

import psbp.internal.specification.computation.programFromComputation

import psbp.internal.implementation.computation.transformation.freeTransformedComputation

import psbp.implementation.active.Active

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

Transforming from active programming with `` `=>A` `` to free active programming with `` `=>FA` `` is done by using a combination of `programFromComputation` and `freeTransformedComputation`.

See [programFromComputation](https://psbp-library.github.io#programfromcomputation) and [freeTransformedComputation](https://psbp-library.github.io#freetransformedcomputation) for more details.

### `freeActiveMaterialization`

```scala
package psbp.implementation.freeActive

import psbp.specification.materialization.Materialization

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.implementation.materialization.freeTransformedMaterialization

import psbp.implementation.active.Active

import psbp.implementation.active.{ activeResulting, activeBinding, activeMaterialization }

given freeActiveMaterialization: Materialization[`=>FA`, Unit, Unit] =
  freeTransformedMaterialization[Active, Unit, Unit]
```

Transforming from active materialization of `` `=>A` `` to free active materialization of `` `=>FA` `` is done by using `freeTransformedMaterialization` and friend `computationFromResultingAndBinding`.

See [freeTransformedMaterialization](https://psbp-library.github.io#freetransformedmaterialization) and [computationFromResultingAndBinding](https://psbp-library.github.io#computationfromresultingandbinding) for more details.

### `stateActiveProgram` and `stateActiveState`

```scala
package psbp.implementation.stateActive

import psbp.specification.program.Program

import psbp.internal.specification.computation.Binding

import psbp.internal.specification.computation.programFromComputation

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.specification.computation.transformation.resultingFromTransformation

import psbp.internal.implementation.computation.transformation.stateTransformedBinding

import psbp.implementation.active.Active

import psbp.implementation.active.{ activeResulting, activeBinding }

given stateActiveBinding[S]: Binding[StateActive[S]] = stateTransformedBinding[S, Active]

given stateActiveProgram[S]: Program[`=>SA`[S]] = programFromComputation[StateActive[S]]
```

and

```scala
package psbp.implementation.stateActive

import psbp.specification.program.state.State

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.implementation.computation.transformation.stateTransformedState

import psbp.implementation.active.Active

import psbp.implementation.active.{ activeResulting, activeBinding }

given stateActiveState[S]: State[S, `=>SA`[S]] = stateTransformedState[S, Active]
```

where

```scala
package psbp.implementation.stateActive

import psbp.internal.implementation.computation.transformation.StateTransformed

import psbp.implementation.active.Active

type StateActive[S] = [Y] =>> StateTransformed[S, Active][Y] 

type `=>SA`[S] = [Z, Y] =>> Z => StateActive[S][Y]
```

Transforming from active programming with `` `=>A` `` to active with state programming with `` `=>SA[S]` `` is done by using a combination of `programFromComputation`, `stateTransformedBinding`, `stateTransformedState`, and friends `computationFromResultingAndBinding` and `resultingFromTransformation`.

See [programFromComputation](https://psbp-library.github.io#programfromcomputation), [stateTransformedBinding](https://psbp-library.github.io#statetransformedbinding), [stateTransformedState](https://psbp-library.github.io#statetransformedstate), [computationFromResultingAndBinding](https://psbp-library.github.io#computationfromresultingandbinding) and [resultingFromTransformation](https://psbp-library.github.io#resultingfromtransformation) for more details.

### `stateActiveMaterialization`

```scala
package psbp.implementation.stateActive

import psbp.specification.types.&&

import psbp.specification.program.state.Initial

import psbp.specification.materialization.Materialization

import psbp.internal.implementation.materialization.stateTransformedMaterialization

import psbp.implementation.active.Active

import psbp.implementation.active.{ activeComputation, activeMaterialization }

given stateActiveMaterialization[S: Initial]: Materialization[`=>SA`[S], Unit, Unit] =
  stateTransformedMaterialization[S, Active, Unit, Unit]
```

Transforming from active materialization of `` `=>A` `` to active with state materialization of `` `=>R` `` `` `=>SA`[S] `` is done by using `stateTransformedMaterialization` and friend `computationFromResultingAndBinding`.

See [stateTransformedMaterialization](https://psbp-library.github.io#stateTransformedmaterialization) and [computationFromResultingAndBinding](https://psbp-library.github.io#computationfromresultingandbinding) for more details.

### `reactiveParallel`

```scala
package psbp.implementation.reactive

import psbp.specification.program.parallel.Parallel

import psbp.internal.specification.computation.computationFromResultingAndBinding

import psbp.internal.implementation.computation.transformation.reactiveTransformedParallel

import psbp.implementation.active.Active

import psbp.implementation.active.{ activeResulting, activeBinding }

given reactiveParallel: Parallel[`=>R`] = reactiveTransformedParallel[Active]
```

Transforming from active programming with `` `=>A` `` to reactive programming with parallelism with `` `=>R` `` is done by using `reactiveTransformedParallel` and friend `computationFromResultingAndBinding`.

[reactiveTransformedParallel](https://psbp-library.github.io#reactivetransformedparallel) and [computationFromResultingAndBinding](https://psbp-library.github.io#computationfromresultingandbinding) for more details.




