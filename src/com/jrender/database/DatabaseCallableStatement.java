package com.jrender.database;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
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
import java.util.Map;

import com.jrender.kernel.Console;
import com.jrender.kernel.JRenderConfig;

public final class DatabaseCallableStatement implements CallableStatement {
	private final CallableStatement cs;
	private final DatabaseConnection connection;

	DatabaseCallableStatement(DatabaseConnection connection, String sql) throws SQLException {
		this.connection = connection;
		this.cs = connection.getConnection().prepareCall(sql);
	}
	
	DatabaseCallableStatement(DatabaseConnection connection, String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		this.connection = connection;
		this.cs = connection.getConnection().prepareCall(sql, resultSetType, resultSetConcurrency);
	}
	
	DatabaseCallableStatement(DatabaseConnection connection, String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		this.connection = connection;
		this.cs = connection.getConnection().prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	public ResultSet executeQuery() throws SQLException {
		try {
			ResultSet s = cs.executeQuery();

			if (JRenderConfig.Server.DataBase.showResultQuery) {
				String queryString = cs.toString();
				Console.log(queryString.substring(queryString.indexOf(':') + 2));
			}

			return s;
		} catch (SQLException e) {
			this.connection.hasError = true;
			String queryString = cs.toString();
			throw new SQLException(e.getMessage() + " : " + queryString.substring(queryString.indexOf(':') + 2));
		}
	}

	public ResultSet executeQuery(String sql) throws SQLException {
		if (JRenderConfig.Server.DataBase.showResultQuery)
			Console.log(sql);
		
		try {
			return cs.executeQuery(sql);
		} catch (SQLException e) {
			this.connection.hasError = true;
			throw e;
		}
	}

	public int executeUpdate(String sql) throws SQLException {
		if (JRenderConfig.Server.DataBase.showResultQuery)
			Console.log(sql);
		
		try {
			return cs.executeUpdate(sql);
		} catch (SQLException e) {
			this.connection.hasError = true;
			throw e;
		}
	}

	public void close() throws SQLException {
		cs.close();
	}

	public int getMaxFieldSize() throws SQLException {
		return cs.getMaxFieldSize();
	}

	public void setMaxFieldSize(int max) throws SQLException {
		cs.setMaxFieldSize(max);
	}

	public int getMaxRows() throws SQLException {
		return cs.getMaxRows();
	}

	public void setMaxRows(int max) throws SQLException {
		cs.setMaxRows(max);
	}

	public void setEscapeProcessing(boolean enable) throws SQLException {
		cs.setEscapeProcessing(enable);
	}

	public int getQueryTimeout() throws SQLException {
		return cs.getQueryTimeout();
	}

	public void setQueryTimeout(int seconds) throws SQLException {
		cs.setQueryTimeout(seconds);
	}

	public void cancel() throws SQLException {
		cs.cancel();
	}

	public SQLWarning getWarnings() throws SQLException {
		return cs.getWarnings();
	}

	public void clearWarnings() throws SQLException {
		cs.clearWarnings();
	}

	public void setCursorName(String name) throws SQLException {
		cs.setCursorName(name);
	}

	public boolean execute(String sql) throws SQLException {
		if (JRenderConfig.Server.DataBase.showResultQuery)
			Console.log(sql);

		try {
			return cs.execute(sql);
		} catch (SQLException e) {
			this.connection.hasError = true;
			throw e;
		}
	}

	public ResultSet getResultSet() throws SQLException {
		return cs.getResultSet();
	}

	public int getUpdateCount() throws SQLException {
		return cs.getUpdateCount();
	}

	public boolean getMoreResults() throws SQLException {
		return cs.getMoreResults();
	}

	public void setFetchDirection(int direction) throws SQLException {
		cs.setFetchDirection(direction);
	}

	public int getFetchDirection() throws SQLException {
		return cs.getFetchDirection();
	}

	public void setFetchSize(int rows) throws SQLException {
		cs.setFetchSize(rows);
	}

	public int getFetchSize() throws SQLException {
		return cs.getFetchSize();
	}

	public int getResultSetConcurrency() throws SQLException {
		return cs.getResultSetConcurrency();
	}

	public int getResultSetType() throws SQLException {
		return cs.getResultSetType();
	}

	public void addBatch(String sql) throws SQLException {
		cs.addBatch(sql);
	}

	public void clearBatch() throws SQLException {
		cs.clearBatch();
	}

	public int[] executeBatch() throws SQLException {
		return cs.executeBatch();
	}

	public Connection getConnection() throws SQLException {
		return cs.getConnection();
	}

	public boolean getMoreResults(int current) throws SQLException {
		return cs.getMoreResults();
	}

	public ResultSet getGeneratedKeys() throws SQLException {
		return cs.getGeneratedKeys();
	}

	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		if (JRenderConfig.Server.DataBase.showResultQuery)
			Console.log(sql);

		try {
			return cs.executeUpdate(sql, autoGeneratedKeys);
		} catch (SQLException e) {
			this.connection.hasError = true;
			throw e;
		}
	}

	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		if (JRenderConfig.Server.DataBase.showResultQuery)
			Console.log(sql);

		try {
			return cs.executeUpdate(sql, columnIndexes);
		} catch (SQLException e) {
			this.connection.hasError = true;
			throw e;
		}
	}

	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		if (JRenderConfig.Server.DataBase.showResultQuery)
			Console.log(sql);

		try {
			return cs.executeUpdate(sql, columnNames);
		} catch (SQLException e) {
			this.connection.hasError = true;
			throw e;
		}
	}

	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		if (JRenderConfig.Server.DataBase.showResultQuery)
			Console.log(sql);

		try {
			return cs.execute(sql, autoGeneratedKeys);
		} catch (SQLException e) {
			this.connection.hasError = true;
			throw e;
		}
	}

	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		if (JRenderConfig.Server.DataBase.showResultQuery)
			Console.log(sql);

		try {
			return cs.execute(sql, columnIndexes);
		} catch (SQLException e) {
			this.connection.hasError = true;
			throw e;
		}
	}

	public boolean execute(String sql, String[] columnNames) throws SQLException {
		if (JRenderConfig.Server.DataBase.showResultQuery)
			Console.log(sql);

		try {
			return cs.execute(sql, columnNames);
		} catch (SQLException e) {
			this.connection.hasError = true;
			throw e;
		}
	}

	public int getResultSetHoldability() throws SQLException {
		return cs.getResultSetHoldability();
	}

	public boolean isClosed() throws SQLException {
		return cs.isClosed();
	}

	public void setPoolable(boolean poolable) throws SQLException {
		cs.setPoolable(poolable);
	}

	public boolean isPoolable() throws SQLException {
		return cs.isPoolable();
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		return cs.unwrap(iface);
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return cs.isWrapperFor(iface);
	}

	public int executeUpdate() throws SQLException {
		try {
			return cs.executeUpdate();
		} catch (SQLException e) {
			this.connection.hasError = true;
			throw e;
		}
	}

	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		cs.setNull(parameterIndex, sqlType);
	}

	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		cs.setBoolean(parameterIndex, x);
	}

	public void setByte(int parameterIndex, byte x) throws SQLException {
		cs.setByte(parameterIndex, x);
	}

	public void setShort(int parameterIndex, short x) throws SQLException {
		cs.setShort(parameterIndex, x);
	}

	public void setInt(int parameterIndex, int x) throws SQLException {
		cs.setInt(parameterIndex, x);
	}

	public void setLong(int parameterIndex, long x) throws SQLException {
		cs.setLong(parameterIndex, x);
	}

	public void setFloat(int parameterIndex, float x) throws SQLException {
		cs.setFloat(parameterIndex, x);
	}

	public void setDouble(int parameterIndex, double x) throws SQLException {
		cs.setDouble(parameterIndex, x);
	}

	public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
		cs.setBigDecimal(parameterIndex, x);
	}

	public void setString(int parameterIndex, String x) throws SQLException {
		cs.setString(parameterIndex, x);
	}

	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		cs.setBytes(parameterIndex, x);
	}

	public void setDate(int parameterIndex, Date x) throws SQLException {
		cs.setDate(parameterIndex, x);
	}

	public void setTime(int parameterIndex, Time x) throws SQLException {
		cs.setTime(parameterIndex, x);
	}

	public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
		cs.setTimestamp(parameterIndex, x);
	}

	public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
		cs.setAsciiStream(parameterIndex, x, length);
	}

	@Deprecated
	public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
		cs.setUnicodeStream(parameterIndex, x, length);
	}

	public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
		setBinaryStream(parameterIndex, x, length);
	}

	public void clearParameters() throws SQLException {
		cs.clearParameters();
	}

	public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
		cs.setObject(parameterIndex, x, targetSqlType);
	}

	public void setObject(int parameterIndex, Object x) throws SQLException {
		cs.setObject(parameterIndex, x);
	}

	public boolean execute() throws SQLException {
		try {
			return cs.execute();
		} catch (SQLException e) {
			this.connection.hasError = true;
			throw e;
		}
	}

	public void addBatch() throws SQLException {
		cs.addBatch();
	}

	public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
		cs.setCharacterStream(parameterIndex, reader, length);
	}

	public void setRef(int parameterIndex, Ref x) throws SQLException {
		cs.setRef(parameterIndex, x);
	}

	public void setBlob(int parameterIndex, Blob x) throws SQLException {
		cs.setBlob(parameterIndex, x);
	}

	public void setClob(int parameterIndex, Clob x) throws SQLException {
		cs.setClob(parameterIndex, x);
	}

	public void setArray(int parameterIndex, Array x) throws SQLException {
		cs.setArray(parameterIndex, x);
	}

	public ResultSetMetaData getMetaData() throws SQLException {
		return cs.getMetaData();
	}

	public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
		cs.setDate(parameterIndex, x, cal);
	}

	public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
		cs.setTime(parameterIndex, x, cal);
	}

	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
		cs.setTimestamp(parameterIndex, x, cal);
	}

	public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
		cs.setNull(parameterIndex, sqlType, typeName);
	}

	public void setURL(int parameterIndex, URL x) throws SQLException {
		cs.setURL(parameterIndex, x);
	}

	public ParameterMetaData getParameterMetaData() throws SQLException {
		return cs.getParameterMetaData();
	}

	public void setRowId(int parameterIndex, RowId x) throws SQLException {
		cs.setRowId(parameterIndex, x);
	}

	public void setNString(int parameterIndex, String value) throws SQLException {
		cs.setNString(parameterIndex, value);
	}

	public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
		cs.setNCharacterStream(parameterIndex, value, length);
	}

	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		cs.setNClob(parameterIndex, value);
	}

	public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
		cs.setClob(parameterIndex, reader, length);
	}

	public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
		cs.setBlob(parameterIndex, inputStream, length);
	}

	public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
		cs.setNClob(parameterIndex, reader, length);
	}

	public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
		cs.setSQLXML(parameterIndex, xmlObject);
	}

	public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
		cs.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
	}

	public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
		cs.setAsciiStream(parameterIndex, x, length);
	}

	public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
		cs.setBinaryStream(parameterIndex, x, length);
	}

	public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
		cs.setCharacterStream(parameterIndex, reader, length);
	}

	public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
		cs.setAsciiStream(parameterIndex, x);
	}

	public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
		cs.setBinaryStream(parameterIndex, x);
	}

	public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
		cs.setCharacterStream(parameterIndex, reader);
	}

	public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
		cs.setNCharacterStream(parameterIndex, value);
	}

	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		cs.setClob(parameterIndex, reader);
	}

	public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
		cs.setBlob(parameterIndex, inputStream);
	}

	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		cs.setNClob(parameterIndex, reader);
	}

	public Array getArray(int parameterIndex) throws SQLException {
		return cs.getArray(parameterIndex);
	}

	public Array getArray(String parameterName) throws SQLException {
		return cs.getArray(parameterName);
	}

	public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {

		return cs.getBigDecimal(parameterIndex);
	}

	public BigDecimal getBigDecimal(String parameterName) throws SQLException {
		return cs.getBigDecimal(parameterName);
	}

	@Deprecated
	public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
		return cs.getBigDecimal(parameterIndex, scale);
	}

	public Blob getBlob(int parameterIndex) throws SQLException {
		return cs.getBlob(parameterIndex);
	}

	public Blob getBlob(String parameterName) throws SQLException {
		return cs.getBlob(parameterName);
	}

	public boolean getBoolean(int parameterIndex) throws SQLException {
		return cs.getBoolean(parameterIndex);
	}

	public boolean getBoolean(String parameterName) throws SQLException {
		return cs.getBoolean(parameterName);
	}

	public byte getByte(int parameterIndex) throws SQLException {
		return cs.getByte(parameterIndex);
	}

	public byte getByte(String parameterName) throws SQLException {
		return cs.getByte(parameterName);
	}

	public byte[] getBytes(int parameterIndex) throws SQLException {
		return cs.getBytes(parameterIndex);
	}

	public byte[] getBytes(String parameterName) throws SQLException {
		return cs.getBytes(parameterName);
	}

	public Reader getCharacterStream(int parameterIndex) throws SQLException {
		return cs.getCharacterStream(parameterIndex);
	}

	public Reader getCharacterStream(String parameterName) throws SQLException {
		return cs.getCharacterStream(parameterName);
	}

	public Clob getClob(int parameterIndex) throws SQLException {
		return cs.getClob(parameterIndex);
	}

	public Clob getClob(String parameterName) throws SQLException {
		return cs.getClob(parameterName);
	}

	public Date getDate(int parameterIndex) throws SQLException {
		return cs.getDate(parameterIndex);
	}

	public Date getDate(String parameterName) throws SQLException {
		return cs.getDate(parameterName);
	}

	public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
		return cs.getDate(parameterIndex, cal);
	}

	public Date getDate(String parameterName, Calendar cal) throws SQLException {
		return cs.getDate(parameterName, cal);
	}

	public double getDouble(int parameterIndex) throws SQLException {
		return cs.getDouble(parameterIndex);
	}

	public double getDouble(String parameterName) throws SQLException {
		return cs.getDouble(parameterName);
	}

	public float getFloat(int parameterIndex) throws SQLException {
		return cs.getFloat(parameterIndex);
	}

	public float getFloat(String parameterName) throws SQLException {
		return cs.getFloat(parameterName);
	}

	public int getInt(int parameterIndex) throws SQLException {
		return cs.getInt(parameterIndex);
	}

	public int getInt(String parameterName) throws SQLException {
		return cs.getInt(parameterName);
	}

	public long getLong(int parameterIndex) throws SQLException {
		return getLong(parameterIndex);
	}

	public long getLong(String parameterName) throws SQLException {
		return cs.getLong(parameterName);
	}

	public Reader getNCharacterStream(int parameterIndex) throws SQLException {
		return cs.getNCharacterStream(parameterIndex);
	}

	public Reader getNCharacterStream(String parameterName) throws SQLException {
		return cs.getNCharacterStream(parameterName);
	}

	public NClob getNClob(int parameterIndex) throws SQLException {
		return cs.getNClob(parameterIndex);
	}

	public NClob getNClob(String parameterName) throws SQLException {
		return cs.getNClob(parameterName);
	}

	public String getNString(int parameterIndex) throws SQLException {
		return cs.getNString(parameterIndex);
	}

	public String getNString(String parameterName) throws SQLException {
		return cs.getNString(parameterName);
	}

	public Object getObject(int parameterIndex) throws SQLException {
		return cs.getObject(parameterIndex);
	}

	public Object getObject(String parameterName) throws SQLException {
		return cs.getObject(parameterName);
	}

	public Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException {
		return cs.getObject(parameterIndex, map);
	}

	public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
		return cs.getObject(parameterName, map);
	}

	public Ref getRef(int parameterIndex) throws SQLException {
		return cs.getRef(parameterIndex);
	}

	public Ref getRef(String parameterName) throws SQLException {
		return cs.getRef(parameterName);
	}

	public RowId getRowId(int parameterIndex) throws SQLException {
		return cs.getRowId(parameterIndex);
	}

	public RowId getRowId(String parameterName) throws SQLException {
		return cs.getRowId(parameterName);
	}

	public SQLXML getSQLXML(int parameterIndex) throws SQLException {
		return cs.getSQLXML(parameterIndex);
	}

	public SQLXML getSQLXML(String parameterName) throws SQLException {
		return cs.getSQLXML(parameterName);
	}

	public short getShort(int parameterIndex) throws SQLException {
		return cs.getShort(parameterIndex);
	}

	public short getShort(String parameterName) throws SQLException {
		return cs.getShort(parameterName);
	}

	public String getString(int parameterIndex) throws SQLException {
		return cs.getString(parameterIndex);
	}

	public String getString(String parameterName) throws SQLException {
		return cs.getString(parameterName);
	}

	public Time getTime(int parameterIndex) throws SQLException {
		return cs.getTime(parameterIndex);
	}

	public Time getTime(String parameterName) throws SQLException {
		return cs.getTime(parameterName);
	}

	public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
		return cs.getTime(parameterIndex, cal);
	}

	public Time getTime(String parameterName, Calendar cal) throws SQLException {
		return cs.getTime(parameterName, cal);
	}

	public Timestamp getTimestamp(int parameterIndex) throws SQLException {
		return cs.getTimestamp(parameterIndex);
	}

	public Timestamp getTimestamp(String parameterName) throws SQLException {
		return cs.getTimestamp(parameterName);
	}

	public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
		return getTimestamp(parameterIndex, cal);
	}

	public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
		return cs.getTimestamp(parameterName, cal);
	}

	public URL getURL(int parameterIndex) throws SQLException {
		return cs.getURL(parameterIndex);
	}

	public URL getURL(String parameterName) throws SQLException {
		return cs.getURL(parameterName);
	}

	public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
		cs.registerOutParameter(parameterIndex, sqlType);
	}

	public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
		cs.registerOutParameter(parameterName, sqlType);
	}

	public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
		cs.registerOutParameter(parameterIndex, sqlType);
	}

	public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {
		cs.registerOutParameter(parameterIndex, sqlType, typeName);
	}

	public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
		cs.registerOutParameter(parameterName, sqlType, scale);
	}

	public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
		cs.registerOutParameter(parameterName, sqlType, typeName);
	}

	public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
		cs.setAsciiStream(parameterName, x);
	}

	public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
		cs.setAsciiStream(parameterName, x, length);
	}

	public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
		cs.setAsciiStream(parameterName, x, length);
	}

	public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
		cs.setBigDecimal(parameterName, x);
	}

	public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
		cs.setBinaryStream(parameterName, x);
	}

	public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
		cs.setBinaryStream(parameterName, x, length);
	}

	public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
		cs.setBinaryStream(parameterName, x, length);
	}

	public void setBlob(String parameterName, Blob x) throws SQLException {
		cs.setBlob(parameterName, x);
	}

	public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
		cs.setBlob(parameterName, inputStream);
	}

	public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
		cs.setBlob(parameterName, inputStream, length);
	}

	public void setBoolean(String parameterName, boolean x) throws SQLException {
		cs.setBoolean(parameterName, x);
	}

	public void setByte(String parameterName, byte x) throws SQLException {
		cs.setByte(parameterName, x);
	}

	public void setBytes(String parameterName, byte[] x) throws SQLException {
		cs.setBytes(parameterName, x);
	}

	public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
		cs.setCharacterStream(parameterName, reader);
	}

	public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
		cs.setCharacterStream(parameterName, reader, length);
	}

	public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
		cs.setCharacterStream(parameterName, reader, length);
	}

	public void setClob(String parameterName, Clob x) throws SQLException {
		cs.setClob(parameterName, x);
	}

	public void setClob(String parameterName, Reader reader) throws SQLException {
		cs.setClob(parameterName, reader);
	}

	public void setClob(String parameterName, Reader reader, long length) throws SQLException {
		cs.setClob(parameterName, reader, length);
	}

	public void setDate(String parameterName, Date x) throws SQLException {
		cs.setDate(parameterName, x);
	}

	public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
		cs.setDate(parameterName, x, cal);
	}

	public void setDouble(String parameterName, double x) throws SQLException {
		cs.setDouble(parameterName, x);
	}

	public void setFloat(String parameterName, float x) throws SQLException {
		cs.setFloat(parameterName, x);
	}

	public void setInt(String parameterName, int x) throws SQLException {
		cs.setInt(parameterName, x);
	}

	public void setLong(String parameterName, long x) throws SQLException {
		cs.setLong(parameterName, x);
	}

	public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
		cs.setNCharacterStream(parameterName, value);
	}

	public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
		cs.setNCharacterStream(parameterName, value, length);
	}

	public void setNClob(String parameterName, NClob value) throws SQLException {
		cs.setNClob(parameterName, value);
	}

	public void setNClob(String parameterName, Reader reader) throws SQLException {
		cs.setNClob(parameterName, reader);
	}

	public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
		cs.setNClob(parameterName, reader, length);
	}

	public void setNString(String parameterName, String value) throws SQLException {
		cs.setNString(parameterName, value);
	}

	public void setNull(String parameterName, int sqlType) throws SQLException {
		cs.setNull(parameterName, sqlType);
	}

	public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
		cs.setNull(parameterName, sqlType, typeName);
	}

	public void setObject(String parameterName, Object x) throws SQLException {
		cs.setObject(parameterName, x);
	}

	public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
		cs.setObject(parameterName, x, targetSqlType);
	}

	public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
		cs.setObject(parameterName, x, targetSqlType, scale);
	}

	public void setRowId(String parameterName, RowId x) throws SQLException {
		cs.setRowId(parameterName, x);
	}

	public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
		cs.setSQLXML(parameterName, xmlObject);
	}

	public void setShort(String parameterName, short x) throws SQLException {
		cs.setShort(parameterName, x);
	}

	public void setString(String parameterName, String x) throws SQLException {
		cs.setString(parameterName, x);
	}

	public void setTime(String parameterName, Time x) throws SQLException {
		cs.setTime(parameterName, x);
	}

	public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
		cs.setTime(parameterName, x, cal);
	}

	public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
		cs.setTimestamp(parameterName, x);
	}

	public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
		cs.setTimestamp(parameterName, x, cal);
	}

	public void setURL(String parameterName, URL val) throws SQLException {
		cs.setURL(parameterName, val);
	}

	public boolean wasNull() throws SQLException {
		return cs.wasNull();
	}

	public void closeOnCompletion() throws SQLException {
		cs.closeOnCompletion();
	}

	public boolean isCloseOnCompletion() throws SQLException {
		return cs.isCloseOnCompletion();
	}

	public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
		return cs.getObject(parameterIndex, type);
	}

	public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
		return cs.getObject(parameterName, type);
	}
}