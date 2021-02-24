
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