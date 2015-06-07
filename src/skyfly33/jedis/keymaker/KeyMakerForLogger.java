package skyfly33.jedis.keymaker;

public class KeyMakerForLogger implements KeyMaker {

	private static final String KEY_WAS_LOG = "was:log:list";
	private final String serverId;

	public KeyMakerForLogger(String serverId) {
		this.serverId = serverId;
	}

	@Override
	public String keyMaker() {
		// TODO Auto-generated method stub

		return KeyMakerForLogger.KEY_WAS_LOG + this.serverId;
	}

	/**
	 * 서버 아이디를 조회한다
	 * 
	 * @return 서버 아이디
	 */
	public String getServerId() {
		return this.serverId;
	}
}
