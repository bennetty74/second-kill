package exercise.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

	public static void main(String[] args) {

		Logger logger = LoggerFactory.getLogger(LogTest.class);

		for (int i = 0; i < 2; i++) {
			/**
			 * 记录日志的标准方式
			 * 如下
			 * 使用system是一种糟糕的方式
			 */
			logger.info("This is a {} test", i);
			logger.warn("This is a {} test", i);
			logger.error("This is a {} test", i);
			/**
			 * 对于debug和trace 使用如上的方式无法在run的console查看
			 */
			
			logger.info("This is a person {}",new Person().setName("BoLiu").setAge("13"));
			/**
			 * 其他省略
			 */
			
			
			try {
				throw new NullPointerException("Null Pointer");
			}catch (Exception e) {
				/**
				 * 正确的异常日志打印方式
				 */
				logger.error("This is a error",e);
			}
		}
	}

}

class Person{
	
	private String name;
	
	private String age;

	public String getName() {
		return name;
	}

	public Person setName(String name) {
		this.name = name;
		return this;
	}

	public String getAge() {
		return age;
	}

	public Person setAge(String age) {
		this.age = age;
		return this;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
}
