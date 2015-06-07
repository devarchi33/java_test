package skyfly33.jedis.keymaker;

public interface KeyMaker {

	/**
	 * 키 생성기로 부터 키를 가져온다.
	 * @return 만들어진 키
	 */
	public String keyMaker();
}
