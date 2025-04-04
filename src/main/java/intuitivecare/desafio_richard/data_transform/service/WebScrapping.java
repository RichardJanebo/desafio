package intuitivecare.desafio_richard.data_transform.service;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class WebScrapping {

    private File pdfPath;
    private File zipPath;

    private final String URL = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";

    public void init() {
        criarDiretorios();
        File anexo1 = downloadPdf("Anexo I");
        File anexo2 = downloadPdf("Anexo II");

        if (anexo1 != null && anexo2 != null) {
            concatPdf(anexo1, anexo2);
        }
    }

    private void criarDiretorios() {
        pdfPath = new File(System.getProperty("user.dir") + "/downloads/filepdf");
        zipPath = new File(System.getProperty("user.dir") + "/downloads/filezipe");

        if (!pdfPath.exists()) {
            boolean criado = pdfPath.mkdirs();
            System.out.println(criado ? "Criado: " + pdfPath.getAbsolutePath() : "Falha ao criar: " + pdfPath.getAbsolutePath());
        }

        if (!zipPath.exists()) {
            boolean criado = zipPath.mkdirs();
            System.out.println(criado ? "Criado: " + zipPath.getAbsolutePath() : "Falha ao criar: " + zipPath.getAbsolutePath());
        }
    }

    public File downloadPdf(String fileName) {
        try {
            Document document = Jsoup.connect(URL).get();
            Elements links = document.select("a[href$=.pdf]");

            for (Element link : links) {
                if (link.text().contains(fileName)) {
                    URL pdfLink = new URL(link.attr("href"));
                    File file = new File(pdfPath.getAbsolutePath() + "/" + fileName + ".pdf");
                    FileUtils.copyURLToFile(pdfLink, file);
                    return file;
                }
            }
            return null;

        } catch (IOException e) {
            throw new RuntimeException("Erro ao baixar o PDF: " + fileName, e);
        }
    }

    private void concatPdf(File... files) {
        File zipFile = new File(zipPath.getAbsolutePath() + "/anexos.zip");

        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zipOut = new ZipOutputStream(fos)) {

            for (File file : files) {
                ZipEntry entry = new ZipEntry(file.getName());
                zipOut.putNextEntry(entry);
                FileUtils.copyFile(file, zipOut);
                zipOut.closeEntry();
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar o arquivo ZIP", e);
        }
    }
}