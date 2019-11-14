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
		assertEquals(new Double(7), somaum(new Double(6)));
	}

	@Test
	void testeSomaNumeroFracionario() {
		assertEquals(new Double(12.11), somaum(new Double(11.11)));
	}
	
	private Double somaum(Double numero) {
		Double resultado = null;
		ConectorOracle co = new ConectorOracle();
		Connection conn = co.obterConexao();
		CallableStatement cStmt;
		try {
			cStmt = conn.prepareCall("{? = call somaum(?)}");
			cStmt.registerOutParameter( 1, Types.DOUBLE);
			cStmt.setDouble(2, numero);		
			cStmt.execute();	
			resultado = cStmt.getDouble(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		co.liberarConexao(conn);	
		return resultado;
	}
	
}
