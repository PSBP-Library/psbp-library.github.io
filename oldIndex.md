

## `factorial`

```scala
package examples.specification.program

import psbp.specification.program.Program

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
package examples.specification.program

import scala.language.postfixOps

import psbp.specification.types.&&

import psbp.specification.functional.Functional

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

`factorial` is a program that makes use of programming capabilities 

- `asProgram` (being functional)
- `>-->` (sequential composition)
- `Let {} In {}` (defining local values)
- `If() {} Else {}` (performing if-then-else logic)

Let's explain the pointfree definition of `factorial` in a pointful way using an argument `i` (thinking of programs as functions).

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

a local value, which equals `(subtractOne >--> factorial)(i)`, which equals `factorial(subtractOne(i))`, which equals `factorial(i - 1)` is constructed, which is available as the second component of a product value `(i, factorial(i - 1))`.

At level `3.2`, `factorial` looks like

```scala
      multiply
```

The result is `multiply(i, factorial(i - 1))`, which equals `i * factorial(i - 1)`.

## `fibonacci`

```scala
package examples.specification.program

import psbp.specification.program.Program

def fibonacci[>-->[- _, + _]: Program]: BigInt >--> BigInt =

  val program: Program[>-->] = summon[Program[>-->]]
  import program.{ Let, If }

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
package examples.specification.program

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

`fibonacci` is a program that makes use of programming capabilities 

- `asProgram` (being functional)
- `>-->` (sequential composition)
- `&&` and `&&&` (construction)
- `If() {} Else {}` (performing if-then-else logic)

Let's explain the pointfree definition of `fibonacci` in a pointful way using an argument `i` (thinking of programs as functions).

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

The result is `add(fibonacci(i - 1), fibonacci(i - 2))` which equals `fibonacci(i - 1) + fibonacci(i - 2)`

## `mainFactorial`

```scala
package examples.specification.program.effectful

import psbp.specification.program.Program

import examples.specification.program.factorial

def mainFactorial[>-->[- _, + _]: Program]: Unit >--> Unit =
  factorial toMainWith (
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

## `mainFibonacci`

```scala
package examples.specification.program.effectful

import psbp.specification.program.Program

import examples.specification.program.fibonacci

def mainFibonacci[>-->[- _, + _]: Program]: Unit >--> Unit =
  fibonacci toMainWith (
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

## Materialization

```scala
package psbp.specification.materialization

trait Materialization[>-->[- _, + _], -Z, +Y]:

  // declared

  val materialize: (Unit >--> Unit) => Z => Y

  // defined extensions

  extension (`u>-->u`: Unit >--> Unit) def materialized: Z => Y =
    materialize(`u>-->u`) 
```

Main programs are materialized to functions.

The function type `Z => Y` is generic to allow for various materializations of main programs.

The public member `materialized` is an `extension` that can be used as postfix operator.

# Computations

An *internal specification part* of the `PSBP` library models *computing*.

Think about it as explaining to `Scala` what computing is all about.

Members of internal specification `trait`'s are referred to as *computational capabilities*.

A `val` that is defined in terms of computational capabilities is referred to as a *computation*. 

A computation has a *descriptive* nature, in fact it would be more appropriate to refer to it as a *computation description*.

In a way a computation is a generalization of an *expression*.

An expression of type `Y` evaluates to a *result* of type `Y`

A computation of type `C[Y]` *describes* computing a result of type `Y`.

A computation may, or may not, *describe* performing side effects along the way.

## Basic computational capabilities

The *basic* computational capabilities are

- *resulting*
- *binding*

Computational capabilities have an *operational* nature.

They contribute to *performing computations*

Performing computations generalizes *evaluating expressions*.


## `Resulting`

```scala
package psbp.specification.internal.resulting

private[psbp] trait Resulting[C[+ _]]:

  // declared

  private[psbp] def result[Z]: Z => C[Z]
```

The `result` member of `Computation` specifies that performing a computation yields a *result*.

## `Binding`

```scala
package psbp.specification.internal.binding

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

The `bind` computational capability specifies that while performing a computation, the result yielded by performing an inner computation can be *bound* to the argument of a *continuation function* transforming it to a result that is an outer computation where performing continues with.

The `join` computational capability specifies that an inner computing result can be seen as an outer computation.

The member `>=` is an`extension` that can be used as infix operator.

The member `joined` is an`extension` that can be used as postfix operator.

## `Computation`

```scala
package psbp.specification.internal.computation

import psbp.specification.types.{ &&, || }

import psbp.specification.program.Program

import psbp.specification.internal.resulting.Resulting

import psbp.specification.internal.binding.Binding

private[psbp] trait Computation[C[+ _]] 
  extends Resulting[C] 
  with Binding[C]
  with Program[[Z, Y] =>> Z => C[Y]]:

  // defined programming capabilities  
  
  private type `=>C`[-Z, +Y] = Z => C[Y]

  private[psbp] override def toProgram[Z, Y]: (Z => Y) => Z `=>C` Y = 
    `z=>y` => 
      z =>
        result(`z=>y`(z))

  private[psbp] override def andThen[Z, Y, X](`z>-->y`: Z `=>C` Y, `y>-->x`: => Y `=>C` X): Z `=>C` X =
    z =>
      `z>-->y`(z) >= 
        `y>-->x`

  private[psbp] override def construct[Z, Y, X] (`z>-->y`: Z `=>C` Y, `z>-->x`: => Z `=>C` X): Z `=>C` (Y && X) =
    z =>
      `z>-->y`(z) >= { y => 
        `z>-->x`(z) >= { x =>
          result(y, x)
        }
      }

  private[psbp] override def conditionally[Z, Y, X] (`y>-->z`: => Y `=>C` Z, `x>-->z`: => X `=>C` Z): (Y || X) `=>C` Z =
    _.foldSum(`y>-->z`, `x>-->z`)   

```

It turns out that the basic programming capabilities of a program of type `Z => C[Y]` can be defined in terms of the basic computational capabilities of the computation of type `C[Y]`.

Compare this with functions being defined in terms of expressions.

## Setting the scene

The `PSBP` library limits the programming types `Z >--> Y` to programming types `Z => C[Y]` defined in terms of computing types `C[Y]`.

The `PSBP` specification `trait`''s define an *application developer API*.

The internal `PSBP` specification `trait`''s define a  *library developer API*.

It is a deliberate choice to not let application developers make use of the pointful library developer API, forcing them to think in a pointfree way.

# Active programming

So far no implementation of the basic programming capabilities has been given yet.

A completely trivial way to implement programs of type `Z >--> Y` is as functions of type `Z => Y`, referred to as *active programs*.

## `` Program[`=>A`] `` 

```scala
package psbp.active.program

import psbp.specification.program.Program

import psbp.specification.internal.computation.Computation

import psbp.active.types._

given Computation[Active] with Program[`=>A`] with

  private[psbp] def result[Z]: Z => Active[Z] =
    z =>
      z

  private[psbp] def bind[Z, Y] (cz: Active[Z], `z=>cy`: => Z => Active[Y]): Active[Y] =
    `z=>cy`(cz)
```

where

```scala
package psbp.active.types

type Active[+Y] = Y

type `=>A`[-Z, +Y] = Z => Active[Y]
```

`Active` is a  `given` implementation of `Computation`.

As a consequence, `` `=>A` `` is an implementation of `Program`.

Think of active programming as *pure functional programming*.

Of course, using `Scala`, it is possible to write functions that perform side effects along the way of transforming their argument to a result.

For example, inside of `mainFactorial`, `intProducer` are `factorialConsumer` perform side effects.

Eventually side effects are unavoidable, but the intention is to push them as far as possible to the outside of programs in general, and main programs in particular.

## `` Materialization[`=>A`, Unit, Unit] `` 

```scala
package psbp.active.materialization

import psbp.specification.materialization.Materialization

import psbp.active.types.`=>A`

given Materialization[`=>A`, Unit, Unit] with

  val materialize: (Unit `=>A` Unit) => Unit => Unit =
    identity
```

Materialization of `` `=>A` `` is completely trivial.

## running active effectful factorial

```scala
package examples.active.program.effectful

import psbp.active.program.given

import psbp.active.materialization.given

import examples.specification.program.effectful.mainFactorial

@main def factorial(args: String*): Unit =
  mainFactorial materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.reactive.program.effectful.factorial 
Please type an integer
10
applying factorial to the integer argument 10 yields result 3628800
[success] ...
```

## running active effectful fibonacci

```scala
package examples.active.program.effectful

import psbp.active.program.given

import psbp.active.materialization.given

import examples.specification.program.effectful.mainFibonacci

@main def fibonacci(args: String*): Unit =
  mainFibonacci materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.active.program.effectful.fibonacci 
Please type an integer
10
applying fibonacci to the integer argument 10 yields result 55
[success] ...
```

# Reactive programming

A less trivial way to implement programs of type `Z >--> Y` is as functions of type `Z => (Y => Unit) => Unit`, referred to as *reactive programs*.

A function of type `Y => Unit` is referred to as a *callback*.

A function of type `(Y => Unit) => Unit` is referred to as a *callback handler*.

Reactive programs are functions that transform an argument to a callback handler result.

## `` Program[`=>R`] `` 

```scala
package psbp.reactive.program

import psbp.specification.program.Program

import psbp.specification.internal.computation.Computation

import psbp.reactive.types._

given Computation[Reactive] with Program[`=>R`] with

  private[psbp] def result[Z]: Z => Reactive[Z] =
    z => 
      `z=>u` =>
        `z=>u`(z)

  private[psbp] def bind[Z, Y] (cz: Reactive[Z], `z=>cy`: => Z => Reactive[Y]): Reactive[Y] =
    `y=>u` =>
      cz { 
        z =>
          `z=>cy`(z)(`y=>u`)
      }
```

where

```scala
package psbp.reactive.types

type Reactive[+Y] = (Y => Unit) => Unit

type `=>R`[-Z, +Y] = Z => Reactive[Y]
```

`Reactive` is a  `given` implementation of `Computation`.

As a consequence, `` `=>R` `` is an implementation of `Program`.

## `` Materialization[`=>R`, Unit, Unit] `` 

```scala
package psbp.reactive.materialization

import psbp.specification.materialization.Materialization

import psbp.reactive.types.`=>R`

given Materialization[`=>R`, Unit, Unit] with

  val materialize: (Unit `=>R` Unit) => Unit => Unit =
    `u>-->u` =>
      u =>
        `u>-->u`(u)(identity)
```

Materialization of `` `=>R` `` is less trivial.

## Getting the types right

For reactive programming, there is really only one reasonable way to define `result`, `bind` and `materialize` in a way to get the types right.

This is a recurring theme in pure functional programming and, as a generalization, in program description based programming.

Often an almost trivial choice for a function component, resp. program component does the trick to get the types right.

## reactive `factorial`

```scala
package examples.reactive.program.effectful

import psbp.reactive.program.given

import psbp.reactive.materialization.given

import examples.specification.program.effectful.mainFactorial

@main def factorial(args: String*): Unit =
  mainFactorial materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.reactive.program.effectful.factorial 
Please type an integer
10
applying factorial to the integer argument 10 yields result 3628800
[success] ...
```

The only difference with the active version is the usage of different dependency injections by `import`.

## reactive `fibonacci`

```scala
package examples.reactive.program.effectful

import psbp.reactive.program.given

import psbp.reactive.materialization.given

import examples.specification.program.effectful.mainFibonacci

@main def fibonacci(args: String*): Unit =
  mainFibonacci materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.reactive.program.effectful.fibonacci 
Please type an integer
10
applying fibonacci to the integer argument 10 yields result 55
[success] ...
```

`fibonacci` is extremely slow.

You better not try it with `1000`, or even with `250`, instead of `10`!

# Accumulator based optimization

```scala
package psbp.specification.program

// ...

def optimizeWith[>-->[- _, + _]: Program, Z, A, Y](
  accumulatorInitializer: Z >--> A,
  argumentPredicate: Z >--> Boolean,
  updater: (Z && A) >--> (Z && A),
  resultExtractor: A >--> Y): Z >--> Y =

  val program: Program[>-->] = summon[Program[>-->]]
  import program.{ Let, If }

  val argument: (Z && A) >--> Z = `(z&&y)>-->z`
  val accumulator: (Z && A) >--> A = `(z&&y)>-->y`
  val updated: ((Z && A) && (Z && A)) >--> (Z && A) = `(z&&y)>-->y`

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
```

Programs like `fibonacci` can be optimized using an *accumulator*.

## `optimizedFactorial`

```scala
package examples.specification.program

import psbp.specification.program.Program

import psbp.specification.program.{ `(z&&y)>-->z` => argument, `z>-->z` => accumulator }

import psbp.specification.program.optimizeWith

import examples.specification.program.{ isZero, one, subtractOne, multiply }

def optimizedFactorial[>-->[- _, + _]: Program]: BigInt >--> BigInt =
  optimizeWith(
    accumulatorInitializer = one, 
    argumentPredicate = isZero, 
    updater = (argument >--> subtractOne) && multiply, 
    resultExtractor = accumulator
  )
```

For `optimizedFactorial` the accumulator type is `BigInt`.

One `BigInt` optimizes one recursion occurrence.

## `optimizedFibonacci`

```scala
package examples.specification.program

import psbp.specification.program.Program

import psbp.specification.program.{ `(z&&y)>-->z` => firstAccumulator, `(z&&y)>-->y` => secondAccumulator  }

import psbp.specification.program.optimizeWith

import examples.specification.program.{ isZero, isOne, one, subtractOne, subtractTwo, add }

def optimizedFibonacci[>-->[- _, + _]: Program]: BigInt >--> BigInt =
  optimizeWith(
    accumulatorInitializer = one && one, 
    argumentPredicate = isZero, 
    updater = subtractOne &&& (secondAccumulator && add), 
    resultExtractor = firstAccumulator
  )
```

For `optimizedFibonacci` the accumulator type is `BigInt && BigInt`.

Two `BigInt`'s optimize two recursion occurrences.

## `mainOptimizedFactorial`

```scala
package examples.specification.program.effectful

import psbp.specification.program.Program

import examples.specification.program.optimizedFactorial

import examples.specification.program.effectful.intProducer

import examples.specification.program.effectful.factorialConsumer

def mainOptimizedFactorial[>-->[- _, + _]: Program]: Unit >--> Unit =
  optimizedFactorial toMainWith (
    producer = intProducer,
    consumer = factorialConsumer
  )
```

## `mainOptimizedFibonacci`

```scala
package examples.specification.program.effectful

import psbp.specification.program.Program

import examples.specification.program.optimizedFibonacci

import examples.specification.program.effectful.intProducer

import examples.specification.program.effectful.fibonacciConsumer

def mainOptimizedFibonacci[>-->[- _, + _]: Program]: Unit >--> Unit =
  optimizedFibonacci toMainWith (
    producer = intProducer,
    consumer = fibonacciConsumer
  )
```

## running active effectful optimized factorial

```scala
package examples.active.program.effectful

import psbp.active.program.given

import psbp.active.materialization.given

import examples.specification.program.effectful.mainOptimizedFactorial

@main def optimizedFactorial(args: String*): Unit =
  mainOptimizedFactorial materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.active.program.effectful.optimizedFactorial 
Please type an integer
10
applying factorial to the integer argument 10 yields result 3628800
[success] ...
```

## running active effectful optimized fibonacci

```scala
package examples.active.program.effectful

import psbp.active.program.given

import psbp.active.materialization.given

import examples.specification.program.effectful.mainOptimizedFibonacci

@main def optimizedFibonacci(args: String*): Unit =
  mainOptimizedFibonacci materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.active.program.effectful.optimizedFibonacci 
Please type an integer
10
applying fibonacci to the integer argument 10 yields result 89
[success] ...
```

You can also try it with `1000` instead of `10`!

## reactive optimized `factorial`

```scala
package examples.reactive.program.effectful

import psbp.reactive.program.given

import psbp.reactive.materialization.given

import examples.specification.program.effectful.mainOptimizedFactorial

@main def optimizedFactorial(args: String*): Unit =
  mainOptimizedFactorial materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.reactive.program.effectful.optimizedFactorial 
Please type an integer
10
applying factorial to the integer argument 10 yields result 3628800
[success] ...
```

## reactive optimized `fibonacci`

```scala
package examples.reactive.program.effectful

import psbp.reactive.program.given

import psbp.reactive.materialization.given

import examples.specification.program.effectful.mainOptimizedFibonacci

@main def optimizedFibonacci(args: String*): Unit =
  mainOptimizedFibonacci materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.reactive.program.effectful.optimizedFibonacci 
Please type an integer
10
applying fibonacci to the integer argument 10 yields result 89
[success] ...
```

You can also try it with `250` instead of `10`!

# Transformations

Until now active and reactive `given` implementations of `Program` have been dealt with.

Using `transformations` one `given` implementation can be transformed to another one.

## `~>`

```scala
package psbp.specification.internal.transformation

private[psbp] trait ~>[F[+ _], T[+ _]]:

  // declared

  private[psbp] def apply[Z]: F[Z] => T[Z] 
```

A *natural transformation* of type `F ~> T` is like a function of type `F[Z] => T[Z]`. 

Instead of transforming at the type level it transforms at the *type constructor* level.

## `Transformation`

```scala
package psbp.specification.internal.computation.transformation

import psbp.specification.program.Program

import psbp.specification.internal.computation.Computation

import psbp.specification.internal.transformation.~>

private[psbp] trait Transformation[F[+ _]: Computation, T[+ _]]
  extends Computation[T] 
  with Program[[Z, Y] =>> Z => T[Y]]:

    // declared

    private[psbp] val `f~>t`: F ~> T
  
    // defined computational capabilities 

    override private[psbp] def result[Z]: Z => T[Z] = 

      import `f~>t`.{ apply => `fz=>tz` }

      val computationF: Computation[F] = summon[Computation[F]]
      import computationF.{ result => `z=>fz` }
      
      `z=>fz` andThen `fz=>tz`
```

A *computation transformation* uses a transformation to define `result` of the `T[+ _]` computation in terms of `result` of the `F[+ _]` computation.

## `ReactiveTransformation`

```scala
package psbp.specification.internal.computation.transformation.reactive

import psbp.specification.internal.computation.Computation

import psbp.specification.internal.transformation.~>

import psbp.specification.internal.computation.transformation.Transformation

private[psbp] type ReactiveTransformed[C[+ _]] = [Z] =>> (C[Z] => Unit) => Unit

private[psbp] trait ReactiveTransformation[C[+ _]: Computation] 
  extends Transformation[C, ReactiveTransformed[C]]:

    private type F[+Z] = C[Z]
    private type T[+Z] = ReactiveTransformed[F][Z]
  
    private val computationF: Computation[F] = summon[Computation[F]]
    import computationF.{ result => resultF, bind => bindF }
    
    override private[psbp] val `f~>t`: F ~> T = new {
      def apply[Z]: F[Z] => T[Z] =
        fz => 
          `fz=>u` =>
            `fz=>u`(fz)
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

Any computation type `[Z] =>> C[Z]` can be transformed to a reactive one of type `[Z] =>> (C[Z] => Unit) => Unit`.

## Reactive programming revisited

```scala
package psbp.reactive.program

import psbp.specification.program.Program

import psbp.specification.internal.computation.Computation

import psbp.specification.internal.computation.transformation.Transformation

import psbp.specification.internal.computation.transformation.reactive.ReactiveTransformation

import psbp.active.types.Active

import psbp.reactive.types.{ Reactive, `=>R` }

import psbp.active.program.given

given Computation[Reactive] with Program[`=>R`] with

  object reactiveComputation
    extends ReactiveTransformation[Active] 
    with Transformation[Active, Reactive]

  export reactiveComputation.result
  export reactiveComputation.bind
```

where

```scala
package psbp.reactive.types

import psbp.specification.internal.computation.transformation.reactive.ReactiveTransformed

import psbp.active.types.Active

type Reactive[+Y] = ReactiveTransformed[Active][Y] 

type `=>R`[-Z, +Y] = Z => Reactive[Y]
```

The `Reactive` type can be defined using `ReactiveTransformed` and `Active`.

`Reactive` is a `given` implementation of `Computation` that can be defined using `ReactiveTransformation` and `Active`.

## `FreeTransformation`

```scala
```