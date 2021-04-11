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

# Program

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

Main programs, programs of type `Unit >--> Unit` are materialized to functions in order to be able to eventually use them, together with implementation `given`'s, dependency injected by `import`, in `@main` `Scala` code.

The function type `Z => Y` is generic to allow for various materializations.

`materialize` is a `private[psbp]` member.

`materialized` is an public syntax `extension` that can be used as postfix operator.

## `Identity`

```scala
package psbp.external.specification.program

trait Identity[>-->[- _, + _]]:

  // declared

  def `z>-->z`[Z]: Z >--> Z

  // defined

  def identity[Z]: Z >--> Z =
    `z>-->z`

  def `y>-->y`[Y]: Y >--> Y =
    `z>-->z`[Y]

  def `x>-->x`[X]: X >--> X =
    `z>-->z`[X]      
  
  def `w>-->w`[W]: W >--> W =
    `z>-->z`[W]

  def `v>-->v`[V]: V >--> V =
    `z>-->z`[V]  

  // ...

  def `u>-->u`: Unit >--> Unit =
    `z>-->z`[Unit] 

  // ...  
```

`Identity` specifies that *there exists a program transforming its argument to a result that is equal to its argument, doing nothing else*.

`z>-->z` is a public declared member.

Names like `` `z>-->z` `` can be thought of as *typeful generic names for programs*.

After all there is only one meaningful *generic program* of type `Z >--> Z` transforming its argument to a result, doing nothing else.

So why not giving it the name `` `z>-->z` ``?

`Identity` also defines some useful synonyms of `` `z>-->z` ``, especially for dealing with other types, like the name `` `y>-->y` `` for dealing with type `Y`.

### `Functional`

```scala
package psbp.external.specification.program

trait Functional[>-->[- _, + _]]: 

  // declared

  private[psbp] def toProgram[Z, Y]: (Z => Y) => Z >--> Y

  // defined extensions

  extension [Z, Y] (`z=>y`: Z => Y) def asProgram: Z >--> Y =
    toProgram(`z=>y`)
```

`Functional` specifies that *functions can be used as programs*.

Names like `` `z=>y` `` can be thought of as *typeful generic names for functions*.

There is, for example, only one meaningful *generic function* of type `Z => Z` *for all types* `Z` transforming its argument to a result, doing nothing else.

So why not giving it the name `` `z=>z` ``?

`toProgram` is a `private[psbp]` declared member.

`asProgram` is a public syntax `extension` that can be used as postfix operator.

The full power that comes with the `PSBP` library is not available for functions that are used as programs using `asProgram`, so, which functions to use as programs using `asProgram` is an important choice.

Note that it is possible to define `Functional[>-->[- _, + _]]` so that it `extend`s `Identity[>-->]`.

This is an object oriented programming way of implementing a specification `trait`.

The course promotes the functional programming way of implementing a specification `trait` as an *implementation* `given`.

#### Exercise (easy)

*Define the generic identity function* `` `z==>z` `` *of type* `Z => Z` *transforming its argument to a result, doing nothing else, and use it in* `@main` *code* to test that it correct.

#### Solution

```scala
package exercises.external.specification.function

def `z==>z`[Z]: Z => Z =
  z =>
    z

@main def main(args: String*): Unit =
  val argument = 12345
  val result = `z==>z`(argument)
  println(s"applying `z==>z` to the integer argument $argument yields result $result")
```

Note that, in order to be useful, `main` performs an external side effect.

#### Exercise (easy)

*Define a generic function* `` `z=s=>z` `` *of type* `Z => Z` *other than the identity function* `` `z>-->z` ``, *and use it* `@main` *code to test that it differes from* `` `z>-->z` ``.

*Hints*

There is no way out: `` `z=s=>z` `` has to perform an external side effect because it is not possible to generically change an argument in a meaningful way.

#### Solution

```scala
package exercises.external.specification.function

def `z=s=>z`[Z]: Z => Z =
  z =>
    println("side effect")
    z

@main def main(args: String*): Unit =
  `z=s=>z`(12345)
```

Note that `` `z=s=>z` `` performs an external side effect.

Of course, it is *not* the intention to define and use functions this way.

#### Exercise (moderate)

*Define, not making use of* `Identity[>-->]`*, the generic identity program* `z>-->z` *of type* `Z >--> Z` *corresponding to* `z>-->z` *of* `Identity[>-->]`.

*Hints*

- See [Context Bounds](https://dotty.epfl.ch/docs/reference/contextual/context-bounds.html) for context bound syntax
- Use it for `>-->[- _, + _]` with `Functional`

#### Solution

```scala
package exercises.external.specification.functional

import scala.language.postfixOps

import psbp.external.specification.program.Functional

import exercises.external.specification.function

def `z>-->z`[Z, >-->[- _, + _]: Functional]: Z >--> Z =
  function.`z==>z` asProgram
```

Note that `asProgram` is automatically available since it is an `extension`.

Also note that `z>-->z` cannot be used *as is* in `@main` code.

#### Exercise (easy now)

*Define a generic program* `` `z>-s->z` `` *of type* `Z >-> Z` *other than the identity program* `z>-->z` *of* `Identity[>-->]`.

#### Solution

```scala
package exercises.external.specification.functional

import scala.language.postfixOps

import psbp.external.specification.program.Functional

import exercises.external.specification.function

def `z>-s->z`[Z, >-->[- _, + _]: Functional]: Z >--> Z =
  function.`z=s=>z` asProgram
```

Of course, it is *not* the intention to define functions as programs this way.

#### Exercise (moderate)

*Define a generic implementation* `given` *of* `Identity[>-->]`.

*Hints*

- See [](https://dotty.epfl.ch/docs/reference/contextual/givens.html) for `given` syntax
- Use it for `Identity[>-->]`

#### Solution

```scala
package exercises.external.specification.functional

import scala.language.postfixOps

import psbp.external.specification.program.Identity

import psbp.external.specification.program.Functional

import exercises.external.specification.function

given identityFromFunctional[>-->[- _, + _]: Functional]: Identity[>-->] with
  override def `z>-->z`[Z]: Z >--> Z =
    function.`z==>z` asProgram
```

Note that, although it makes perfect sense to add this `given` to the `PSBP` library code, another implementation* `given` of `Identity[>-->]` is added since, as stated in the introduction, programming ingredients will be implemented using computing ingredients anyway.

See [setting the scene](https://psbp-library.github.io/introduction.md#setting-the-scene).

### `Composition`

```scala
package psbp.external.specification.program

trait Composition[>-->[- _, + _]]:

  // declared

  private[psbp] def andThen[Z, Y, X](
    `z>-->y`: Z >--> Y
    , `y>-->x`: => Y >--> X
  ) : Z >--> X

  // defined extensions
  
  extension [Z, Y, X] (`z>-->y`: Z >--> Y) 
    def >-->(`y>-->x`: => Y >--> X): Z >--> X =
      andThen(`z>-->y`, `y>-->x`)
```

`Composition` specifies that *programs can be composed sequentially*.

Sequential composition is simply referred to as composition.

`andThen` is a `private[psbp]` declared member.

`>-->` is a public syntax `extension` that can be used as infix operator.

The type of the second parameter of `andThen` is a *call-by-name* one.

Typeful generic names, hopefully, are convient when writing code like 

- `` val `z>-->x` = `z>-->y` >--> `y>-->x` ``.

### `Classification`

```scala
package psbp.external.specification.program

trait Classification[>-->[- _, + _]]
  extends Identity[>-->]
  with Composition[>-->]
```

It is agreed upon that, in the world of *function abstractions*, *classification* of into *classes* is based upon a combination of *identity* and *composition*.

Traditionally such classes are referred to as *categories*.

See [Category Theory](https://en.wikipedia.org/wiki/Category_theory).

### `Construction`

```scala
package psbp.external.specification.program

import psbp.external.specification.types.&&

trait Construction[>-->[- _, + _]]:

  // declared

  private[psbp] def construct[Z, Y, X](
    `z>-->y`: Z >--> Y
    , `z>-->x`: => Z >--> X
  ): Z >--> (Y && X) 

  // defined extensions

  extension [Z, Y, X] (`z>-->y`: Z >--> Y) 
    def &&(`z>-->x`: => Z >--> X): Z >--> (Y && X) =
      construct(`z>-->y`, `z>-->x`)
```

where

```scala
package psbp.external.specification.types

// product

type &&[+Z, +Y] = (Z, Y)

// ...
```

`Construction` specifies that *programs can construct product based composite data*.

`construct` is a `private[psbp]` declared member.

`&&` is a public syntax `extension` that can be used as infix operator.

The type of the second argument of `construct` is a call-by-name one.

`&&` is, somewhat artificially, sequentially biased towards it's first argument.

Typeful generic names, hopefully, are convient when writing code like 

- `` val `z>-->(y&&x)` = `z>-->y` && `z>-->x` ``.

#### Exercise (hard)














