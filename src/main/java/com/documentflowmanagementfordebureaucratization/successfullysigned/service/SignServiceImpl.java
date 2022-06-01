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

		try {
			PKCS7 pkcs = new PKCS7("C:\\Program Files\\Java\\jdk-15.0.2\\bin\\" + userName + "_root_.p12", password); // Use

			signature.sign(1, true, new java.awt.Rectangle(300, 100, 400, 200), pkcs);

			signature.save(filePath);

		} catch (Exception e) {
			System.out.print(e);
		}
	}

	@Override
	public void signaturesValidation(long documentId) {
		PdfFileSignature pdfSign = new PdfFileSignature();
		String _dataDir = "C:\\Users\\Mihai\\Desktop\\Successfully-Signed-Documents\\";
		pdfSign.bindPdf(_dataDir + documentId + ".pdf");
		int index = 1;
		
		for (String i : pdfSign.getSignNames()) {

			if (pdfSign.verifySignature("Signature" + index)) {
				System.out.println(i + " signature is valid");

				//validSignatures.add(i);

			} else {
				System.out.println(i + " signature is not valid");
			}

			index++;
		}
		pdfSign.close();
	}

}
