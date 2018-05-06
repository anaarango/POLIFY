package com.polify.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.polify.dao.DaoEmpresa_difusora;
import com.polify.entity.Empresa_difusora;

public class DaoEmpresa_difusoraTest1 {
	int idGeneradoEmpresa = 0;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		DaoEmpresa_difusora dao = new DaoEmpresa_difusora();
		Empresa_difusora empresa = new Empresa_difusora(2, "Youtube", "youtube@youtube.com", 10);
		dao.save(empresa);
		List<Empresa_difusora> empresas = dao.getAllEmpresas();
		for (Empresa_difusora em:empresas) {
			if(em.getNombre_empresa().equals("Youtube")) {
				this.idGeneradoEmpresa=em.getId_empresa_difusora();
				break;
			}
		}
	}

	@After
	public void tearDown() throws Exception {
		DaoEmpresa_difusora dao = new DaoEmpresa_difusora();
		dao.delete(this.idGeneradoEmpresa);
	}

	@Test
	public void testDaoEmpresa_difusora() {
		
	}

	@Test
	public void testGetAllEmpresas() {
		DaoEmpresa_difusora dao = new DaoEmpresa_difusora();
		boolean resultado = false;
		try {
			List<Empresa_difusora> empresa = dao.getAllEmpresas();
			if (!empresa.isEmpty()) {
				resultado = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		DaoEmpresa_difusora dao = new DaoEmpresa_difusora();
		boolean resultado = dao.delete(idGeneradoEmpresa);
		assertTrue("Prueba satisfactoria", resultado);
	}

	@Test
	public void testSave() {
		DaoEmpresa_difusora dao = new DaoEmpresa_difusora();
		Empresa_difusora empresa = new Empresa_difusora(2, "Youtube", "youtube@youtube.com", 10);
		try {
			boolean resultado = dao.save(empresa);
			assertTrue("Empresa guardada", resultado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		DaoEmpresa_difusora dao = new DaoEmpresa_difusora();
		Empresa_difusora empresa = new Empresa_difusora(2, "Youtube", "youtube@youtube.com", 10);
		boolean resultado = dao.update(empresa);
		assertTrue("Empresa Actualizada", resultado);
	}

}
