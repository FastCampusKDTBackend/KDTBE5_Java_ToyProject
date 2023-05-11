package smartstore1;

public class Group {

	private int GeneralHour; // 제너럴 이용시간
	private int GeneralMoney; // 제너럴 결제금액
	private int VipHour; // VIP 이용시간
	private int VipMoney; // VIP 결제금액

// 	Member member = new Member();


	public void setGrade(Member member) {

		if(member.getUseHour()  < GeneralHour  && member.getUseMoney() < GeneralMoney)
			member.grade = Grade.GENERAL;
		else if(member.getUseHour() < VipHour && member.getUseMoney() < VipMoney)
			member.grade = Grade.VIP;
		else
			member.grade = Grade.VVIP;		
	}
	
	public void setGeneral(int i, int j) {
		this.GeneralHour = i;
		this.GeneralMoney = j;
	}
	public void setVip(int i,int j) {
		this.VipHour = i;
		this.VipMoney = j;
	}

	public int getGeneralHour() {
		return GeneralHour;
	}
}