package com.example.contoller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.record.CFRuleBase.ComparisonOperator;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFColor.BLACK;
import org.apache.poi.ss.formula.functions.Address;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellRange;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFConditionalFormattingRule;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFFontFormatting;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheetConditionalFormatting;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.model.QueryModel;
import com.example.service.QueryService;

@Component
public class JobQuery {
	@Autowired
	private QueryService queryService;

	private static final String FILE_NAME = "E://New folder//test.xlsx";

	@Scheduled(cron = "10 56 *	*  * * * ", zone = "Asia/Colombo")
	public void createExcel() throws IOException {
		ArrayList<Object[]> test = new ArrayList<Object[]>();
		List<QueryModel> queryReportForExcel = queryService.getQueryReportForExcel();
		Date date3 = null;
		String closedDateExcel = "";
		int queryStatus = 0;
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Java Books");

		test.add(new String[] { "ATTENDED BY", "CLIENT", "RAISEDDATE", "QUERY", "CLOSED DATE", "RAISED BY",
				"RAISED THROUGH", "CLOSED BY", "STATUS", "Second Status" });
		for (QueryModel queryModel : queryReportForExcel) {
			queryStatus = queryModel.getQueryStatus();
			System.out.println(queryStatus + "queryStatusqueryStatus");
		}
		if (queryStatus == 2) {
			System.out.println("in if");
			for (QueryModel query : queryReportForExcel) {
				CreationHelper createHelper = workbook.getCreationHelper();
				java.util.Date raisedDate = query.getRaisedDate();
				java.util.Date closedDate = query.getClosedDate();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
				String raisedDateExcel = dateFormat.format(raisedDate);
				if (closedDate != date3) {
					closedDateExcel = dateFormat.format(closedDate);
					int rownum = 0;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					for (Object[] countries : test) {

						Row row = sheet.createRow(rownum++);

						CellStyle style = workbook.createCellStyle();
						style.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
						XSSFFont font = workbook.createFont();
						font.setColor(HSSFColor.RED.index);
						style.setFont(font);
						row.setRowStyle(style);

						int cellnum = 0;
						Cell cell = row.createCell(cellnum++);
						cell.setCellStyle(style);
						for (Object obj : countries) {
							CellStyle style2 = workbook.createCellStyle();
							style.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
							XSSFFont font2 = workbook.createFont();
							font.setColor(HSSFColor.RED.index);
							style.setFont(font2);
							cell.setCellStyle(style2);

							if (obj instanceof String)
								cell.setCellValue((String) obj);

							else if (obj instanceof java.util.Date)
								cell.setCellValue((java.util.Date) obj);
							else if (obj instanceof Integer)
								cell.setCellValue((Integer) obj);
						}
					}

					test.add(new Object[] { query.getAttentedBy(), query.getClient(), raisedDateExcel, query.getQuery(),
							closedDateExcel, query.getRaisedBy(), query.getRaisedThrough(), query.getClosedBy(),
							query.getStatus() });
				}

				else {
					System.out.println("test in else");
					int rownum = 0;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					for (Object[] countries : test) {
						Row row = sheet.createRow(rownum++);
						CellStyle style = workbook.createCellStyle();
						style.setFillBackgroundColor(IndexedColors.RED.getIndex());
						row.setRowStyle(style);

						int cellnum = 0;
						for (Object obj : countries) {

							CellStyle style3 = workbook.createCellStyle();
							style.setFillBackgroundColor(IndexedColors.BROWN.getIndex());
							XSSFFont font = workbook.createFont();
							font.setColor(HSSFColor.RED.index);
							style.setFont(font);
							row.setRowStyle(style3);
							Cell cell = row.createCell(cellnum++);
							if (obj instanceof String)
								cell.setCellValue((String) obj);
							else if (obj instanceof java.util.Date)
								cell.setCellValue((java.util.Date) obj);
							else if (obj instanceof Integer)
								cell.setCellValue((Integer) obj);
						}
					}

					test.add(new Object[] { query.getAttentedBy(), query.getClient(), raisedDateExcel, query.getQuery(),
							"", query.getRaisedBy(), query.getRaisedThrough(), query.getClosedBy(), query.getStatus(),
							query.getQueryStatus() });

				}

			}

			// Iterate over data and write to sheet
			try {
				String dest = "E:\\New folder\\test.xlsx";
				System.out.println(dest + "dest");
				FileOutputStream out = new FileOutputStream(new File(dest));
				workbook.write(out);
				out.close();
			}

			catch (Exception e) {
				e.printStackTrace();
			} finally {
				workbook.close();
			}
		}

	}
	/*
	 * public void createExcelByClick() throws IOException { ArrayList<Object[]>
	 * test = new ArrayList<Object[]>(); List<QueryModel> queryReportForExcel =
	 * queryService.getQueryReportForExcel(); Date date3 = null; String
	 * closedDateExcel = ""; int queryStatus = 0; XSSFWorkbook workbook = new
	 * XSSFWorkbook(); XSSFSheet sheet = workbook.createSheet("query");
	 * System.out.println(workbook);
	 * 
	 * test.add(new String[] { "ATTENDED BY", "CLIENT", "RAISEDDATE", "QUERY",
	 * "CLOSED DATE", "RAISED BY", "RAISED THROUGH", "CLOSED BY", "STATUS",
	 * "Second Status" }); for (QueryModel queryModel : queryReportForExcel) {
	 * System.out.println(queryModel.getQuery() + "queryModelqueryModel");
	 * queryStatus = queryModel.getQueryStatus(); } if (queryStatus == 2) { for
	 * (QueryModel query : queryReportForExcel) { CreationHelper createHelper =
	 * workbook.getCreationHelper(); java.util.Date raisedDate =
	 * query.getRaisedDate(); java.util.Date closedDate = query.getClosedDate();
	 * DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd "); String
	 * raisedDateExcel = dateFormat.format(raisedDate); if (closedDate != date3) {
	 * System.out.println(closedDate + "closedDateclosedDate"); closedDateExcel =
	 * dateFormat.format(closedDate); System.out.println(closedDateExcel +
	 * "closedDateExcelclosedDateExcel"); int rownum = 0; SimpleDateFormat sdf = new
	 * SimpleDateFormat("yyyy-MM-dd"); for (Object[] countries : test) {
	 * 
	 * Row row = sheet.createRow(rownum++);
	 * 
	 * CellStyle style = workbook.createCellStyle();
	 * style.setFillBackgroundColor(IndexedColors.YELLOW.getIndex()); XSSFFont font
	 * = workbook.createFont(); font.setColor(HSSFColor.RED.index);
	 * style.setFont(font); row.setRowStyle(style);
	 * 
	 * int cellnum = 0; Cell cell = row.createCell(cellnum++);
	 * cell.setCellStyle(style); for (Object obj : countries) { CellStyle style2 =
	 * workbook.createCellStyle();
	 * style.setFillBackgroundColor(IndexedColors.YELLOW.getIndex()); XSSFFont font2
	 * = workbook.createFont(); font.setColor(HSSFColor.RED.index);
	 * style.setFont(font2); cell.setCellStyle(style2);
	 * 
	 * if (obj instanceof String) cell.setCellValue((String) obj);
	 * 
	 * else if (obj instanceof java.util.Date) cell.setCellValue((java.util.Date)
	 * obj); else if (obj instanceof Integer) cell.setCellValue((Integer) obj); } }
	 * 
	 * test.add(new Object[] { query.getAttentedBy(), query.getClient(),
	 * raisedDateExcel, query.getQuery(), closedDateExcel, query.getRaisedBy(),
	 * query.getRaisedThrough(), query.getClosedBy(), query.getStatus() }); }
	 * 
	 * else { System.out.println("in else"); int rownum = 0; SimpleDateFormat sdf =
	 * new SimpleDateFormat("yyyy-MM-dd"); for (Object[] countries : test) { Row row
	 * = sheet.createRow(rownum++); CellStyle style = workbook.createCellStyle();
	 * style.setFillBackgroundColor(IndexedColors.RED.getIndex());
	 * row.setRowStyle(style);
	 * 
	 * int cellnum = 0; for (Object obj : countries) {
	 * 
	 * CellStyle style3 = workbook.createCellStyle();
	 * style.setFillBackgroundColor(IndexedColors.BROWN.getIndex()); XSSFFont font =
	 * workbook.createFont(); font.setColor(HSSFColor.RED.index);
	 * style.setFont(font); row.setRowStyle(style3); Cell cell =
	 * row.createCell(cellnum++); if (obj instanceof String)
	 * cell.setCellValue((String) obj); else if (obj instanceof java.util.Date)
	 * cell.setCellValue((java.util.Date) obj); else if (obj instanceof Integer)
	 * cell.setCellValue((Integer) obj); } }
	 * 
	 * test.add(new Object[] { query.getAttentedBy(), query.getClient(),
	 * raisedDateExcel, query.getQuery(), "", query.getRaisedBy(),
	 * query.getRaisedThrough(), query.getClosedBy(), query.getStatus(),
	 * query.getQueryStatus() });
	 * 
	 * }
	 * 
	 * }
	 * 
	 * // Iterate over data and write to sheet
	 * 
	 * try { // Write the workbook in file system System.out.println("test here ");
	 * FileOutputStream out = new FileOutputStream(new File(FILE_NAME));
	 * workbook.write(out); System.out.println(out); out.close(); try {
	 * 
	 * } catch (Exception e) { // TODO: handle exception }
	 * 
	 * }
	 * 
	 * catch (Exception e) { e.printStackTrace(); } finally { workbook.close(); } }
	 * 
	 * }
	 */

	@Scheduled(cron = "11 09 *	*  * * * ", zone = "Asia/Colombo")
	public void test() throws IOException {

		int status = 0;
		int queryStatus = 0;
		String[] columns = { "Client", "Raised On", "Queries/Enhancements", "Raised By", "Raised through",
				"Attended By", "Comments", "Closed By", "Closed On" };
		Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

		/*
		 * CreationHelper helps us create instances of various things like DataFormat,
		 * Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way
		 */
		CreationHelper createHelper = workbook.getCreationHelper();

		// Create a Sheet
		Sheet sheet = workbook.createSheet("HMS & DMS Queries");
		Header header = sheet.getHeader();
		header.setCenter("HMS Support/Development Queries");
		header.setLeft("Open - Yellow 	Closed - Green");

		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setBorderBottom(style.BORDER_THIN);
		style.setBorderRight(style.BORDER_THIN);
		style.setBorderLeft(style.BORDER_THIN);
		style.setBorderTop(style.BORDER_THIN);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Font font = workbook.createFont();
		font.setColor(IndexedColors.BLACK.getIndex());
		style.setFont(font);

		CellStyle style1 = workbook.createCellStyle();
		style1.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style1.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		style1.setBorderBottom(style.BORDER_THIN);
		style1.setBorderRight(style.BORDER_THIN);
		style1.setBorderLeft(style.BORDER_THIN);
		style1.setBorderTop(style.BORDER_THIN);
		Font font1 = workbook.createFont();
		font1.setColor(IndexedColors.YELLOW.getIndex());
		style1.setFont(font);

		CellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style2.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		style2.setBorderBottom(style.BORDER_THIN);
		style2.setBorderRight(style.BORDER_THIN);
		style2.setBorderLeft(style.BORDER_THIN);
		style2.setBorderTop(style.BORDER_THIN);
		Font font2 = workbook.createFont();
		font2.setColor(IndexedColors.BLACK.getIndex());
		style2.setFont(font);

		List<QueryModel> queryReportForExcelCondition = queryService.getQueryReportForExcel();
		for (QueryModel queryModel : queryReportForExcelCondition) {
			status = queryModel.getQueryStatus();
		}

		if (status == 2) {

			System.out.println("in if");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setColor(IndexedColors.BROWN.getIndex());

			// Create a CellStyle with the font
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Create a Row
			Row headerRow = sheet.createRow(0);

			// Create cells
			for (int i = 0; i < columns.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerCellStyle);
			}

			// Create Cell Style for formatting Date
			CellStyle dateCellStyle = workbook.createCellStyle();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

			// Create Other rows and cells with employees data
			int rowNum = 1;

			List<QueryModel> queryReportForExcel = queryService.getQueryReportForExcel();
			for (QueryModel employee : queryReportForExcel) {

				queryStatus = employee.getQueryStatus();

				if (queryStatus == 1) {
					Row row = sheet.createRow(rowNum++);

					row.createCell(0).setCellValue(employee.getClient());

					java.util.Date raisedDate = employee.getRaisedDate();
					java.util.Date closedDate = employee.getClosedDate();
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
					String raisedDateExcel = dateFormat.format(raisedDate);
					String closedDateExcel = dateFormat.format(closedDate);
					row.createCell(1).setCellValue(raisedDateExcel);

					row.createCell(2).setCellValue(employee.getQuery());
					row.createCell(3).setCellValue(employee.getRaisedBy());
					row.createCell(4).setCellValue(employee.getRaisedThrough());
					row.createCell(5).setCellValue(employee.getAttentedBy());
					row.createCell(6).setCellValue(employee.getStatus());
					row.createCell(7).setCellValue(employee.getClosedBy());

					row.createCell(8).setCellValue(closedDateExcel);

					/*
					 * Cell dateOfBirthCell = row.createCell(2);
					 * dateOfBirthCell.setCellValue(employee.getRaisedDate());
					 * dateOfBirthCell.setCellStyle(dateCellStyle);
					 * 
					 * row.createCell(3).setCellValue(employee.getQuery()); row.setRowStyle(style);
					 * Cell cell = row.getCell(0); Cell cell1 = row.getCell(1);
					 * 
					 * Cell cell2 = row.getCell(2); Cell cell3 = row.getCell(3);
					 */
					Cell cell = row.getCell(0);
					Cell cell1 = row.getCell(1);

					Cell cell2 = row.getCell(2);
					Cell cell3 = row.getCell(3);
					Cell cell4 = row.getCell(4);
					Cell cell5 = row.getCell(5);
					Cell cell6 = row.getCell(6);
					Cell cell7 = row.getCell(7);
					Cell cell8 = row.getCell(8);
					cell.setCellStyle(style);
					cell1.setCellStyle(style);
					cell2.setCellStyle(style);
					cell3.setCellStyle(style);
					cell4.setCellStyle(style);
					cell5.setCellStyle(style);
					cell6.setCellStyle(style);
					cell7.setCellStyle(style);
					cell8.setCellStyle(style);

				} else if (queryStatus == 3) {
					Row row = sheet.createRow(rowNum++);
					java.util.Date raisedDate = employee.getRaisedDate();
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
					String raisedDateExcel = dateFormat.format(raisedDate);

					row.createCell(0).setCellValue(employee.getClient());

					Cell dateOfBirthCell = row.createCell(1);
					dateOfBirthCell.setCellValue(raisedDateExcel);
					dateOfBirthCell.setCellStyle(dateCellStyle);
					row.createCell(2).setCellValue(employee.getQuery());
					row.createCell(3).setCellValue(employee.getRaisedBy());
					row.createCell(4).setCellValue(employee.getRaisedThrough());
					row.createCell(5).setCellValue(employee.getAttentedBy());
					row.createCell(6).setCellValue(employee.getStatus());
					row.createCell(7).setCellValue(employee.getClosedBy());
					row.createCell(8).setCellValue("");

					/*
					 * Cell dateOfBirthCell = row.createCell(2);
					 * dateOfBirthCell.setCellValue(employee.getRaisedDate());
					 * dateOfBirthCell.setCellStyle(dateCellStyle);
					 * 
					 * row.createCell(3).setCellValue(employee.getQuery()); row.setRowStyle(style);
					 * Cell cell = row.getCell(0); Cell cell1 = row.getCell(1);
					 * 
					 * Cell cell2 = row.getCell(2); Cell cell3 = row.getCell(3);
					 */
					Cell cell = row.getCell(0);
					Cell cell1 = row.getCell(1);

					Cell cell2 = row.getCell(2);
					Cell cell3 = row.getCell(3);
					Cell cell4 = row.getCell(4);
					Cell cell5 = row.getCell(5);
					Cell cell6 = row.getCell(6);
					Cell cell7 = row.getCell(7);
					Cell cell8 = row.getCell(8);
					cell.setCellStyle(style2);
					cell1.setCellStyle(style2);
					cell2.setCellStyle(style2);
					cell3.setCellStyle(style2);
					cell4.setCellStyle(style2);
					cell5.setCellStyle(style2);
					cell6.setCellStyle(style2);
					cell7.setCellStyle(style2);

					cell8.setCellStyle(style2);

				}

				else {
					Row row = sheet.createRow(rowNum++);
					java.util.Date raisedDate = employee.getRaisedDate();
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
					String raisedDateExcel = dateFormat.format(raisedDate);

					row.createCell(0).setCellValue(employee.getClient());

					Cell dateOfBirthCell = row.createCell(1);
					dateOfBirthCell.setCellValue(raisedDateExcel);
					dateOfBirthCell.setCellStyle(dateCellStyle);
					row.createCell(2).setCellValue(employee.getQuery());
					row.createCell(3).setCellValue(employee.getRaisedBy());
					row.createCell(4).setCellValue(employee.getRaisedThrough());
					row.createCell(5).setCellValue(employee.getAttentedBy());
					row.createCell(6).setCellValue(employee.getStatus());
					row.createCell(7).setCellValue(employee.getClosedBy());
					row.createCell(8).setCellValue("");

					/*
					 * Cell dateOfBirthCell = row.createCell(2);
					 * dateOfBirthCell.setCellValue(employee.getRaisedDate());
					 * dateOfBirthCell.setCellStyle(dateCellStyle);
					 * 
					 * row.createCell(3).setCellValue(employee.getQuery()); row.setRowStyle(style);
					 * Cell cell = row.getCell(0); Cell cell1 = row.getCell(1);
					 * 
					 * Cell cell2 = row.getCell(2); Cell cell3 = row.getCell(3);
					 */
					Cell cell = row.getCell(0);
					Cell cell1 = row.getCell(1);

					Cell cell2 = row.getCell(2);
					Cell cell3 = row.getCell(3);
					Cell cell4 = row.getCell(4);
					Cell cell5 = row.getCell(5);
					Cell cell6 = row.getCell(6);
					Cell cell7 = row.getCell(7);
					Cell cell8 = row.getCell(8);
					cell.setCellStyle(style1);
					cell1.setCellStyle(style1);
					cell2.setCellStyle(style1);
					cell3.setCellStyle(style1);
					cell4.setCellStyle(style1);
					cell5.setCellStyle(style1);
					cell6.setCellStyle(style1);
					cell7.setCellStyle(style1);

					cell8.setCellStyle(style1);

				}

				// Resize all columns to fit the content size
				for (int i = 0; i < columns.length; i++) {
					sheet.autoSizeColumn(i);
				}

			}
			// Create a Font for styling header cells
			/*
			 * Font headerFont = workbook.createFont(); headerFont.setBold(true);
			 * headerFont.setFontHeightInPoints((short) 14);
			 * headerFont.setColor(IndexedColors.RED.getIndex());
			 * 
			 * // Create a CellStyle with the font CellStyle headerCellStyle =
			 * workbook.createCellStyle(); headerCellStyle.setFont(headerFont);
			 * 
			 * // Create a Row Row headerRow = sheet.createRow(0);
			 * 
			 * // Create cells for(int i = 0; i < columns.length; i++) { Cell cell =
			 * headerRow.createCell(i); cell.setCellValue(columns[i]);
			 * cell.setCellStyle(headerCellStyle); }
			 * 
			 * // Create Cell Style for formatting Date CellStyle dateCellStyle =
			 * workbook.createCellStyle();
			 * dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat(
			 * "dd-MM-yyyy"));
			 * 
			 * // Create Other rows and cells with employees data int rowNum = 1;
			 * 
			 * List<QueryModel> queryReportForExcel = queryService.getQueryReportForExcel();
			 * for(QueryModel employee: queryReportForExcel) { Row row =
			 * sheet.createRow(rowNum++);
			 * 
			 * row.createCell(0) .setCellValue(employee.getClient());
			 * 
			 * row.createCell(1) .setCellValue(employee.getQuery());
			 * 
			 * Cell dateOfBirthCell = row.createCell(2);
			 * dateOfBirthCell.setCellValue(employee.getRaisedDate());
			 * dateOfBirthCell.setCellStyle(dateCellStyle);
			 * 
			 * row.createCell(3) .setCellValue(employee.getQuery()); }
			 * 
			 * // Resize all columns to fit the content size for(int i = 0; i <
			 * columns.length; i++) { sheet.autoSizeColumn(i); }
			 */
			// Write the output to a file
			FileOutputStream out = new FileOutputStream(new File("E://New folder//CountriesDetails.xlsx"));
			workbook.write(out);
			out.close();

			// Closing the workbook
			workbook.close();
		}
	}

	public void createExcelByClick() throws IOException {

		int status = 0;
		int queryStatus = 0;
		String[] columns = { "Client", "Raised On", "Queries/Enhancements", "Raised By", "Raised through",
				"Attended By", "Comments", "Closed By", "Closed On" };
		Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

		
		CreationHelper createHelper = workbook.getCreationHelper();

		Sheet sheet = workbook.createSheet("HMS & DMS Queries");
		Header header = sheet.getHeader();
		header.setCenter("HMS Support/Development Queries");
		header.setLeft("Open - Yellow 	Closed - Green");

		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setBorderBottom(style.BORDER_THIN);
		style.setBorderRight(style.BORDER_THIN);
		style.setBorderLeft(style.BORDER_THIN);
		style.setBorderTop(style.BORDER_THIN);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Font font = workbook.createFont();
		font.setColor(IndexedColors.BLACK.getIndex());
		style.setFont(font);

		CellStyle style1 = workbook.createCellStyle();
		style1.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style1.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		style1.setBorderBottom(style.BORDER_THIN);
		style1.setBorderRight(style.BORDER_THIN);
		style1.setBorderLeft(style.BORDER_THIN);
		style1.setBorderTop(style.BORDER_THIN);
		Font font1 = workbook.createFont();
		font1.setColor(IndexedColors.YELLOW.getIndex());
		style1.setFont(font);

		CellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style2.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		style2.setBorderBottom(style.BORDER_THIN);
		style2.setBorderRight(style.BORDER_THIN);
		style2.setBorderLeft(style.BORDER_THIN);
		style2.setBorderTop(style.BORDER_THIN);
		Font font2 = workbook.createFont();
		font2.setColor(IndexedColors.BLACK.getIndex());
		style2.setFont(font);

		List<QueryModel> queryReportForExcelCondition = queryService.getQueryReportForExcel();
		for (QueryModel queryModel : queryReportForExcelCondition) {
			status = queryModel.getQueryStatus();


			if (status == 2) {


				Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setFontHeightInPoints((short) 14);
				headerFont.setColor(IndexedColors.BROWN.getIndex());

				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFont(headerFont);

				Row headerRow = sheet.createRow(0);

				for (int i = 0; i < columns.length; i++) {
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(columns[i]);
					cell.setCellStyle(headerCellStyle);
				}

				// Create Cell Style for formatting Date
				CellStyle dateCellStyle = workbook.createCellStyle();
				dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

				// Create Other rows and cells with employees data
				int rowNum = 1;

				List<QueryModel> queryReportForExcel = queryService.getQueryReportForExcel();
				for (QueryModel employee : queryReportForExcel) {

					queryStatus = employee.getQueryStatus();

					if (queryStatus == 1) {
						Row row = sheet.createRow(rowNum++);

						row.createCell(0).setCellValue(employee.getClient());

						java.util.Date raisedDate = employee.getRaisedDate();
						java.util.Date closedDate = employee.getClosedDate();
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
						String raisedDateExcel = dateFormat.format(raisedDate);
						String closedDateExcel = dateFormat.format(closedDate);
						row.createCell(1).setCellValue(raisedDateExcel);

						row.createCell(2).setCellValue(employee.getQuery());
						row.createCell(3).setCellValue(employee.getRaisedBy());
						row.createCell(4).setCellValue(employee.getRaisedThrough());
						row.createCell(5).setCellValue(employee.getAttentedBy());
						row.createCell(6).setCellValue(employee.getStatus());
						row.createCell(7).setCellValue(employee.getClosedBy());

						row.createCell(8).setCellValue(closedDateExcel);

						Cell cell = row.getCell(0);
						Cell cell1 = row.getCell(1);

						Cell cell2 = row.getCell(2);
						Cell cell3 = row.getCell(3);
						Cell cell4 = row.getCell(4);
						Cell cell5 = row.getCell(5);
						Cell cell6 = row.getCell(6);
						Cell cell7 = row.getCell(7);
						Cell cell8 = row.getCell(8);
						cell.setCellStyle(style);
						cell1.setCellStyle(style);
						cell2.setCellStyle(style);
						cell3.setCellStyle(style);
						cell4.setCellStyle(style);
						cell5.setCellStyle(style);
						cell6.setCellStyle(style);
						cell7.setCellStyle(style);
						cell8.setCellStyle(style);

					} else if (queryStatus == 3) {
						Row row = sheet.createRow(rowNum++);
						java.util.Date raisedDate = employee.getRaisedDate();
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
						String raisedDateExcel = dateFormat.format(raisedDate);

						row.createCell(0).setCellValue(employee.getClient());

						Cell dateOfBirthCell = row.createCell(1);
						dateOfBirthCell.setCellValue(raisedDateExcel);
						dateOfBirthCell.setCellStyle(dateCellStyle);
						row.createCell(2).setCellValue(employee.getQuery());
						row.createCell(3).setCellValue(employee.getRaisedBy());
						row.createCell(4).setCellValue(employee.getRaisedThrough());
						row.createCell(5).setCellValue(employee.getAttentedBy());
						row.createCell(6).setCellValue(employee.getStatus());
						row.createCell(7).setCellValue(employee.getClosedBy());
						row.createCell(8).setCellValue("");

						/*
						 * Cell dateOfBirthCell = row.createCell(2);
						 * dateOfBirthCell.setCellValue(employee.getRaisedDate());
						 * dateOfBirthCell.setCellStyle(dateCellStyle);
						 * 
						 * row.createCell(3).setCellValue(employee.getQuery()); row.setRowStyle(style);
						 * Cell cell = row.getCell(0); Cell cell1 = row.getCell(1);
						 * 
						 * Cell cell2 = row.getCell(2); Cell cell3 = row.getCell(3);
						 */
						Cell cell = row.getCell(0);
						Cell cell1 = row.getCell(1);

						Cell cell2 = row.getCell(2);
						Cell cell3 = row.getCell(3);
						Cell cell4 = row.getCell(4);
						Cell cell5 = row.getCell(5);
						Cell cell6 = row.getCell(6);
						Cell cell7 = row.getCell(7);
						Cell cell8 = row.getCell(8);
						cell.setCellStyle(style2);
						cell1.setCellStyle(style2);
						cell2.setCellStyle(style2);
						cell3.setCellStyle(style2);
						cell4.setCellStyle(style2);
						cell5.setCellStyle(style2);
						cell6.setCellStyle(style2);
						cell7.setCellStyle(style2);

						cell8.setCellStyle(style2);

					}

					else {
						Row row = sheet.createRow(rowNum++);
						java.util.Date raisedDate = employee.getRaisedDate();
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
						String raisedDateExcel = dateFormat.format(raisedDate);

						row.createCell(0).setCellValue(employee.getClient());

						Cell dateOfBirthCell = row.createCell(1);
						dateOfBirthCell.setCellValue(raisedDateExcel);
						dateOfBirthCell.setCellStyle(dateCellStyle);
						row.createCell(2).setCellValue(employee.getQuery());
						row.createCell(3).setCellValue(employee.getRaisedBy());
						row.createCell(4).setCellValue(employee.getRaisedThrough());
						row.createCell(5).setCellValue(employee.getAttentedBy());
						row.createCell(6).setCellValue(employee.getStatus());
						row.createCell(7).setCellValue(employee.getClosedBy());
						row.createCell(8).setCellValue("");

						Cell cell = row.getCell(0);
						Cell cell1 = row.getCell(1);

						Cell cell2 = row.getCell(2);
						Cell cell3 = row.getCell(3);
						Cell cell4 = row.getCell(4);
						Cell cell5 = row.getCell(5);
						Cell cell6 = row.getCell(6);
						Cell cell7 = row.getCell(7);
						Cell cell8 = row.getCell(8);
						cell.setCellStyle(style1);
						cell1.setCellStyle(style1);
						cell2.setCellStyle(style1);
						cell3.setCellStyle(style1);
						cell4.setCellStyle(style1);
						cell5.setCellStyle(style1);
						cell6.setCellStyle(style1);
						cell7.setCellStyle(style1);

						cell8.setCellStyle(style1);

					}

					// Resize all columns to fit the content size
					for (int i = 0; i < columns.length; i++) {
						sheet.autoSizeColumn(i);
					}

				}
			}
			/*
			 * try {
			 * 
			 * FileOutputStream out = new FileOutputStream(new
			 * File("E://New folder//Queries.xlsx")); System.out.println(out+"out");
			 * workbook.write(out); out.close();
			 * 
			 * // Closing the workbook workbook.close(); } catch (Exception e) { // TODO:
			 * handle exception }
			 */

		
				// Write the workbook in file system
				
			 try {
				 FileOutputStream out = new FileOutputStream(new File("E:\\New folder\\queries.xlsx"));
				    workbook.write(out);
				    out.flush();
				    out.close();
		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }

		        System.out.println("Done");

	}
	}

}