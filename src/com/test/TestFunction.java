package com.test;

import java.sql.CallableStatement;
import java.sql.Connection;

import org.junit.Test;

import com.utils.JDBCUtuls;

import oracle.jdbc.internal.OracleTypes;

public class TestFunction {

/*	create or replace function queryempincome(eno in number)
	return number*/
	
	
	@Test
	public void testFunction(){
		//{?=call <procedure-name>[(<arg1>,<arg2>,...)]}
		String sql = "{?=call queryempincome(?)}";
		Connection conn  = null;
		CallableStatement call = null;
		
		try {
			conn = JDBCUtuls.getConnection();
			//基于连接创建statement
			call = conn.prepareCall(sql);
			//对于输出参数，申明
			call.registerOutParameter(1, OracleTypes.NUMBER);
			//对于输入参数，赋值
			call.setInt(2, 7839);
			//执行调用
			call.execute();
			//取出年收入的结果
			double income = call.getDouble(1);
			System.out.println("该员工的年收入是："+income);
		} catch (Exception e) {
		    e.printStackTrace();
		}finally {
			JDBCUtuls.release(conn, call, null);
		}
	}
	
}
