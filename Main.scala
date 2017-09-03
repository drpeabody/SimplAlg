/**
  * Created by Abhishek on 9/3/2017.
  */
object Main {
  def main(args: Array[String]): Unit = {
    val x = new Variable("x")
    val y = new Variable("y")
    val t = new Variable("t")
    val e = new Expression(List(List(x,y), List(y,t), List(x,t)))
    System.out.println(e)
  }
}
