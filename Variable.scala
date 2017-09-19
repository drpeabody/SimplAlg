/**
  * Created by Abhishek on 9/3/2017.
  */
class Variable(private val name: String) extends Scalar(1){
  def getName: String = name
  override def toString: String = name

  override def equivalentTo(s: Scalar): Boolean = s match{
    case (x: Variable) => x.getName.equals(getName)
    case _ => false
  }
}
object Variable{
  def apply(name: String): Variable = new Variable(name)
}
