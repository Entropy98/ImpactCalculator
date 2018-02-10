import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class pdfReaderTest{
    private static String getText(File pdfFile) throws IOException {
        PDDocument doc = PDDocument.load(pdfFile);
        return new PDFTextStripper().getText(doc);
    }
    public static void main(String[] args) {
        try {
<<<<<<< HEAD
            String text = getText(new File("C:\\Users\\vidhi\\Documents\\Patel.Vidhi.resume.pdf"));
=======

            String currentDir = System.getProperty("user.dir");
            String text = getText(new File("\\pdfs\\hw3.pdf"));
>>>>>>> cd37c524552570c5274e65143883479343245bc8
            String[] t = text.trim().split("\\s+");
        } catch (IOException e) {///
            e.printStackTrace();
        }
    }
}