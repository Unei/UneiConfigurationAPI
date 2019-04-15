package me.unei.configuration.api;

import java.io.Closeable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface ISQLConfiguration extends Closeable {

    /**
     * Attempts to reconnect to the remote database, or to reopen the file.
     *
     * @throws SQLException if a database access error occurs
     */
    public void reconnect() throws SQLException;

    /**
     * Executes the specified SQL query after applying the specified parameters.
     *
     * @see java.sql.PreparedStatement#execute()
     *
     * @param query the SQL query to prepare and then execute
     * @param parameters a map of the parameters to apply to the query
     *
     * @return <code>true</code> if the first result is a <code>ResultSet</code>
     *         object; <code>false</code> if the first result is an update
     *         count or there is no result
     *
     * @throws SQLException if a database access error occurs
     */
    public boolean execute(String query, Map<Integer, Object> parameters) throws SQLException;

    /**
     * Executes the specified SQL query after applying the specified parameters,
     * and returns the {@link ResultSet result}.
     *
     * @see java.sql.PreparedStatement#executeQuery()
     *
     * @param query the SQL query to prepare and then execute
     * @param parameters a map of the parameters to apply to the query
     *
     * @return a <code>ResultSet</code> object that contains the data produced by the
     *         query; never <code>null</code>
     *
     * @throws SQLException if a database access error occurs
     */
    public ResultSet query(String query, Map<Integer, Object> parameters) throws SQLException;

    /**
     * Executes the specified SQL query after applying the specified parameters,
     * and returns the integer returned in case of an update.
     *
     * @see java.sql.PreparedStatement#executeUpdate()
     *
     * @param query the SQL query to prepare and then execute
     * @param parameters a map of the parameters to apply to the query
     *
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 for SQL statements that return nothing
     *
     * @throws SQLException if a database access error occurs
     */
    public int update(String query, Map<Integer, Object> parameters) throws SQLException;

    /**
     * Executes the specified SQL query after applying the specified parameters,
     * and returns the long returned in case of an update.
     *
     * @see java.sql.PreparedStatement#executeLargeUpdate()
     *
     * @param query the SQL query to prepare and then execute
     * @param parameters a map of the parameters to apply to the query
     *
     * @return either (1) the row count for SQL Data Manipulation Language
     *         (DML) statements or (2) 0 for SQL statements that return nothing
     *
     * @throws SQLException if a database access error occurs
     */
    public long largeUpdate(String query, Map<Integer, Object> parameters) throws SQLException;
}

