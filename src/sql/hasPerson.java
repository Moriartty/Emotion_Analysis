package sql;

import java.sql.Connection;

public class hasPerson {
	public static int HasPerson(Connection dbConn,String name){
		int has=0;
		try {
			has = hasTable.hastable(dbConn,"Personinfo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(has==1){
			String recode[]=getData.GetPerson_name();
			for(int i=0;recode[i]!=null;i++){        //������Ҫһ���õ��ַ��������㷨
				if(name.equals(recode[i]))
					return 1;
			}
		}
		else {
			return 0;
		}
		return 0;
	}

}
