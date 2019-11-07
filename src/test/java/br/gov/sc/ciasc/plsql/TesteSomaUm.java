package br.gov.sc.ciasc.plsql;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.gov.sc.ciasc.plsql.ConectorOracle;

class TesteSomaUm {
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testeSomaNumeroInteiro() {
		ConectorOracle co = new ConectorOracle();
		Connection conn = co.obterConexao();
		CallableStatement cStmt;
		try {
			cStmt = conn.prepareCall("{? = call somaum(?)}");
			cStmt.registerOutParameter( 1, Types.FLOAT);
			cStmt.setFloat(2, 6);
			
			cStmt.execute();						
			Float resultado = cStmt.getFloat(1);
			assertEquals(7, resultado);			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		co.liberarConexao(conn);
	}

	@Test
	void testeSomaNumeroFracionario() {
		ConectorOracle co = new ConectorOracle();
		Connection conn = co.obterConexao();
		CallableStatement cStmt;
		try {
			cStmt = conn.prepareCall("{? = call somaum(?)}");
			cStmt.registerOutParameter( 1, Types.FLOAT);
			cStmt.setFloat( 2, new Float(11.11));			
			cStmt.execute();						
			Float resultado = cStmt.getFloat(1);						
			assertEquals(new Float(12.11), resultado);			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		co.liberarConexao(conn);
	}
	
}
