/**
  * Created by Abhishek on 9/3/2017.
  */
class Value(private val value: Double) extends Scalar(1){
  def getValue:Double = value
  override def toString: String = String.valueOf(value)

  override def equivalentTo(s: Scalar): Boolean = s match {
    case (_: Value) => true
    case _ => false
  }
}
object Value{
  def apply(l: Double): Value = new Value(l)
  def apply(): Value = Value(0.0)
}