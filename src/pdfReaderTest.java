import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class pdfReaderTest{
    private static String getText(File pdfFile) throws IOException {
        PDDocument doc = PDDocument.load(pdfFile);
        return new PDFTextStripper().getText(doc);
    }
    public static void main(String args[]) {
        try {
            String text = getText(new File("C:\\Users\\vidhi\\Documents\\Patel.Vidhi.resume.pdf"));
            String[] t = text.trim().split("\\s+");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}