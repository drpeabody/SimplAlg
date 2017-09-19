/**
  * Created by Abhishek on 9/3/2017.
  */
object Main {
  def main(args: Array[String]): Unit = {
    val x = Variable("x")
    val y = Variable("y")
    val t = Variable("t")
    val xt = Term(List(x, t))
    val tx = Term(List(Value(2), t, x))
    val yt = Term(List(Value(3), y, t))
    val e = Expression(List(List(Value(2), t, x, y), List(x), List(y, t), List(Value(3)), List(x,t), List(Value(9)), List(Value(8)), List(Value(3), y, x, t)))
    System.out.println(e)
    System.out.println(e reduceValuedTerms() reduceSimilarTerms())
    System.out.println(xt equivalentTo tx)
    System.out.println(yt filterScalar (Value, default = true))
    System.out.println(yt mergeWith tx)
  }
}
