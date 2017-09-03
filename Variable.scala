/**
  * Created by Abhishek on 9/3/2017.
  */
class Variable(private val name: String) extends Scalar(1){
  def getName: String = name
  override def toString: String = name
}
