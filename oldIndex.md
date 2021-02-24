

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