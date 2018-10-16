package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Impiegato;

public class ImpiegatoDaoUtil {
	
	private DataSource dataSource;

	public ImpiegatoDaoUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Impiegato> getAllImpiegati() throws SQLException{
		
		List<Impiegato> impiegati = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from impiegato ";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("firstName");
				String lastName = myRs.getString("lastName");
				String fiscalCode = myRs.getString("fiscalCode");
				String email = myRs.getString("email");
				
				// create new impiegato object
				Impiegato tempImpiegato = new Impiegato(id, firstName, lastName,fiscalCode, email);
				
				// add it to the list of impiegati
				impiegati.add(tempImpiegato);				
			}
			
			return impiegati;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}
	
	public Impiegato getImpiegato(String theImpiegatoId) throws Exception {

		Impiegato theImpiegato = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int impiegatoId;
		
		try {
			// convert impiegato id to int
			impiegatoId = Integer.parseInt(theImpiegatoId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected impiegato
			String sql = "select * from impiegato where id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, impiegatoId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String firstName = myRs.getString("firstName");
				String lastName = myRs.getString("lastName");
				String fiscalCode = myRs.getString("fiscalCode");
				String email = myRs.getString("email");
				
				// use the impiegatoId during construction
				theImpiegato = new Impiegato(impiegatoId, firstName,fiscalCode, lastName, email);
			}
			else {
				throw new Exception("Could not find student id: " + impiegatoId);
			}				
			
			return theImpiegato;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public void addImpiegato(Impiegato theImpiegato) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into impiegato "
					   + "(firstName, lastName, fiscalCode, email) "
					   + "values (?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the impiegato
			myStmt.setString(1, theImpiegato.getFirstName());
			myStmt.setString(2, theImpiegato.getLastName());
			myStmt.setString(3, theImpiegato.getFiscalCode());
			myStmt.setString(4, theImpiegato.getEmail());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	public void deleteImpiegato(String theImpiegatoId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert impiegato id to int
			int impiegatoId = Integer.parseInt(theImpiegatoId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete impiegato
			String sql = "delete from impiegato where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, impiegatoId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}
	
public void updateImpiegato(Impiegato theImpiegato) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update impiegato "
						+ "set firstName=?, lastName=?, fiscalCode=?, email=? "
						+ "where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, theImpiegato.getFirstName());
			myStmt.setString(2, theImpiegato.getLastName());
			myStmt.setString(3, theImpiegato.getFiscalCode());
			myStmt.setString(4, theImpiegato.getEmail());
			
			myStmt.setInt(5, theImpiegato.getId());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
}
