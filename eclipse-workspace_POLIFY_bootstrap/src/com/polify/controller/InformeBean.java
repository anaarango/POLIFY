package com.polify.controller;

import java.security.cert.PKIXRevocationChecker.Option;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.management.RuntimeErrorException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polify.dao.DaoArtista;
import com.polify.dao.DaoEmpresa_difusora;
import com.polify.dao.DaoInforme;
import com.polify.dao.DaoOperaciones;
import com.polify.dao.DaoRecompensa;
import com.polify.entity.Artista;
import com.polify.entity.Empresa_difusora;
import com.polify.entity.Informe;
import com.polify.entity.Operaciones;
import com.polify.entity.Recompensa;
import com.polify.entity.SessionUtils;

import okhttp3.Request;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@ManagedBean
@SessionScoped
public class InformeBean {

	ArtistaBean artistaBean = new ArtistaBean();
	Informe informe = new Informe();
	private String selectedArtist;
	private String selectedDiffuserCompany;
	List<Informe> informesList = new ArrayList<Informe>();
	List<Artista> artistList = new ArrayList<Artista>();
	List<Empresa_difusora> diffuserCompanyList = new ArrayList<Empresa_difusora>();
	private List<Operaciones> operationByArtistList = new ArrayList<Operaciones>();
	private List<Operaciones> operationByDiffuserCompanyList = new ArrayList<Operaciones>();
	private List<Operaciones> operationRankingList = new ArrayList<Operaciones>();
	private List<Operaciones> recompensasPorArtista = new ArrayList<Operaciones>();
	private List<Recompensa> recompensaList = new ArrayList<Recompensa>();
	private List<Operaciones> operacionesForRecompensa = new ArrayList<Operaciones>();
	private List<Operaciones> recompensasGanadas = new ArrayList<Operaciones>();
	private String fileName;
	private String message;
	private String filePath = "C:\\";
	public InformeBean() {
}

	
	
	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public List<Operaciones> getOperacionesForRecompensa() {
		return operacionesForRecompensa;
	}

	public void setOperacionesForRecompensa(List<Operaciones> operacionesForRecompensa) {
		this.operacionesForRecompensa = operacionesForRecompensa;
	}

	public List<Operaciones> getRecompensasGanadas() {
		return recompensasGanadas;
	}

	public void setRecompensasGanadas(List<Operaciones> recompensasGanadas) {
		this.recompensasGanadas = recompensasGanadas;
	}

	public List<Recompensa> getRecompensaList() {
		return recompensaList;
	}

	public void setRecompensaList(List<Recompensa> recompensaList) {
		this.recompensaList = recompensaList;
	}

	public List<Operaciones> getRecompensasPorArtista() {
		return recompensasPorArtista;
	}

	public void setRecompensasPorArtista(List<Operaciones> recompensasPorArtista) {
		this.recompensasPorArtista = recompensasPorArtista;
	}

	public List<Operaciones> getOperationRankingList() {
		return operationRankingList;
	}

	public void setOperationRankingList(List<Operaciones> operationRankingList) {
		this.operationRankingList = operationRankingList;
	}

	public List<Operaciones> getOperationByArtistList() {
		return operationByArtistList;
	}

	public void setOperationByArtistList(List<Operaciones> operationByArtistList) {
		this.operationByArtistList = operationByArtistList;
	}

	public List<Operaciones> getOperationByDiffuserCompanyList() {
		return operationByDiffuserCompanyList;
	}

	public void setOperationByDiffuserCompanyList(List<Operaciones> operationByDiffuserCompanyList) {
		this.operationByDiffuserCompanyList = operationByDiffuserCompanyList;
	}

	public String getSelectedArtist() {
		return selectedArtist;
	}

	public void setSelectedArtist(String selectedArtist) {
		this.selectedArtist = selectedArtist;
	}

	public String getSelectedDiffuserCompany() {
		return selectedDiffuserCompany;
	}

	public void setSelectedDiffuserCompany(String selectedDiffuserCompany) {
		this.selectedDiffuserCompany = selectedDiffuserCompany;
	}

	public List<Artista> getAllArtist() throws SQLException {
		DaoArtista daoArtista = new DaoArtista();
		artistList = daoArtista.getAllArtist();
		return artistList;
	}

	public List<Empresa_difusora> getAllDiffuserCompany() throws SQLException {
		DaoEmpresa_difusora daoDiffuserCompany = new DaoEmpresa_difusora();
		diffuserCompanyList = daoDiffuserCompany.getAllEmpresas();
		return diffuserCompanyList;
	}

	public void getSellArtists() throws NumberFormatException, ParseException {
		System.out.println(SessionUtils.getUserName());
		System.out.println(informe.getStartDate());
		System.out.println(informe.getToDate());
		System.out.println(informe.getOptionSelected());
		System.out.println(selectedArtist);
		operationByArtistList = new ArrayList<Operaciones>();
		operationByDiffuserCompanyList = new ArrayList<Operaciones>();
		operationRankingList = new ArrayList<Operaciones>();
		informesList = new ArrayList<Informe>();
		recompensasGanadas = new ArrayList<Operaciones>();
		try {

			if (Integer.parseInt(informe.getOptionSelected()) == 1) {
				getSellForArtist();
			} else if (Integer.parseInt(informe.getOptionSelected()) == 2) {
				GetSellForDiffuserCompany();
			} else if (Integer.parseInt(informe.getOptionSelected()) == 3) {
				GetRecompensaArtist();
			} else if (Integer.parseInt(informe.getOptionSelected()) == 4) {
				GetSellRankingArtist();
			} else if (Integer.parseInt(informe.getOptionSelected()) == 5) {
				GetAllInforms();
			} else {
				getSellForArtist();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String createInformeForm() {
		DaoInforme daoInforme = new DaoInforme();
		try {
			daoInforme.save(informe);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "resultInforme";
	}

	public Informe getInforme() {
		return informe;
	}

	public void setInforme(Informe informe) {
		this.informe = informe;
	}

	public List<Informe> getInformesList() throws SQLException {

		return informesList;
	}

	public void setInformesList(List<Informe> informesList) {
		this.informesList = informesList;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void getSellForArtist() throws SQLException, NumberFormatException, ParseException {
		DaoOperaciones daoOperations = new DaoOperaciones();
		operationByArtistList = daoOperations.getAllOperacionesByArtist(
				(informe.getStartDate() != null ? informe.getStartDate() : null),
				(informe.getToDate() != null ? informe.getToDate() : null),
				(getSelectedArtist() != "" ? Integer.parseInt(getSelectedArtist()) : null));
		setOperationByArtistList(operationByArtistList);
	}

	public void GetSellForDiffuserCompany() {
		DaoOperaciones daoOperations = new DaoOperaciones();
		operationByDiffuserCompanyList = daoOperations.getAllOperacionesByDiffuserCompany(
				(informe.getStartDate() != null ? informe.getStartDate() : null),
				(informe.getToDate() != null ? informe.getToDate() : null),
				(getSelectedDiffuserCompany() != "" ? Integer.parseInt(getSelectedDiffuserCompany()) : null));
		setOperationByDiffuserCompanyList(operationByDiffuserCompanyList);
	}

	public void GetSellRankingArtist() {
		DaoOperaciones daoOperations = new DaoOperaciones();
		operationRankingList = daoOperations.getAllOperacionesRanking(
				(informe.getStartDate() != null ? informe.getStartDate() : null),
				(informe.getToDate() != null ? informe.getToDate() : null));
		setOperationRankingList(operationRankingList);
	}

	public void GetAllInforms() throws SQLException {
		DaoInforme daoInforme = new DaoInforme();
		informesList = daoInforme.getAllInformes();
		setInformesList(informesList);
	}

	public void ExportExcel() throws SQLException, NumberFormatException, ParseException {
		List<Operaciones> operations;
		DaoInforme daoInforme = new DaoInforme();
		// Get CurrentDateTimeforFileName append
		Date date = new Date();
		java.sql.Date currentDate = new java.sql.Date(date.getTime());
		String dateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss");
		try {
			dateString = sdfr.format(currentDate);
		} catch (Exception ex) {
			System.out.println(ex);
		}

		String SaveName = getFileName() + dateString;

		DaoOperaciones daoOperaciones = new DaoOperaciones();
	
		try {
			if (getFileName() != null && getFileName() != "") {
				if (Integer.parseInt(informe.getOptionSelected()) == 1) {
					HSSFWorkbook workbook = new HSSFWorkbook();
					HSSFSheet sheet = workbook.createSheet("Reporte artista");

					operations = getOperationByArtistList();

					int rowNum = 1;
					System.out.println("Creating excel");

					for (Operaciones operacion : operations) {
						Row row = sheet.createRow(rowNum++);
						Cell cell = row.createCell(0);
						cell.setCellValue(operacion.getId_artista());
						cell = row.createCell(1);
						cell.setCellValue(operacion.getNombre_artista());
						cell = row.createCell(2);
						cell.setCellValue(operacion.getNombre_empresa());
						cell = row.createCell(3);
						cell.setCellValue(operacion.getTotal());
					}

					try {
						FileOutputStream outputStream = new FileOutputStream(filePath + SaveName + ".xls");
						workbook.write(outputStream);
						workbook.close();
						System.out.println("File Created");
						System.out.println("Save Inform");
						informe = new Informe(0, SessionUtils.getUserId() , getFileName(), currentDate, SaveName);
						daoInforme.save(informe);
						System.out.println("Infom Saved");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					System.out.println("Done");

				} else if (Integer.parseInt(informe.getOptionSelected()) == 2) {
					HSSFWorkbook workbook = new HSSFWorkbook();
					HSSFSheet sheet = workbook.createSheet("Reporte empresa difusora");

					operations = getOperationByDiffuserCompanyList();

					int rowNum = 1;
					System.out.println("Creating excel");

					for (Operaciones operacion : operations) {
						Row row = sheet.createRow(rowNum++);
						Cell cell = row.createCell(0);
						cell.setCellValue((Integer) operacion.getId_empresa_difusora());
						cell = row.createCell(1);
						cell.setCellValue((String) operacion.getNombre_empresa());
						cell = row.createCell(2);
						cell.setCellValue((String) operacion.getNombre_artista());
						cell = row.createCell(4);
						cell.setCellValue((Integer) operacion.getNumero_operaciones());
						cell = row.createCell(3);
						cell.setCellValue((Integer) operacion.getTotal());
					}

					try {
						FileOutputStream outputStream = new FileOutputStream(filePath + SaveName + ".xls");
						workbook.write(outputStream);
						workbook.close();
						System.out.println("File Created");
						System.out.println("Save Inform");
						informe = new Informe(0, SessionUtils.getUserId(), getFileName(), currentDate, SaveName);
						daoInforme.save(informe);
						System.out.println("Infom Saved");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					System.out.println("SellDiffuserCompany");
				} else if (Integer.parseInt(informe.getOptionSelected()) == 3) {
					HSSFWorkbook workbook = new HSSFWorkbook();
					HSSFSheet sheet = workbook.createSheet("Reporte recompensas");

					operations = getRecompensasGanadas();

					int rowNum = 1;
					System.out.println("Creating excel");

					for (Operaciones operacion : operations) {
						Row row = sheet.createRow(rowNum++);
						Cell cell = row.createCell(0);
						cell.setCellValue((String) operacion.getNombre_artista());
						cell = row.createCell(1);
						cell.setCellValue((String) operacion.getNombre_empresa());
						cell = row.createCell(2);
						cell.setCellValue((Integer) operacion.getTotal());
						cell = row.createCell(3);
						cell.setCellValue((String) operacion.getNombre_recompensa());
					}

					try {
						FileOutputStream outputStream = new FileOutputStream(filePath + SaveName + ".xls");
						workbook.write(outputStream);
						workbook.close();
						System.out.println("File Created");
						System.out.println("Save Inform");
						informe = new Informe(0, SessionUtils.getUserId() , getFileName(), currentDate, SaveName);
						daoInforme.save(informe);
						System.out.println("Infom Saved");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					System.out.println("RecompensasParaArtistas");
				} else if (Integer.parseInt(informe.getOptionSelected()) == 4) {
					// GetSellRankingArtist();

					HSSFWorkbook workbook = new HSSFWorkbook();
					HSSFSheet sheet = workbook.createSheet("Reporte ranking");

					operations = getOperationRankingList();

					int rowNum = 1;
					System.out.println("Creating excel");

					for (Operaciones operacion : operations) {
						Row row = sheet.createRow(rowNum++);
						Cell cell = row.createCell(0);
						cell.setCellValue((Integer) operacion.getRankNumber());
						cell = row.createCell(1);
						cell.setCellValue((String) operacion.getNombre_artista());
						cell = row.createCell(2);
						cell.setCellValue((Integer) operacion.getTotal());
						cell = row.createCell(3);
						cell.setCellValue((String) operacion.getNombre_empresa());
					}

					try {
						FileOutputStream outputStream = new FileOutputStream(filePath + SaveName + ".xls");

						workbook.write(outputStream);
						workbook.close();
						System.out.println("File Created");
						System.out.println("Save Inform");
						informe = new Informe(0, SessionUtils.getUserId() , getFileName(), currentDate, SaveName);
						daoInforme.save(informe);
						System.out.println("Infom Saved");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					System.out.println("RankingArtist");
				} else {
					// getSellForArtist();
					System.out.println("SellForArtist");
				}
			}else {
				FacesContext.getCurrentInstance().addMessage("Test-Error", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error-Summary", "Error-Detail"));
				setMessage("Error ");
			}
			setFileName("");
			setMessage("Finalizado ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void GetRecompensaArtist() {
		DaoRecompensa daoRecompensa = new DaoRecompensa();
		DaoOperaciones daoOperations = new DaoOperaciones();
		List<Operaciones> resultado = new ArrayList<Operaciones>();
		recompensaList = daoRecompensa.getAllRecompensas();
		setRecompensaList(recompensaList);
		setRecompensaList(daoRecompensa.getAllRecompensas());
		operacionesForRecompensa = daoOperations.getAllOperacionesByArtist(
				(informe.getStartDate() != null ? informe.getStartDate() : null),
				(informe.getToDate() != null ? informe.getToDate() : null), null);

		for (Operaciones operacion : operacionesForRecompensa) {
			for (Recompensa recompensa : recompensaList) {
				if (operacion.getTotal() >= recompensa.getValor_recompensa()) {
					operacion.setNombre_recompensa(recompensa.getNombre_recompensa());
					resultado.add(operacion);
					break;
				}

			}
		}

		recompensasGanadas = resultado;

		for (Operaciones reco : getRecompensasGanadas()) {
			System.out.println(reco.getId_artista());
			System.out.println(reco.getTotal());

		}

	}
	
	
	public void dowloadExcel(String ubicacion_archivo,String nombre_informe) {
		System.out.println("DownloadExcel");
		System.out.println(ubicacion_archivo);
		FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        
        response.reset();
        response.setHeader("Content-Disposition","attachment;filename="+nombre_informe+".xls");

        try {
    		System.out.println("Try");
            File file = new File(filePath+ubicacion_archivo+".xls");
            FileInputStream fileIn = new FileInputStream(file);
            ServletOutputStream out = response.getOutputStream();

            byte[] outputByte = new byte[4096];
            //copy binary contect to output stream
            while(fileIn.read(outputByte, 0, 4096) != -1)
            {
                out.write(outputByte, 0, 4096);
            }
            fileIn.close();
            out.flush();
            out.close();
            facesContext.responseComplete();
        }
        catch(IOException e) {
    		System.out.println("Catch");
            e.printStackTrace();
        }
	}
}
