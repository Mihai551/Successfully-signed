package com.documentflowmanagementfordebureaucratization.successfullysigned.service;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Calendar;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.ExternalSigningSupport;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureOptions;
import org.springframework.stereotype.Service;

import com.aspose.pdf.Document;
import com.aspose.pdf.PKCS7;
import com.aspose.pdf.facades.PdfFileSignature;

/**
 * An example for signing a PDF with bouncy castle. A keystore can be created
 * with the java keytool, for example:
 *
 * {@code keytool -genkeypair -storepass 123456 -storetype pkcs12 -alias test -validity 365
 *        -v -keyalg RSA -keystore keystore.p12 }
 *
 * @author Thomas Chojecki
 * @author Vakhtang Koroghlishvili
 * @author John Hewson
 */
@Service
public class SignServiceImpl implements SignService {

	private static String _dataDir = "C:/Users/Mihai/Desktop/Successfully-Signed-Documents/";

	@Override
	public void sign(String userName, long documentId, String password) throws FileNotFoundException, Exception {

		com.aspose.pdf.License license = new com.aspose.pdf.License();
		license.setLicense(new java.io.FileInputStream("C:\\Users\\Mihai\\lics\\Aspose.PDF.Product.Family.lic"));

		String filePath = _dataDir + documentId + ".pdf";
		Document document = new Document(filePath);

		PdfFileSignature signature = new PdfFileSignature(document);

		PKCS7 pkcs = new PKCS7("C:\\Program Files\\Java\\jdk-15.0.2\\bin\\" + userName + "_root_.p12", password); // Use
																													// PKCS7/PKCS7Detached
		// objects
		signature.sign(1, true, new java.awt.Rectangle(300, 100, 400, 200), pkcs);
		// Save output PDF file
		signature.save(filePath);
	}

//	@Override
//	public void sign(String userName, long documentId, String password) throws FileNotFoundException, Exception {
//
//		String OUT_FILE = "C:/Users/Mihai/Desktop/Successfully-Signed-Documents/" + documentId + ".pdf";
//		System.out.print(OUT_FILE);
//		// ADD SIGNATURES
//
//		com.aspose.pdf.License license = new com.aspose.pdf.License();
//		license.setLicense(new java.io.FileInputStream(
//				"C:\\Users\\Mihai\\lics\\Aspose.PDF.Product.Family.lic"));
//
//		System.out.println(OUT_FILE);
//		@SuppressWarnings({ "deprecation", "resource" })
//		PdfFileSignature signature = new PdfFileSignature(document);
//		Rectangle rect = new Rectangle(100, 100, 200, 100);
//		pdfSign.sign(1, true, rect,
//				new PKCS7("C:\\Program Files\\Java\\jdk-15.0.2\\bin\\" + userName + "_root_.p12", password));
//		pdfSign.save(OUT_FILE);
//
//	}
}
