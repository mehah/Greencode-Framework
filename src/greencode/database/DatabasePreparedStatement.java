package greencode.database;

import greencode.kernel.Console;
import greencode.kernel.GreenCodeConfig;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public final class DatabasePreparedStatement implements PreparedStatement {
	private final PreparedStatement ps;
	
	public DatabasePreparedStatement(PreparedStatement s) { this.ps = s; }
		
	public ResultSet executeQuery() throws SQLException {
		try {
			ResultSet s = ps.executeQuery();
			
			if(GreenCodeConfig.DataBase.showResultQuery) {
				String queryString = ps.toString();
				Console.log(queryString.substring(queryString.indexOf(':')+2));
			}
			
			return s;
		} catch (SQLException e) {
			String queryString = ps.toString();
			throw new SQLException(e.getMessage()+" : "+queryString.substring(queryString.indexOf(':')+2));
		}
	}

	public ResultSet executeQuery(String sql) throws SQLException {
		if(GreenCodeConfig.DataBase.showResultQuery)
			Console.log(sql);
		
		return ps.executeQuery(sql);
	}

	public int executeUpdate(String sql) throws SQLException {
		if(GreenCodeConfig.DataBase.showResultQuery)
			Console.log(sql);
		
		return ps.executeUpdate(sql);
	}

	public void close() throws SQLException { ps.close(); }
	
	public int getMaxFieldSize() throws SQLException { return ps.getMaxFieldSize(); }

	public void setMaxFieldSize(int max) throws SQLException { ps.setMaxFieldSize(max); }

	public int getMaxRows() throws SQLException { return ps.getMaxRows(); }

	public void setMaxRows(int max) throws SQLException { ps.setMaxRows(max); }

	public void setEscapeProcessing(boolean enable) throws SQLException { ps.setEscapeProcessing(enable); }

	public int getQueryTimeout() throws SQLException { return ps.getQueryTimeout(); }

	public void setQueryTimeout(int seconds) throws SQLException { ps.setQueryTimeout(seconds); }

	public void cancel() throws SQLException { ps.cancel(); }

	public SQLWarning getWarnings() throws SQLException { return ps.getWarnings(); }

	public void clearWarnings() throws SQLException { ps.clearWarnings(); }

	public void setCursorName(String name) throws SQLException { ps.setCursorName(name); }

	public boolean execute(String sql) throws SQLException {
		if(GreenCodeConfig.DataBase.showResultQuery)
			Console.log(sql);
		
		return ps.execute(sql);
	}

	public ResultSet getResultSet() throws SQLException { return ps.getResultSet(); }

	public int getUpdateCount() throws SQLException { return ps.getUpdateCount(); }

	public boolean getMoreResults() throws SQLException { return ps.getMoreResults(); }

	public void setFetchDirection(int direction) throws SQLException { ps.setFetchDirection(direction); }

	public int getFetchDirection() throws SQLException { return ps.getFetchDirection(); }

	public void setFetchSize(int rows) throws SQLException { ps.setFetchSize(rows); }

	public int getFetchSize() throws SQLException { return ps.getFetchSize(); }

	public int getResultSetConcurrency() throws SQLException { return ps.getResultSetConcurrency(); }

	public int getResultSetType() throws SQLException { return ps.getResultSetType(); }

	public void addBatch(String sql) throws SQLException { ps.addBatch(sql); }

	public void clearBatch() throws SQLException { ps.clearBatch(); }

	public int[] executeBatch() throws SQLException { return ps.executeBatch(); }

	public Connection getConnection() throws SQLException { return ps.getConnection(); }

	public boolean getMoreResults(int current) throws SQLException { return ps.getMoreResults(); }

	public ResultSet getGeneratedKeys() throws SQLException { return ps.getGeneratedKeys(); }

	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		if(GreenCodeConfig.DataBase.showResultQuery)
			Console.log(sql);
		
		return ps.executeUpdate(sql, autoGeneratedKeys);
	}

	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		if(GreenCodeConfig.DataBase.showResultQuery)
			Console.log(sql);
		
		return ps.executeUpdate(sql, columnIndexes);
	}

	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		if(GreenCodeConfig.DataBase.showResultQuery)
			Console.log(sql);
		
		return ps.executeUpdate(sql, columnNames);
	}

	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		if(GreenCodeConfig.DataBase.showResultQuery)
			Console.log(sql);		
		
		return ps.execute(sql, autoGeneratedKeys);
	}

	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		if(GreenCodeConfig.DataBase.showResultQuery)
			Console.log(sql);
		
		return ps.execute(sql, columnIndexes);
	}

	public boolean execute(String sql, String[] columnNames) throws SQLException {
		if(GreenCodeConfig.DataBase.showResultQuery)
			Console.log(sql);
		
		return ps.execute(sql, columnNames);
	}

	public int getResultSetHoldability() throws SQLException { return ps.getResultSetHoldability(); }

	public boolean isClosed() throws SQLException { return ps.isClosed(); }

	public void setPoolable(boolean poolable) throws SQLException { ps.setPoolable(poolable); }

	public boolean isPoolable() throws SQLException { return ps.isPoolable(); }

	public <T> T unwrap(Class<T> iface) throws SQLException { return ps.unwrap(iface); }

	public boolean isWrapperFor(Class<?> iface) throws SQLException { return ps.isWrapperFor(iface); }

	public int executeUpdate() throws SQLException {
		int res = 0;
		try {
			res = ps.executeUpdate();
			
			if(GreenCodeConfig.DataBase.showResultQuery) {
				String queryString = ps.toString();
				Console.log(queryString.substring(queryString.indexOf(':')+2));
			}
		} catch (SQLException e) {
			String queryString = ps.toString();
			throw new SQLException(e.getMessage()+" : "+queryString.substring(queryString.indexOf(':')+2));
		}
		
		return res;
	}

	public void setNull(int parameterIndex, int sqlType) throws SQLException { ps.setNull(parameterIndex, sqlType); }

	public void setBoolean(int parameterIndex, boolean x) throws SQLException { ps.setBoolean(parameterIndex, x); }

	public void setByte(int parameterIndex, byte x) throws SQLException { ps.setByte(parameterIndex, x); }

	public void setShort(int parameterIndex, short x) throws SQLException { ps.setShort(parameterIndex, x); }

	public void setInt(int parameterIndex, int x) throws SQLException { ps.setInt(parameterIndex, x); }

	public void setLong(int parameterIndex, long x) throws SQLException { ps.setLong(parameterIndex, x); }

	public void setFloat(int parameterIndex, float x) throws SQLException { ps.setFloat(parameterIndex, x); }

	public void setDouble(int parameterIndex, double x) throws SQLException { ps.setDouble(parameterIndex, x); }

	public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException { ps.setBigDecimal(parameterIndex, x); }

	public void setString(int parameterIndex, String x) throws SQLException { ps.setString(parameterIndex, x); }

	public void setBytes(int parameterIndex, byte[] x) throws SQLException { ps.setBytes(parameterIndex, x); }

	public void setDate(int parameterIndex, Date x) throws SQLException { ps.setDate(parameterIndex, x); }

	public void setTime(int parameterIndex, Time x) throws SQLException { ps.setTime(parameterIndex, x); }

	public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException { ps.setTimestamp(parameterIndex, x); }

	public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException { ps.setAsciiStream(parameterIndex, x, length); }

	@Deprecated
	public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException { ps.setUnicodeStream(parameterIndex, x, length); }

	public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException { setBinaryStream(parameterIndex, x, length); }

	public void clearParameters() throws SQLException { ps.clearParameters(); }

	public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException { ps.setObject(parameterIndex, x, targetSqlType); }

	public void setObject(int parameterIndex, Object x) throws SQLException { ps.setObject(parameterIndex, x); }

	public boolean execute() throws SQLException { return ps.execute(); }

	public void addBatch() throws SQLException { ps.addBatch(); }

	public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException { ps.setCharacterStream(parameterIndex, reader, length); }

	public void setRef(int parameterIndex, Ref x) throws SQLException { ps.setRef(parameterIndex, x); }

	public void setBlob(int parameterIndex, Blob x) throws SQLException { ps.setBlob(parameterIndex, x); }

	public void setClob(int parameterIndex, Clob x) throws SQLException { ps.setClob(parameterIndex, x); }

	public void setArray(int parameterIndex, Array x) throws SQLException { ps.setArray(parameterIndex, x); }

	public ResultSetMetaData getMetaData() throws SQLException { return ps.getMetaData(); }

	public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException { ps.setDate(parameterIndex, x, cal); }

	public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException { ps.setTime(parameterIndex, x, cal); }

	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException { ps.setTimestamp(parameterIndex, x, cal); }

	public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException { ps.setNull(parameterIndex, sqlType, typeName); }

	public void setURL(int parameterIndex, URL x) throws SQLException { ps.setURL(parameterIndex, x); }

	public ParameterMetaData getParameterMetaData() throws SQLException { return ps.getParameterMetaData(); }

	public void setRowId(int parameterIndex, RowId x) throws SQLException { ps.setRowId(parameterIndex, x); }

	public void setNString(int parameterIndex, String value) throws SQLException { ps.setNString(parameterIndex, value); }

	public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException { ps.setNCharacterStream(parameterIndex, value, length); }

	public void setNClob(int parameterIndex, NClob value) throws SQLException { ps.setNClob(parameterIndex, value); }

	public void setClob(int parameterIndex, Reader reader, long length) throws SQLException { ps.setClob(parameterIndex, reader, length); }

	public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException { ps.setBlob(parameterIndex, inputStream, length); }

	public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException { ps.setNClob(parameterIndex, reader, length); }

	public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException { ps.setSQLXML(parameterIndex, xmlObject); }

	public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException { ps.setObject(parameterIndex, x, targetSqlType, scaleOrLength); }

	public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException { ps.setAsciiStream(parameterIndex, x, length); }

	public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException { ps.setBinaryStream(parameterIndex, x, length); }

	public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException { ps.setCharacterStream(parameterIndex, reader, length); }

	public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException { ps.setAsciiStream(parameterIndex, x); }

	public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException { ps.setBinaryStream(parameterIndex, x); }

	public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException { ps.setCharacterStream(parameterIndex, reader); }

	public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException { ps.setNCharacterStream(parameterIndex, value); }

	public void setClob(int parameterIndex, Reader reader) throws SQLException { ps.setClob(parameterIndex, reader); }

	public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException { ps.setBlob(parameterIndex, inputStream); }

	public void setNClob(int parameterIndex, Reader reader) throws SQLException { ps.setNClob(parameterIndex, reader); }
}