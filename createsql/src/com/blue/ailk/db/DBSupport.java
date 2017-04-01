package com.blue.ailk.db;

import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.jdbc.OracleConnection;

import com.blue.core.exception.BaseException;

public abstract class DBSupport {
	DBParameter paramter;

	public DBParameter getParamter() {
		return paramter;
	}

	public void setParamter(DBParameter paramter) {
		this.paramter = paramter;
	}

	public DBSupport(IParameter paramter, Object o) {
		this.setParamter((DBParameter) paramter);
		open();
		excute(o);
		close();
	}

	public DBSupport() {
		open();
		excute();
		close();
	}

	OracleConnection conn;

	public OracleConnection getConn() {
		return conn;
	}

	public void setConn(OracleConnection conn) {
		this.conn = conn;
	}

	/**
	 * 获得数据连接
	 * 
	 * @return
	 */
	public void open() {
		OracleConnection conn = null;

		try {
			Class.forName(paramter.getDriverClass());
			conn = (OracleConnection) DriverManager.getConnection(paramter.getJdbcUrl(),
					paramter.getUser(),
					paramter.getPassword());
		} catch (ClassNotFoundException e) {
			throw new BaseException(e);
		} catch (SQLException e) {
			throw new BaseException(e);
		}
		this.setConn(conn);
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param conn
	 */
	public void close() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new BaseException(e);
		}
	}

	abstract protected void excute(Object o);

	abstract protected void excute();
}
