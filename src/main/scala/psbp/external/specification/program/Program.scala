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
  extends Functional[>-->]
  with Classification[>-->]
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

    // todo maybe outside of trait later just like with reading and writing    
    extension [Z, Y] (program: Z >--> Y) 
      def toMainWith(
        producer: Unit >--> Z
        , consumer: (Z && Y) >--> Unit
      ): Unit >--> Unit =
        producer
          >--> {
            Let { 
              program
            } In { 
              consumer 
            }
          }

    // extra

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

def toMain[
  Z, Y
  , >-->[- _, + _]: Program
](producer: Unit >--> Z
  , program: => Z >--> Y
  , consumer: => (Z && Y) >--> Unit
): Unit >--> Unit =

  val program_ : Program[>-->] = 
    summon[Program[>-->]]
  import program_.Let  

  producer
    >--> {
      Let { 
        program
      } In { 
        consumer 
      }
    }