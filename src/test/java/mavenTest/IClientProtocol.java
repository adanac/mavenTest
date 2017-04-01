package mavenTest;

public interface IClientProtocol extends IProtocol {

	int METHOD_START = 0;

	@AnnatDemo(METHOD_START)
	public String say(String person);
}