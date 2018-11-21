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
			//�������Ӵ���statement
			call = conn.prepareCall(sql);
			//�����������������
			call.registerOutParameter(1, OracleTypes.NUMBER);
			//���������������ֵ
			call.setInt(2, 7839);
			//ִ�е���
			call.execute();
			//ȡ��������Ľ��
			double income = call.getDouble(1);
			System.out.println("��Ա�����������ǣ�"+income);
		} catch (Exception e) {
		    e.printStackTrace();
		}finally {
			JDBCUtuls.release(conn, call, null);
		}
	}
	
}
