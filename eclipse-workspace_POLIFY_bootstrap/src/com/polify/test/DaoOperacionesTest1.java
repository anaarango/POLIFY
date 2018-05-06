package com.polify.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.polify.dao.DaoArtista;
import com.polify.dao.DaoEmpresa_difusora;
import com.polify.dao.DaoOperaciones;
import com.polify.entity.Artista;
import com.polify.entity.Empresa_difusora;
import com.polify.entity.Operaciones;

public class DaoOperacionesTest1 {
	
	int idGeneradoUsuario = 0;
	int idGeneradoEmpresa_difusora = 0;
	int idGeneradoArtista = 0;
	int idGeneradoOperaciones = 0;

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date fechaInicio = new Date();
	Date fechaFin = new Date();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		DaoEmpresa_difusora daoED = new DaoEmpresa_difusora();
		Empresa_difusora empresa = new Empresa_difusora(0, "Deezer", "deezer@deezer.com", 8);
		daoED.save(empresa);
		List<Empresa_difusora> empresas = daoED.getAllEmpresas();
		for (Empresa_difusora em : empresas) {
			if (em.getNombre_empresa().equals("Deezer")) {
				this.idGeneradoEmpresa_difusora = em.getId_empresa_difusora();
				break;
			}
		}

		DaoArtista daoArtista = new DaoArtista();
		Artista artista = new Artista(0, "Pablo", this.idGeneradoEmpresa_difusora, "pablo@gmil.com");
		daoArtista.save(artista);
		List<Artista> artistas = daoArtista.getAllArtist();
		for (Artista ar : artistas) {
			if (ar.getNombre_artista().equals("Pablo")) {
				this.idGeneradoArtista = ar.getId_artista();
				break;
			}
		}

		DaoOperaciones dao = new DaoOperaciones();
		fechaInicio = dateFormat.parse("12/03/2018");
		fechaFin = dateFormat.parse("16/03/2018");
		java.sql.Date fi = new java.sql.Date(fechaInicio.getTime());
		java.sql.Date ff = new java.sql.Date(fechaFin.getTime());
		Operaciones operaciones = new Operaciones(0, 1, this.idGeneradoArtista, this.idGeneradoEmpresa_difusora, 100,
				fi, ff);
		dao.save(operaciones);
		List<Operaciones> operacion = dao.getAllOperaciones();
		for (Operaciones op : operacion) {
			if (op.getId_usuario()== 1 && op.getId_artista()== this.idGeneradoArtista && op.getId_empresa_difusora()== this.idGeneradoEmpresa_difusora && op.getNumero_operaciones()== 100 && op.getFecha_inicial().equals(fi) && op.getFecha_final().equals(ff)){
				this.idGeneradoOperaciones = op.getId_operaciones();
				break;
			}
			
		}
	}

	@After
	public void tearDown() throws Exception {
		DaoOperaciones dao = new DaoOperaciones();
		dao.delete(this.idGeneradoOperaciones);
		
		DaoArtista daoArtista = new DaoArtista();
		daoArtista.delete(this.idGeneradoArtista);
		
		DaoEmpresa_difusora daoED = new DaoEmpresa_difusora();
		daoED.delete(this.idGeneradoEmpresa_difusora);
	}

	@Test
	public void testDaoOperaciones() {
		
	}

	@Test
	public void testGetAllOperaciones() {
		DaoOperaciones dao = new DaoOperaciones();
		boolean resultado = false;
		try {
			List<Operaciones> operaciones = dao.getAllOperaciones();
			if (!operaciones.isEmpty()) {
				resultado = true;

			}
			assertTrue("Resultado es", resultado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		DaoOperaciones dao = new DaoOperaciones();
		boolean resultado = dao.delete(idGeneradoOperaciones);
		assertTrue("Prueba satisfactoria", resultado);
	}

	@Test
	public void testSave() {
		DaoOperaciones dao = new DaoOperaciones();
		java.sql.Date fi = new java.sql.Date(fechaInicio.getTime());
		java.sql.Date ff = new java.sql.Date(fechaFin.getTime());
		Operaciones operaciones = new Operaciones(0, 1, this.idGeneradoArtista, this.idGeneradoEmpresa_difusora, 150, fi, ff);
		try {
			boolean resultado = dao.save(operaciones);
			assertTrue("Operacion guardada", resultado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		DaoOperaciones dao = new DaoOperaciones();
		java.sql.Date fi = new java.sql.Date(fechaInicio.getTime());
		java.sql.Date ff = new java.sql.Date(fechaFin.getTime());
		Operaciones operaciones = new Operaciones(0, 1, this.idGeneradoArtista, this.idGeneradoEmpresa_difusora, 150, fi, ff);
		boolean resultado = dao.update(operaciones);
		assertTrue("Actualizacion exitosa", resultado);
	}

}
