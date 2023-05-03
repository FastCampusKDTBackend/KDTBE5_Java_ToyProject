package smartstore1;


public class Member {
	
	private String name;
	private String id;
	private int useHour;
	private int useMoney;	
	Grade grade = Grade.GENERAL; // 초기 설정은 general
	

	//게터 세터 투스트링
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getUseHour() {
		return useHour;
	}
	public void setUseHour(int useHour) {
		this.useHour = useHour;
	}
	public int getUseMoney() {
		return useMoney;
	}
	public void setUseMoney(int useMoney) {
		this.useMoney = useMoney;
	}

	
	@Override
	public String toString() {
		return " ===========\n이름=" + name + "\n아이디=" + id + "\n이용시간=" + useHour + "\n이용금액=" + useMoney + "\n등급="
				+ grade + "\n===========\n";
	}


}
