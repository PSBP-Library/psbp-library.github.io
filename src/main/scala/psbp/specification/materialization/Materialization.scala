package psbp.specification.materialization

trait Materialization[>-->[- _, + _], -Z, +Y]:

  // declared

  val materialize: (Unit >--> Unit) => Z => Y

  // defined extensions

  extension (`u>-->u`: Unit >--> Unit) def materialized: Z => Y =
    materialize(`u>-->u`)  