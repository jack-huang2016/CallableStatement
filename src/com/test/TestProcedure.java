package com.test;

import java.sql.CallableStatement;
import java.sql.Connection;

import org.junit.Test;

import com.utils.JDBCUtuls;

import oracle.jdbc.internal.OracleTypes;

public class TestProcedure {
	
/*	create or replace procedure queryempinform(eno in number,
            pename out varchar2,
            psal out number,
            pjob out varchar2)*/
	
	
	@Test
	public void testProcedure(){
		String sql = "call queryempinform(?,?,?,?)";
		Connection conn = null;
		CallableStatement call  = null;
		
		try {
			conn = JDBCUtuls.getConnection();
			call = conn.prepareCall(sql);
			//对于in 参数 ，赋值
			call.setInt(1, 7839);
			//对于out参数，申明
			call.registerOutParameter(2, OracleTypes.VARCHAR);
			call.registerOutParameter(3, OracleTypes.NUMBER);
			call.registerOutParameter(4, OracleTypes.VARCHAR);
			
			//执行调用
			call.execute();
			
			//取出结果
			String name = call.getString(2);
			double sal = call.getDouble(3);
			String job = call.getString(4);
			System.out.println(name+"\t"+sal+"\t"+job);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			JDBCUtuls.release(conn, call, null);
		}
	}
}
