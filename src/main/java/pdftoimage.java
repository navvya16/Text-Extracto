import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.awt.image.BufferedImage;
import java.io.File;

class Extractor {
    public String extractor(String f) {
        Tesseract tesseract = new Tesseract();
        String text="";
        try {
            tesseract.setDatapath("C:\\Users\\navvy\\Desktop\\Tess4J-3.4.8-src\\Tess4J\\tessdata");
            text = tesseract.doOCR(new File(f));
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return text;
    }
}
public class pdftoimage {
    public String text(String filename) {
        String output="";
            try {
                if(FilenameUtils.getExtension(filename).equals("pdf")) {
                    PDDocument document = PDDocument.load(new File(filename));
                    PDFRenderer pdfRenderer = new PDFRenderer(document);
                    Extractor ex = new Extractor();
                    for (int page = 0; page < document.getNumberOfPages(); ++page) {
                        BufferedImage bim = pdfRenderer.renderImageWithDPI(
                                page, 300, ImageType.RGB);
                        ImageIOUtil.writeImage(
                                bim, String.format("C:\\Users\\navvy\\IdeaProjects\\Text Extraction\\src\\pro-%d.%s", page + 1, "jpg"), 300);

                         output += ex.extractor("C:\\Users\\navvy\\IdeaProjects\\Text Extraction\\src\\pro-" + (page + 1) + ".jpg") +
                                                    "*****************************************************************--" + (page + 1) + "--*****************************************************************\n";

                    }
                    document.close();
                }
                if(FilenameUtils.getExtension(filename).equals("jpg")){
                Extractor ex = new Extractor();
                output = ex.extractor(filename) + "*****************************************************************************************************************************\n";
                }

            }
            catch(Exception e){
            System.out.println(e);
            }
        return output;
    }
}
