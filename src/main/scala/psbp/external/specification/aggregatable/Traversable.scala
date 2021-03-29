package psbp.external.specification.aggregatable

import scala.language.postfixOps

import psbp.external.specification.program.Program

import psbp.external.specification.recursive.{ Recursive }

trait Traversable[A[+ _], >-->[- _, + _]: Program]: //  extends InitialTraverser[>-->]:

  // declared

  def traverse[Z, Y]: (Z >--> Y) => A[Z] >--> A[Y]

  // defined

  private[psbp] def recurse[R[+_[+ _]]: [R[+_[+ _]]] =>> Recursive[R, >-->], Y]: (A[Y] >--> Y) => (R[A] >--> Y) =
    `a[y]>-->y` =>
      val recursive: Recursive[R, >-->] = summon[Recursive[R, >-->]]
      import recursive.{ `r[a]>-->a[r[a]]` }

      // val recurseAndThenTraverse: (A[Y] >--> Y) => (A[R[A]] >--> A[Y])= recurse andThen traverse

      // lazy val `a[r[a]]>-->a[y]`: A[R[A]] >--> A[Y] = (recurse andThen traverse)(`a[y]>-->y`)

      `r[a]>-->a[r[a]]` >--> (recurse andThen traverse)(`a[y]>-->y`) >--> `a[y]>-->y`

