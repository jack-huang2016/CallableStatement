package com.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import org.junit.Test;

import com.utils.JDBCUtuls;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.internal.OracleCallableStatement;

public class TestCursor {

	
/*	CREATE OR REPLACE 
	PACKAGE MYPACKAGE AS 

	    type empcursor is ref cursor;
	    procedure queryEmpList(dno in number,empList out empcursor);

	END MYPACKAGE;*/
	
	@Test
	public void testCursor(){
		String sql = "{call mypackage.queryEmpList(?,?)}";
		Connection conn = null;
		CallableStatement call = null;
		ResultSet rs = null;
		
		try {
				conn = JDBCUtuls.getConnection();
				call = conn.prepareCall(sql);
				call.setInt(1,20 );
				call.registerOutParameter(2,OracleTypes.CURSOR);
				call.execute();
				//ȡ���ò���������Ա������Ϣ
				rs = ((OracleCallableStatement)call).getCursor(2);
				while(rs.next()){
					//ȡ����Ա����Ա���ţ�������нˮ��ְλ
					int empno = rs.getInt("empno");
					String name = rs.getString("ename");
					double salary = rs.getDouble("sal");
					String job = rs.getString("job");
					System.out.println(empno+"\t" + name+"\t"+salary+"\t"+job);
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtuls.release(conn, call, rs);
		}
	}
}
