package base

class Derivative(private val y: Variable, private val x: Variable) extends Scalar{
  //dy/dx
  override def toString: String = return "(d" + y.toString + "/d" + x.toString + ")"
  override def equivalentTo(s: Scalar): Boolean = s match {
    case (d: Derivative) => (d.y equals y) && (d.x equals x)
    case _ => false
  }

  override def productWith(t: Term): Term = Term(t.getCoeff, this :: t.getFactors)
}
object Derivative{
  def apply(y:Variable, x: Variable): Derivative = new Derivative(y, x)
}
