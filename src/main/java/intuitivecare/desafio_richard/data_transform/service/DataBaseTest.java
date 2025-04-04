package intuitivecare.desafio_richard.data_transform.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class DataBaseTest {

    private static final String PRIMEIRO_LINK = "https://dadosabertos.ans.gov.br/FTP/PDA/demonstracoes_contabeis/";
    private static final String SEGUNDO_LINK = "https://dadosabertos.ans.gov.br/FTP/PDA/operadoras_de_plano_de_saude_ativas/";
    private static final String DIRETORIO_DOWNLOAD = System.getProperty("user.dir") + "/downloads/";
    private static final String DIRETORIO_ZIP = DIRETORIO_DOWNLOAD + "fileZipCsv/";
    private static final String DIRETORIO_TABULA = DIRETORIO_DOWNLOAD + "fileTabula/";
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DataBaseTest.class);

    public void downloadArquivos() {
        log.info("Metodo donload chamado");
        try {
            criarDiretorios();

            int anoAtual = LocalDate.now().getYear();

            for (int i = 0; i < 2; i++) {
                int ano = anoAtual - i - 1;
                baixarArquivosZip(ano);
            }

            baixarArquivoCsv();

            descompactarArquivosZip();
            substituirVirgulasPorPontos();

            System.out.println("Arquivos baixados e descompactados com sucesso!");

            System.out.println("Docker Compose executado com sucesso!");
        } catch (IOException ex) {
            Logger.getLogger(DataBaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void criarDiretorios() throws IOException {
        File diretorioDownload = new File(DIRETORIO_DOWNLOAD);
        if (!diretorioDownload.exists()) {
            diretorioDownload.mkdirs();
        }

        File diretorioZip = new File(DIRETORIO_ZIP);
        if (!diretorioZip.exists()) {
            diretorioZip.mkdirs();
        }

        File diretorioTabula = new File(DIRETORIO_TABULA);
        if (!diretorioTabula.exists()) {
            diretorioTabula.mkdirs();
        }
    }

    private void baixarArquivosZip(int ano) throws IOException {
        String url = PRIMEIRO_LINK + ano + "/";
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href$=.zip]");

        for (Element link : links) {
            log.info("Baixando  " + link);
            String arquivoZip = link.attr("href");
            URL urlZip = new URL(url + arquivoZip);
            baixarArquivo(urlZip, DIRETORIO_ZIP + arquivoZip);
        }
    }

    private void baixarArquivoCsv() throws IOException {
        URL urlCsv = new URL(SEGUNDO_LINK + "Relatorio_cadop.csv");
        baixarArquivo(urlCsv, DIRETORIO_DOWNLOAD + "Relatorio_cadop.csv");
    }

    private void baixarArquivo(URL url, String filePath) throws IOException {
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
    }

    private void descompactarArquivosZip() throws IOException {
        File diretorioZip = new File(DIRETORIO_ZIP);
        File[] arquivosZip = diretorioZip.listFiles();

        for (File arquivoZip : arquivosZip) {
            ZipInputStream zis = new ZipInputStream(new FileInputStream(arquivoZip));
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File arquivo = new File(DIRETORIO_TABULA + entry.getName());
                arquivo.getParentFile().mkdirs();
                FileOutputStream fos = new FileOutputStream(arquivo);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = zis.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zis.close();
        }
    }

    private void substituirVirgulasPorPontos() throws IOException {
        File diretorioTabula = new File(DIRETORIO_TABULA);
        File[] arquivosCsv = diretorioTabula.listFiles();

        for (File arquivoCsv : arquivosCsv) {
            if (arquivoCsv.getName().endsWith(".csv")) {
                log.info("Substituindo vírgulas por pontos em " + arquivoCsv.getName());
                substituirVirgulasPorPontosArquivo(arquivoCsv);
            }
        }
    }

    private void substituirVirgulasPorPontosArquivo(File arquivoCsv) throws IOException {
        File arquivoTemp = new File(arquivoCsv.getParent(), arquivoCsv.getName() + ".tmp");
        BufferedReader reader = new BufferedReader(new FileReader(arquivoCsv));
        BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTemp));

        String linha;
        while ((linha = reader.readLine()) != null) {
            writer.write(linha.replace(',', '.'));
            writer.newLine();
        }

        reader.close();
        writer.close();

        arquivoCsv.delete();
        arquivoTemp.renameTo(arquivoCsv);
    }
}