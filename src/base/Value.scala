package base

/**
  * Created by Abhishek on 9/3/2017.
  */
class Value(private val value: Double) extends Scalar with Differentiable{
  def getValue:Double = value
  override def toString: String = String.valueOf(value)

  def +(v: Value): Value = return Value(v.getValue + value)
  def -(v: Value): Value = return Value(v.getValue - value)
  def *(v: Value): Value = return Value(v.getValue * value)
  def /(v: Value): Value = return Value(v.getValue / value)

  override def equivalentTo(s: Scalar): Boolean = s match {
    case (_: Value) => true
    case _ => false
  }

  override def derivativeWRT(x: Variable): Scalar = return Value(0.0)

  override def productWith(t: Term): Term = Term(t.getCoeff * this, t.getFactors)
}
object Value{
  def apply(l: Double): Value = new Value(l)
  def apply(): Value = Value(0.0)
}