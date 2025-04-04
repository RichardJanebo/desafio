package intuitivecare.desafio_richard.data_transform.service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.stereotype.Service;
import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.PageIterator;
import technology.tabula.RectangularTextContainer;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;
import technology.tabula.Table;

@Service
public class TransformData {

    private static final String PDF_FILE = "Anexo I.pdf";
    private static final String CSV_FILE = "planilha.csv";
    private static final String ZIP_FILE = "planilha.zip";

    private static final String PDF_DIR = System.getProperty("user.dir") + "/downloads/filepdf";
    private static final String CSV_DIR = System.getProperty("user.dir") + "/downloads/filecsv";
    private static final String ZIP_DIR = System.getProperty("user.dir") + "/downloads/filezipe";

    public void init() {
        TransformData extractor = new TransformData();
        try {
            extractor.extractTables();
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao extrair tabelas: " + e.getMessage());
        }
    }

        private void extractTables() throws FileNotFoundException {
            File pdfFile = new File(PDF_DIR + "/" + PDF_FILE);
            InputStream in = new FileInputStream(pdfFile);

            try (PDDocument document = PDDocument.load(in)) {
                SpreadsheetExtractionAlgorithm sea = new SpreadsheetExtractionAlgorithm();
                PageIterator pi = new ObjectExtractor(document).extract();
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("Planilha");

                int rowIndex = 0;
                while (pi.hasNext()) {
                    Page page = pi.next();
                    List<Table> table = sea.extract(page);
                    for (Table tables : table) {
                        List<List<RectangularTextContainer>> rows = tables.getRows();
                        for (List<RectangularTextContainer> cells : rows) {
                            Row row = sheet.createRow(rowIndex++);
                            int cellIndex = 0;
                            for (RectangularTextContainer content : cells) {
                                String text = content.getText().replace("\r", " ");

                                text = text.replace("OD", "Seg. Odontológica");
                                text = text.replace("AMB", "Seg. Ambulatoria");
                                text = text.replace("HCO", "Seg. Hospitalar Com Obstetrícia");
                                text = text.replace("HSO", "Seg. Hospitalar Sem Obstetrícia");
                                text = text.replace("REF", "Plano Referência");
                                text = text.replace("PAC", "Procedimento de Alta Complexidade");
                                text = text.replace("DUT", "Diretriz de Utilização");

                                Cell cell = row.createCell(cellIndex++);
                                cell.setCellValue(text);
                            }
                        }
                    }
                }

                File csvDir = new File(CSV_DIR);
                if (!csvDir.exists()) {
                    csvDir.mkdirs();
                }


                FileOutputStream fos = new FileOutputStream(new File(CSV_DIR + "/" + CSV_FILE));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
                bw.write("\uFEFF"); // Adicionar BOM para UTF-8
                Sheet sheett = wb.getSheetAt(0);
                for (Row row : sheett) {
                    for (Cell cell : row) {
                        bw.write(cell.getStringCellValue() + ";");
                    }
                    bw.newLine();
                }
                bw.close();
                fos.close();
                wb.close();


                FileOutputStream zipFos = new FileOutputStream(new File(ZIP_DIR + "/" + ZIP_FILE));
                ZipOutputStream zipOut = new ZipOutputStream(zipFos);
                ZipEntry entry = new ZipEntry(CSV_FILE);
                zipOut.putNextEntry(entry);
                FileInputStream fileIn = new FileInputStream(new File(CSV_DIR + "/" + CSV_FILE));
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fileIn.read(buffer)) != -1) {
                    zipOut.write(buffer, 0, len);
                }
                fileIn.close();
                zipOut.closeEntry();
                zipOut.close();

                System.out.println("Planilha gerada com sucesso!");
            } catch (Exception e) {
                System.err.println("Erro ao extrair tabelas: " + e.getMessage());
            }
        }
    }