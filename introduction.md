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

# Introduction

## introduction

This introduction goes for *simplicity*, giving up a bit of *soundness* and a lot of *completeness*.

So, please, read this introduction with the statement above in mind.

### vocabulary

Important vocabulary is *emphasized*, and, sometimes, when *used for the first time*, *explained* as well.

The word *vocabulary* itself is used both for *words* (*nouns*, *verbs*, ...) and *partial sentenses*.

Vocabulary is *context sensitive*.

A course, like this `PSBP` course, comes with its own vocabulary context.

A section, like this introduction section, also comes with its own vocabulary context.

Trying to *define* a vocabulary that is difficult, but doing so is better than not trying to define any vocabulary at all.

Trying to consistently *use* the defined vocabulary is, perhaps, even more difficult.

For example, going for simplicity in an introduction section may imply that the vocabulary that is used in it may slightly differ from the vocabulary that is used in other sections.

### main criterion for vocabulary choice

The main *criterion* for vocabulary choice is *programmer friendliness*.

As such the vocabulary that is used in this course may differ from the vocabulary that is used in an *academic* course.

Let's start setting up the vocabulary scene ... .

### specification and implementation

A *specification* describes *requirements* to be *satified* by *implementations*. 

Note that the statement above is a fairly general one.

The requirements of a specification are *declarations* that are satisfied by corresponding *definitions* of implementations.

Note that the statement above is already a more specific one.

A specification *classifies* implementations according to what kind of *type* they are.

A specification may, itself, optionally, have *default definitions*, defined in terms of its declarations. 

An implementation may, optionally have *overriding definitions*, redefining some of those default definitions.

Note that the statements above are even more specific ones.

Rephrased somewhat differently, a specification defines a *set of types*.

Also note that the informal vocabulary that is defined and used in the statements above is programming language independent.

Some programming languages, most notably `Haskell`, but also `Scala`, formally support *type classes*.

The correspondence between
 
- on the one hand, the informal, programming language independent, concept *set of types* 

and

- on the other hand, the formal, programming language dependent, concept *type class* 

is, of course, an intentional one.

### programming languages

Programming languages have *values*, *expressions* and *functions*.

Values are *primitive expressions*.

Expressions are components.

Starting from primitive expressions, expression fragments can be plugged into expression templates to obtain composite expressions.

Functions applied to expressions are expressions, referred to as *function application expression*.

### functional programming languages

*Functional programming languages* are programming languages having *function-valued expressions* 

Functions are values, referred to as *function-values*, that are primitive function-valued expressions.inform

### typed programming languages

*Typed programming languages* are programming languages where every expression has a *type*.

Note that, in this subsection, the word *type* is used formally.

### typed functional programming languages

*Typed functional programming languages* are functional programming languages that are also typed programming languages.

The types of function-valued expressions are referred to as *function-types*.

### pointfree versus Pointful

Functional programming can be done in a *pointfree* or *pointful* way.

Pointfree functional programming *only* has function-valued expressions, starting from a set of *primitive function-values*.

`FP` is a pointfree functional programming language, referred to as *function-level* programming language. 

### `Scala`

`Scala` is a hybrid programming language that, most notably, is both

- a *typed functional programming language*
- an *object oriented programming language*

As far as `Scala` is an object oriented programming language, it is a *pure* object oriented programming language because everything is an object.

As a consequence, the words *value* and *object* are used interchangably.

As far as `Scala` is a (typed) functional programming language, it is *not* a *pure* (typed) functional programming language because not everything is immutable.

For your information: `Haskell` is a *pure* functional language.

### `trait`'s 

Specifications of `Scala` libraries are typically defined as `trait`'s, referred to as *specification* `traits`*'s*.

The declarations and, optionally, default definitions of the specification `trait`´s are `def` or `type` members.

The definitions and, optionally, overridden definitions of the implementation `given`´s are `def`, `lazy val`, `val` or `type` members.

`Scala`, being a hybrid *typed functional* and *object oriented* programming language, has two kind of `trait`'s.

- `trait`'s that specify *object classes*, whose implementations are `class`es, whose instances are objects, 
- `trait`'s that specify *type classes*, whose implementations are `given`'s,  whose instances are types

It is instructive to 

- think of a `trait` as defining a *set*, and of their *instances* as an *elements* of that set.
- think of a type itself as a `trait`, that is one that specifies an object class.

and, consequently,

- think of a `trait` that specifies a type class as having instances that are `trait`'s that specify an object class that have instances that are objects.

The essence of this thinking experiment is that one can, via instances, 

- go in one step from a `trait` specifying an object class to an object
- go in two steps from a `trait` specifying a type class to an object

### `trait`'s as interfaces

A `trait` is the `Scala` language construct that is used to define an *interface*.

It is commonly considered to be good practice to as *program to an interface*.

In `Scala` this boils down to define `def`'s or `val`'s in terms of members of specification `trait`'s.

It is instructive, if the a specification `trait` is one that defines a type class, to

- think of what has been defined as an *object specification*
- think of using *dependency injection by* `import` of an implementation `given` of the specification `trait` as *implementing* of that object specification so that it becomes an object.

### some analogy

One way to digest all the information above is by comparing it with the picture *Ceci n'est pas une pipe* of *René Magritte*.

See [The Treachery of Images](https://en.wikipedia.org/wiki/The_Treachery_of_Images).

The picture can either be seen as a *specifying a pipe*, so that an instance *is a pipe*.

But here is the thing: there are many different *types* of pipes: *smoking pipes*, *plumbing pipes*, ... .

The picture can also be seen as a *specifying a type of pipes*, so that an instance *is a type of pipes*.

A *type of pipes* itself can be seen as a *specifying pipe of that type*, so that an instance *is a pipe of that type*.

From the second point of view, the type of pipes specified by the picture clearly is *the smoking pipes type*.

So the picture can then be seen as a *specifying the smoking pipes type*, so that an instance *is a smoking pipes type* that can be seen as a *specifying smoking pipes*.

An artist can now create a work of art that is a *smoking pipe specification* that fulfills the requirements of the smoking pipes type as specified by the picture of René Magritte.

Although it may sound somewhat surrealistic (no pun intended), given an implementation of the specification of the picture of René Magritte, the work of art of the artist becomes a smoking pipe. 

### back to `Scala` types and `trait`'s

Note that the word *type*, as far as standing for `Scala` *type* and used in *type class* should be *higher order n-ary type constructor*.

By abuse of notation *higher order n-ary type constructor* will continue to simply be reffered to as *type*.

Specification `trait`´s specifying type classes have a *list of type parameters*.

The implementation `given`´s have a corresponding *list of type arguments*.

It is instructive to

- think of a `specification `trait` specifying a type class as defining a *set of types*.
- think of a corresponding implementation `given` as defining a type to be an *element* of that set.

Note that *set of types* should be *set of sets of types*, corresponding to the list of type parameters.

Note that, in the same way, *defining a type to be an element of that set* should be *defining a set of types to be an element of that set of sets of types*

### the `PSBP` library

The `PSBP` library promotes pointfree functional programming at a more abstract level than the `FP` language does.

All the specifications `trait`’s of the `PSBP` library, also referred to as `PSBP` *specification* `trait`*'s*, specify type classes.

All corresponding implementation `givin`'s, referred to as `PSBP` *implementation* `given`*'s*, define types to belong to those type classes.

### external versus internal

The `PSBP` library strictly separates *external* specifications and implementations from *internal* specifications and implementations. 

External specification `trait`'s form the `PSBP` *application developer interface*, also simply referred to as the external `PSBP` *API*.

External implementation `given`'s can be made available for application developers using *dependency injection by* `import`.

Internal specification `trait`'s form the `PSBP` *library designer interface*, also simply referred to as the internal `PSBP` *API*.

Internal implementation `given`'s can be made available for library designers using *dependency injection by* `import`.

### programming

The external `PSBP` specification `trait`'s specify type classes for modeling the domain of *programming*.

Rephrased somewhat differently, the external `PSBP` API defines a *programming DSL*.

Think about the library as explaining what the *essense of programming* is all about.

By abuse of notation, definitions, `def`'s or `val`'s, that are defined in terms of this programming DSL are referred to as *programs*. 

Note that they should be referred to as a *program specifications* instead.

Correspondingly, by abuse of notation, *program specification implementations* are referred to as *program implementations*. 

Note that they should be referred to as a *programs* instead.

This abuse of notation greatly simplifies the content of the course.

By the way, sorry for overloading the word "program".

The course uses the word "code" for an artifact that is produced by a programmer.

The program type parameter of specification `trait`'s is `>-->[-, + _]`.

As a consequence, programs have type `Z >--> Y`.

Think of a program of type `Z >--> Y` as a *specification* of an *abstraction of a function* of type `Z => Y`.

This allows for more program implementations than only functions.

Note that `>-->[-, + _]` is not a *type* parameter but a *binary type constructor* parameter.

The binary type constructor parameter is *contravariant* in it's first argument and *covariant* in it's second argument.

See [Liskov Substitution Principle](https://en.wikipedia.org/wiki/Liskov_substitution_principle).

Roughly speaking, for programs, this principle is about *requiring less* and *providing more*.

A function of type `Z => Y` *transforms* an *argument* of type `Z` to a *result* of type `Y`, and is supposed to *only* do this, *nothing else*.

Note that, using `Scala`, it is perfectly possible to define functions that do not follow this recommendation.

A program of type `Z >--> Y` specifies transforming an argument of type `Z` to a result of type `Y`, possibly *doing something else* along the way of doing this transformation.

Typically, that *doing something else* consists of *performing side effects*.

Specifying performing side effects along the way of doing something is referred to as specifying doing something *effectfully*.

Side effects can be *external* or *internal*.

- External side effects are useful to deal with such things as files, after all functions are not supposed to interact with the outside world, not useful for dealing with such things as files.
- Internal side effects are useful to deal with such things as randomness, after all functions always transform the same argument to the same result, not useful for dealing with randomness.

Specified side effects are referred to as *effects*.

All the `PSBP` implementation `given`'s of essential `PSBP` specifications `trait`’s are functions.

Internal side effects manifest themselves in the types of those functions.

### programming ingredients

Members of the external `PSBP` specification `trait`'s are referred to as *programming ingredients*.

All the `PSBP` library programming ingredients are pointfree.

Writing code is all about *algorithms* and *data structures*.

See [Algorithms + Data Structures = Programs](https://en.wikipedia.org/wiki/Algorithms_%2B_Data_Structures_%3D_Programs).

Formulated somewhat informally, the basic programming ingredients of the `PSBP` library are

1. *functions can be used as programs*

This programming ingredient is algorithm related.

The library enables functional programming.

Note that functions that are used as programs do not benefit from the full power and flexibility of the library.

2. *programs can be composed sequentially*

This programming ingredient is algorithm related.

3. *programs can be combined to product based composite data structure constructing programs*, or, equivalently, *programs can make use of local values*

This programming ingredient is algorithm + data structure related.

4. *programs can be composed to sum based conditional programs*, or, equivalently, *programs can perform if-then-else logic*

This programming ingredient is algorithm + data structure related.

5. *programs can deal with data structures*, for example, *traverse* them, and *fold* and *unfold* them.

This programming ingredient is algorithm + data structure related. 

In fact, more data structure related as algorithm related.

In practice, programs typically deal with *polynomial recursive data structures*.

### computing

The internal `PSBP` specification `trait`'s specify type classes for modeling the domain of *computing*.

Rephrased somewhat differently, the internal `PSBP` API defines a *computing DSL*. 

Think about the library as explaining what the *essense of computing* is all about.

By abuse of notation, definitions, `def`'s or `val`'s, that are defined in terms of this programming DSL are referred to as *computations*. 

Note that they should be referred to as a *computation specifications* instead.

The computation type parameter of specification `trait`'s is  `C[+ _]`.

As a consequence, computations have type `C[Y]`.

Think of a computation of type `C[Y]` as a *specification* of an *abstraction of an expression* of type `Y`.

This allows for more computation implementations than only expressions.

`C[+ _]` is not a *type parameter* but a *unary type constructor parameter*.

The unary type constructor parameter is *covariant* in it's argument.

See [Liskov Substitution Principle](https://en.wikipedia.org/wiki/Liskov_substitution_principle).

Roughly speaking, for computations, this principle is about *providing more*.

An expression of type `Y` is supposed to *evaluate* to a *result* of type `Y` and is supposed to *only* do this, *nothing else*.

Note that, using `Scala`, it is possible to define expressions that do not follow this recommendation. 

A computation of type `C[Y]`, specifies evaluating an expression to a result of type `Y`, possibly *doing something else* along the way of doing this transformation.

Typically, again, that *doing something else* consists of *performing side effects*.

### computing ingredients

Members of internal `PSBP` specification `trait`'s are referred to as *computing ingredients*.

The `PSBP `library programming ingredients are pointful. 

They do not only involve computations but also results.

Formulated somewhat informally, and replacing evaluating by executing, the basic computing ingredients of the `PSBP` library are

1. *values can be used as computations*

A value trivially executes to itself as a result.

2. *computations can be nested*

The result of executing an inner computation can be used as an argument of a *continuation function* transforming it to a result that is an outer computation to be executed.

Note that the ingredients are not really about algortitms + data structures.

### power of expression

It turns out that the programming ingredients 1. 2. 3. and 4. can be defined in terms of the computing ingredients 1. and 2.

The power of expression of pointful computations at the definition site comes with a complexity price at the use site.

### denotational and operational

It is instructive to think of functions and programs as *development time denotational artifacts*. 

They are artifacts that a developer has in his mind.

Typically they can be given a meaningful name.

It is instructive to think of expressions and computations as *runtime operational artifacts*. 

They are artifacts that a runtime operates on.

Typically they cannot be given a meaningful name.

Compare programming ingredients being defined in terms of computing ingredients with functions being defined using expressions.

The first part of the statement above establishes the link between the denotational world of functions and the operational world of expressions.

The second part of the statement above establishes the link between the denotational world of programs and the operational world of computations.

The `PSBP` library establishes this link *once and for all* rather than *over and over again*.

### setting the scene

All programs of the `PSBP` library are implemented as functions of type `Z => C[Y]` that transform an argument of type `Z` to a *computation result* of type `C[Y]`.

Compare this with functions of type `Z => Y` being defined using `` def z2y[Z, Y](z) = e(z)`` where `e(z)` is an expression of type `Y` that somehow makes use of `z`

See [programFormComputation](https://psbp-library.github.io#programformcomputation).

*Although programs of type* `Z => C[Y]` *can be used in a pointful way, the external `PSBP` API does not allow doing so, forcing application developers to think in a pointfree way.*

### lifting

The internal `PSBP` specification `trait`'s also specify type classes for modeling the domain of *lifing from value-level to computation-level*.

For example

- `n`-ary functions, (for `n = 0, 1, 2, 3, ... `)  can be lifted, referred to as *function lifting*,

- function application can be lifted, referred to as *function application lifting*.

Many more value-level ingredients can be lifted to computation-level ingredients.

In the `PSBP` library, function lifting ingredients are defined using function application lifting ingredients.

See [functionLiftingFromFunctionApplicationLifting](https://psbp-library.github.io#functionliftingfromfunctionapplicationlifting).

In the `PSBP` library, function application lifting ingredients are defined using computing ingredients.

See [functionApplicationLiftingFromComputation](https://psbp-library.github.io#functionapplicationliftingFromcomputation).

Lifting plays, among others, an essential role in the pointful functional programming world when dealing data structures.

It turns out that lifting does not play such an essential role in the pointfree program specification based programming world when dealing data structures.

Nevertheless lifting is part of the `PSBP` library.

### main program

A *main program* is a program of type `Unit >--> Unit`.

A program of type `Z >--> Y` is, somehow, combined with a *producer* of type `Unit >--> Z` and a *consumer* of type `(Z && Y) >--> Unit` to a main program.

Main programs of type `Unit >--> Unit` are *materialized* to functions of type `Z => Y`.

Eventually, materialized implemented main programs are used in `@main` `Scala` code where dependency injection of implementation `given`'s by `import` chooses

- how programs transform their argument to a result and how they perform side effects,
- how main programs are materialized.

All this allows for maximum implementation and materialization flexibility.















