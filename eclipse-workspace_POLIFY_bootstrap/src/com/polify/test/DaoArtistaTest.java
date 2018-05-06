/**
 * 
 */
package com.polify.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.polify.dao.DaoArtista;
import com.polify.entity.Artista;

/**
 * @author jucarore
 *
 */
public class DaoArtistaTest {
	
	int idGeneradoArtista = 0;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		DaoArtista dao = new DaoArtista();
		Artista artista = new Artista(0, "JuanAlejandro", 1, "juan@gmail.com");
		dao.save(artista);
		List<Artista> artistas = dao.getAllArtist();
		for (Artista ar:artistas) {
			if(ar.getNombre_artista().equals("JuanAlejandro")) {
				this.idGeneradoArtista=ar.getId_artista();
				break;
			}
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		DaoArtista dao = new DaoArtista();
		dao.delete(this.idGeneradoArtista);
	}

	

	/**
	 * Test method for {@link com.polify.dao.DaoArtista#getAllArtist()}.
	 */
	@Test
	public void testGetAllArtist() {
		DaoArtista dao = new DaoArtista();
		boolean resultado = false;
		try {
			List<Artista> artistas = dao.getAllArtist();
			if(!artistas.isEmpty()) {
				resultado = true;
			}
			
			assertTrue("Resultado es",resultado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link com.polify.dao.DaoArtista#delete(int)}.
	 */
	@Test
	public void testDelete() {
		
		DaoArtista daoArtista = new DaoArtista();
		boolean resultado = daoArtista.delete(idGeneradoArtista);
		assertTrue("Prueba satisfactoria", resultado);
	}

	/**
	 * Test method for {@link com.polify.dao.DaoArtista#save(com.polify.entity.Artista)}.
	 */
	@Test
	public void testSave() {
		DaoArtista daoArtista = new DaoArtista();
		Artista artista = new Artista(2, "Juanes", 1, "juanes@gmail.com");
		try {
			boolean resultado = daoArtista.save(artista);
			assertTrue("Artista guardado", resultado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link com.polify.dao.DaoArtista#update(com.polify.entity.Artista)}.
	 */
	@Test
	public void testUpdate() {
		DaoArtista daoArtista = new DaoArtista();
		Artista artista = new Artista(2, "JuanesCO", 1, "juanes@gmail.com");
		boolean resultado = daoArtista.update(artista);
		assertTrue("Artista actualizado", resultado);
	}

}
