package base

/**
  * Created by Abhishek on 9/3/2017.
  */
object Main {
  def main(args: Array[String]): Unit = {
    val x = Variable("x")
    val y = Variable("y")
    val t = Variable("t")
    val xt = Term(x, t)
    val tx = Term(Value(2), t, x)
    val yt = Term(Value(3), y, t)
    val e = new Expression(List(Term(Value(2), t, x, y), Term(x), Term(y, t), Term(Value(3)), Term(x,t), Term(Value(9)), Term(Value(8)), Term(Value(3), y, x, t)))
    System.out.println(e)
    System.out.println(e reduceValuedTerms() reduceSimilarTerms())
    System.out.println(xt equivalentTo tx)
    System.out.println(yt filterScalar (Value, default = true))
    System.out.println(yt mergeWith tx)
    println(yt.derivativeWRT(t))
  }
}
