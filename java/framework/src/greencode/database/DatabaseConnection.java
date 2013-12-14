package greencode.database;

import greencode.kernel.Console;
import greencode.kernel.GreenCodeConfig;
import greencode.kernel.LogMessage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public final class DatabaseConnection {
	private Connection connection;	
	private DatabaseConfig config;
	
	private ArrayList<DatabaseStatement> statements = new ArrayList<DatabaseStatement>();
	private ArrayList<DatabasePreparedStatement> preparedStatements = new ArrayList<DatabasePreparedStatement>();
	
	public static DatabaseConnection getInstance() {return new DatabaseConnection();}
	
	private byte chanceConnected = 1;
	public Connection start() throws SQLException
	{
		close(null, null);
		String url = "jdbc:"+getConfig().getDatabase()+"://" + getConfig().getServerName() + ":3306/" + getConfig().getSchema();
		try {
			this.connection = DriverManager.getConnection(url, getConfig().getUserName(), getConfig().getPassword());
		} catch (SQLException e) {
			Console.error(LogMessage.getMessage("green-db-0001", getConfig().getDatabase()));
		
			if(config.getChanceReconnect() > 0 && chanceConnected < config.getChanceReconnect())
			{
				System.out.println(LogMessage.getMessage("green-db-0002"));
				try {
					Thread.sleep(250);
				} catch (InterruptedException e1) {
					Console.error(e1);
				}
				
				++chanceConnected;				
				start();
			}else if(config.getConnectionFileName() != null)
			{
				chanceConnected = 1;
				
				System.out.println(LogMessage.getMessage("green-db-0003", config.getConnectionFileName()));
				config = GreenCodeConfig.DataBase.getConfig(config.getConnectionFileName());
				start();
			}else
				throw e;
		}
		return this.getConnection();
	}
	
	public boolean close()
	{
		Boolean r = close(null, null);
		return r;
	}
	
	public boolean close(Statement stmt, ResultSet rs)
	{
		try {
			if (stmt != null)
			{
				stmt.close();
				if (rs != null)
					rs.close();
			}else
			{				
				while(!this.statements.isEmpty())
				{
					DatabaseStatement statement = this.statements.get(0);
					while(!statement.getResultSets().isEmpty())
					{
						ResultSet resultSet = statement.getResultSets().get(0);
						resultSet.close();
						statement.getResultSets().remove(resultSet);
					}
					
					statement.getStatement().close();
					this.statements.remove(statement);
				}
				
				while(!this.preparedStatements.isEmpty())
				{
					DatabasePreparedStatement prepared = this.preparedStatements.get(0);
					while(!prepared.getResultSets().isEmpty())
					{
						ResultSet resultSet = prepared.getResultSets().get(0);
						resultSet.close();
						prepared.getResultSets().remove(resultSet);
					}
					
					prepared.getPreparedStatement().close();
					this.preparedStatements.remove(prepared);
				}
			}
						
			if(this.connection != null)
				this.connection.close();
		} catch (SQLException e) {
			Console.error(e);
			return false;
		}
		return true;		
	}

	public Connection getConnection() throws SQLException {
		if(connection == null)
			throw new SQLException(LogMessage.getMessage("green-db-0004"));
		
		try {
			if(connection.isClosed())
				throw new SQLException(LogMessage.getMessage("green-db-0005"));
		} catch (SQLException e) {
			Console.error(e);
		}
		
		return connection;
	}
	
	private DatabaseStatement addStatements(DatabaseStatement dbs) {this.statements.add(dbs);return dbs;}
	private DatabasePreparedStatement addPreparedStatements(DatabasePreparedStatement dbps) {this.preparedStatements.add(dbps); return dbps;}

	public DatabaseStatement createStatement() throws SQLException
	{return addStatements(new DatabaseStatement(getConnection().createStatement()));}
	
	public DatabaseStatement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException
	{return addStatements(new DatabaseStatement(getConnection().createStatement(resultSetType, resultSetConcurrency)));}
	
	public DatabaseStatement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException
	{return addStatements(new DatabaseStatement(getConnection().createStatement(resultSetType, resultSetConcurrency, resultSetHoldability)));}
	
	public DatabasePreparedStatement prepareStatement(String sql) throws SQLException
	{return addPreparedStatements(new DatabasePreparedStatement(getConnection().prepareStatement(sql)));}
	
	public DatabasePreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException
	{return addPreparedStatements(new DatabasePreparedStatement(getConnection().prepareStatement(sql, autoGeneratedKeys)));}
	
	public DatabasePreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException
	{return addPreparedStatements(new DatabasePreparedStatement(getConnection().prepareStatement(sql, columnIndexes)));}
	
	public DatabasePreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException
	{return addPreparedStatements(new DatabasePreparedStatement(getConnection().prepareStatement(sql, columnNames)));}
	
	public DatabasePreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException
	{return addPreparedStatements(new DatabasePreparedStatement(getConnection().prepareStatement(sql, resultSetType, resultSetConcurrency)));}
	
	public DatabasePreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException
	{return addPreparedStatements(new DatabasePreparedStatement(getConnection().prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability)));}

	public DatabaseConfig getConfig() {return config;}
	public void setConfig(DatabaseConfig config) {this.config = config;}
}
