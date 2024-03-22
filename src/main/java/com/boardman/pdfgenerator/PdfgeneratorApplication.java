package com.boardman.pdfgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.context.Context;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;

@SpringBootApplication
public class PdfgeneratorApplication {

    public static String generateHTML() {
        // Thymeleaf context
        Context context = new Context();
        context.setVariable("title", "Sample HTML");
        context.setVariable("subhead", "Sample HTML to PDF Conversion");

        // Sample dynamic content
        context.setVariable("content", "<h4>Hello, World!</h4>");

        // Thymeleaf template engine
        TemplateEngine templateEngine = new TemplateEngine();

        // Configure template resolver
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode("HTML");
        templateResolver.setPrefix("/");
        templateResolver.setSuffix(".html");
        templateEngine.setTemplateResolver(templateResolver);

        StringWriter stringWriter = new StringWriter();
        templateEngine.process("template", context, stringWriter);

        return stringWriter.toString();
    }

    public static void convertToPDF(String htmlContent, String pdfFilePath) {
        try {
            // Create a PDF renderer
            OutputStream os = new FileOutputStream(pdfFilePath);
            ITextRenderer renderer = new ITextRenderer();

            // Set the HTML content
            renderer.setDocumentFromString(htmlContent);

            // Load the HTML file
            renderer.layout();

            // Render the PDF
            renderer.createPDF(os);

            // Close the streams
            os.close();

            System.out.println("Conversion successful!");
        } catch (Exception e) {
            System.err.println("Conversion failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Generate HTML content
        String htmlContent = generateHTML();
        // PDF file path
        String pdfFilePath = "output.pdf";

        // Convert HTML to PDF
        convertToPDF(htmlContent, pdfFilePath);

        SpringApplication.run(
                PdfgeneratorApplication.class, args);
    }

}
