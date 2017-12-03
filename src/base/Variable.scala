package base

/**
  * Created by Abhishek on 9/3/2017.
  */
class Variable(private val name: String) extends Scalar with Differentiable {
  def getName: String = name
  override def toString: String = name

  override def equivalentTo(s: Scalar): Boolean = s match{
    case (x: Variable) => x.getName.equals(getName)
    case _ => false
  }

  override def derivativeWRT(x: Variable): Scalar = {
    if(x.name equals name) Value (1) else Derivative(this, x)
  }

  override def productWith(t: Term): Term = Term(t.getCoeff, this :: t.getFactors)
}
object Variable{
  def apply(name: String): Variable = new Variable(name)
}
