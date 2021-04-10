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

Names like `` `z>-->z` `` can be thought of as *typeful generic names for programs*.

After all there is only one meaningful *generic program* of type `Z >--> Z` *for all types* `Z` transforming its argument to a result, doing nothing else.

So why not giving it the name `` `z>-->z` ``?

`Identity` also defines some useful synonyms of `` `z>-->z` ``, especially for dealing with other types, like the name `` `y>-->y` `` for dealing with type `Y`.

### `Functional`

```scala
package psbp.external.specification.program

import psbp.external.specification.function.`z=>z`

trait Functional[>-->[- _, + _]] 
  extends Identity[>-->]:

  // declared

  private[psbp] def toProgram[Z, Y]: (Z => Y) => Z >--> Y

  // defined

  override def `z>-->z`[Z]: Z >--> Z =
    toProgram(`z=>z`)  

  // defined extensions

  extension [Z, Y] (`z=>y`: Z => Y) def asProgram: Z >--> Y =
    toProgram(`z=>y`)
```

where

```scala
package psbp.external.specification.function

// functional

def `z=>z`[Z]: Z => Z = 
  z =>
    z 

// ...    
```

is a function utility

`Functional` specifies that *functions can be used as programs*.

Names like `` `z=>y` `` and `` `z=>y` `` can be thought of as *typeful generic names for functions*.

After all there is only one meaningful *generic function* of type `Z => Z` *for all types* `Z` transforming its argument to a result, and doing nothing else.

So why not giving it the name `` `z=>z` ``?

The member `asProgram` is an `extension` that can be used as postfix operator.

The full power that comes with the `PSBP` library is not available for functions that are used as programs using `asProgram`, so, which functions to use as programs using `asProgram` is an important choice.

#### Exercise (easy)

*Define a generic function of type* `Z => Z` *other than the identity function* `z=>z`.

#### Solution

```scala
package exercises.specification.function

def genericFunctionOfType_Z2Z[Z]: Z => Z =
  z =>
    println("I am a generic function of type Z => Z")
    z
```

#### Exercise (hard)

*Define a generic program of type* `Z >-> Z` *other than the identity program* `z>-->z`.

*Hint*

See [Context Bounds](https://dotty.epfl.ch/docs/reference/contextual/context-bounds.html) for context bound syntax, and use it for `>-->[- _, + _]` with `Functional`

#### Solution

```scala
package exercises.specification.functional

import scala.language.postfixOps

import psbp.external.specification.program.Functional

import exercises.specification.function

def genericProgramOfType_Z2Z[Z, >-->[- _, + _]: Functional]: Z >--> Z =
  function.genericFunctionOfType_Z2Z asProgram
```

Note that the extension `asProgram` is available *without* `import`ing it using a `summon`ed `Functional[>-->]`.

#### Exercise (moderate)

*define the previous generic program of type* `Z >-> Z` using `toProgram`

*Hint*

See [Using Clausees](https://dotty.epfl.ch/docs/reference/contextual/using-clauses.html) for `summon` syntax.

Read the note following the previous exercise.

Note that `toProgram` is `private[psbp]`

#### Solution

```scala
package psbp.exercises.specification.functional

import scala.language.postfixOps

import psbp.external.specification.program.Functional

import exercises.specification.function

def genericProgramOfType_Z2Z[Z, >-->[- _, + _]: Functional]: Z >--> Z =

  val functional: Functional[>-->] = 
    summon[Functional[>-->]]
  import functional.toProgram

  toProgram(function.genericFunctionOfType_Z2Z)
```

Note the change of the package name to make the `private[psbp]` method accessible.

Although this is possible itis not considered to be good practice at all.

`private[psbp]` members are for library designers.

The extension `asProgram` is available for application developers.




