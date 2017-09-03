/**
  * Created by Abhishek on 9/3/2017.
  */
class Value(private val value: Double) extends Scalar(1){
  def getValue:Double = value
  override def toString: String = String.valueOf(value)
}
