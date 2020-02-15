## PDF简介及解析

**PDF**  
**• PDF**  
**– Portable Document Format的简称，意为“便携式文档格式”。**  
**– Adobe公司发明的。**  
**– PostScript，用以生成和输出图形，在任何打印机上都可保证精确的颜色和准确的打印效果。**  
**– 字型嵌入系统，可使字型随文件一起传输。**  
**– 结构化的存储系统，绑定元素和任何相关内容到单个文件，带有适当的数据压缩系统。**  
**• PDF的版本，一般是1.4+。**  



**PDF 处理和第三方包**  
**• 常见功能处理**  
**– 解析PDF**  
**– 生成PDF(转化)**  
**• 第三方包**  
**– Apache PDFBox (免费)**  
**– iText (收费)**  
**– XDocReport (将docx转化为pdf)**  





**PDFBox**  
**• Apache PDFBox**  
**– 纯Java类库**  
**– 主要功能：创建，提取文本，分割/合并/删除，…**  
**– 主要类**  
**• PDDocument pdf文档对象**  
**• PDFTextStripper pdf文本对象**  
**• PDFMergerUtility 合并工具**  



```java
├─src
│  ├─main
│  │  ├─java
│  │  │  │  XDocReportTest.java
│  │  │  │  
│  │  │  ├─itext
│  │  │  │      AddImageExample.java
│  │  │  │      ChinesePDF.java
│  │  │  │      CreateListExample.java
│  │  │  │      CreateTableExample.java
│  │  │  │      FilePermissionsExample.java
│  │  │  │      FirstPdf.java
│  │  │  │      ImgPdf.java
│  │  │  │      JavaPdfHelloWorld.java
│  │  │  │      PasswordProtectedPdfExample.java
│  │  │  │      PdfStyingExample.java
│  │  │  │      ReadModifyPdfExample.java
│  │  │  │      SetPDFAttributes.java
│  │  │  │      TablePdf.java
│  │  │  │      
│  │  │  └─pdfbox
│  │  │          MergePdfs.java
│  │  │          NewMergePdfs.java
│  │  │          PdfReader.java
│  │  │          PdfWriter.java
│  │  │          RemovePdf.java
│  │  │          
│  │  └─resources
│  └─test
│      ├─java
│      └─resources
│  merge.pdf
│  merge2.pdf
│  pom.xml
│  sample1.pdf
│  sample2.pdf
│  simple.pdf
│  template.docx
│  template.pdf
│  test.pdf
```



```java
package pdfbox;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * PdfReader 抽取pdf的文本
 * @author Tom
 *
 */
public class PdfReader {

    public static void main(String[] args){

        File pdfFile = new File("simple.pdf");
        PDDocument document = null;
        try
        {
            document=PDDocument.load(pdfFile);
            
            AccessPermission ap = document.getCurrentAccessPermission();
            if (!ap.canExtractContent())
            {
                throw new IOException("你没有权限抽取文本");
            }
            // 获取页码
            int pages = document.getNumberOfPages();

            // 读文本内容
            PDFTextStripper stripper=new PDFTextStripper();
            // 设置按顺序输出
            stripper.setSortByPosition(true);
            stripper.setStartPage(1);  //起始页
            stripper.setEndPage(pages);//结束页
            String content = stripper.getText(document);
            System.out.println(content);     
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
```



```java
package pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PdfWriter {

	public static void main(String[] args) {
		createHelloPDF();
	}
	public static void createHelloPDF() {
        PDDocument doc = null;
        PDPage page = null;

        try {
            doc = new PDDocument();
            page = new PDPage();
            doc.addPage(page);
            PDFont font = PDType1Font.HELVETICA_BOLD;
            PDPageContentStream content = new PDPageContentStream(doc, page);
            content.beginText();
            content.setFont(font, 12);
            content.moveTextPositionByAmount(100, 700);
            content.showText("hello world");
            

            content.endText();
            content.close();
            doc.save("test.pdf");
            doc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

```



```java
/*
 * Copyright 2016 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pdfbox;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.schema.DublinCoreSchema;
import org.apache.xmpbox.schema.PDFAIdentificationSchema;
import org.apache.xmpbox.schema.XMPBasicSchema;
import org.apache.xmpbox.type.BadFieldValueException;
import org.apache.xmpbox.xml.XmpSerializer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.transform.TransformerException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.io.IOUtils;

/**
 * 合并两个pdf
 * @author YuXiangKaoChi
 *
 */
public class NewMergePdfs
{
    private static final Log LOG = LogFactory.getLog(NewMergePdfs.class);
    
    public static void main(String[] a) throws Exception 
    {
    	merge();    	
    }
    
    public static void merge() throws Exception
    {
    	FileOutputStream  fos = new FileOutputStream(new File("merge.pdf"));
    	
    	ByteArrayOutputStream mergedPDFOutputStream = null;
    	File file1 = new File("sample1.pdf");
    	File file2 = new File("sample2.pdf");
    	
    	List<InputStream> sources = new ArrayList<InputStream>();
    	
        try
        {
        	sources.add(new FileInputStream(file1));
        	sources.add(new FileInputStream(file2));
        	
            mergedPDFOutputStream = new ByteArrayOutputStream();
           
            //设定来源和目标
            PDFMergerUtility pdfMerger = new PDFMergerUtility();
            pdfMerger.addSources(sources);
            pdfMerger.setDestinationStream(mergedPDFOutputStream);
            
            //设置合并选项
            PDDocumentInformation pdfDocumentInfo = new PDDocumentInformation();
            pdfMerger.setDestinationDocumentInformation(pdfDocumentInfo);

            //合并
            pdfMerger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
            
            fos.write(mergedPDFOutputStream.toByteArray());
            fos.close();
        }
        catch (Exception e)
        {
            throw new IOException("PDF merge problem", e);
        }        
        finally
        {
            for (InputStream source : sources)
            {
                IOUtils.closeQuietly(source);
            }            
            IOUtils.closeQuietly(mergedPDFOutputStream);
            IOUtils.closeQuietly(fos);
        }
    }    
}

```



```java
package pdfbox;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 * 基于老的API来合并 pdf
 * @author YuXiangKaoChi
 *
 */
public class MergePdfs {
	   public static void main(String[] args) throws IOException {

	      //Loading an existing PDF document
	      File file1 = new File("sample1.pdf");
	      PDDocument doc1 = PDDocument.load(file1);

	      File file2 = new File("sample2.pdf");
	      PDDocument doc2 = PDDocument.load(file2);

	      //Instantiating PDFMergerUtility class
	      PDFMergerUtility PDFmerger = new PDFMergerUtility();

	      //Setting the destination file
	      PDFmerger.setDestinationFileName("merge2.pdf");

	      //adding the source files
	      PDFmerger.addSource(file1);
	      PDFmerger.addSource(file2);

	      //Merging the two documents
	      PDFmerger.mergeDocuments();

	      System.out.println("Documents merged");
	      //Closing the documents
	      doc1.close();
	      doc2.close();
	   }
	}
```



```java
package pdfbox;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;

/**
 * 删除某一页 pdf
 * @author YuXiangKaoChi
 *
 */
public class RemovePdf {

	public static void main(String[] args) throws Exception {
		File file = new File("merge.pdf");
		PDDocument document = PDDocument.load(file);

		int noOfPages = document.getNumberOfPages();
		System.out.println("total pages: " + noOfPages);

		// 删除第1页
		document.removePage(1);  // 页码索引从0开始算

		System.out.println("page removed");

		// 另存为新文档
		document.save("merge2.pdf");

		document.close();
	}
}

```



**XDocReport**  
**• XDocReport**  
**– 将docx文档合并输出为其他数据格式(pdf/html/…)**  
**– PdfConverter**  
**– 基于poi和iText完成**  



```java
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import fr.opensagres.xdocreport.itext.extension.font.IFontProvider;
import fr.opensagres.xdocreport.itext.extension.font.ITextFontRegistry;

/**
 * XDocReportTest 将docx文档转为pdf
 * @author Tom
 *
 */
public class XDocReportTest {

	public static void main(String[] args) throws Exception {
		XWPFDocument doc = new XWPFDocument(new FileInputStream("template.docx"));// docx
		PdfOptions options = PdfOptions.create();
		options.fontProvider(new IFontProvider() {
			// 设置中文字体
			public Font getFont(String familyName, String encoding, float size, int style, Color color) {
				try {
					BaseFont bfChinese = BaseFont.createFont(
							"C:\\Program Files (x86)\\Microsoft Office\\root\\VFS\\Fonts\\private\\STSONG.TTF",
							BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
					Font fontChinese = new Font(bfChinese, size, style, color);
					if (familyName != null)
						fontChinese.setFamily(familyName);
					return fontChinese;
				} catch (Throwable e) {
					e.printStackTrace();
					return ITextFontRegistry.getRegistry().getFont(familyName, encoding, size, style, color);
				}
			}
		});
		PdfConverter.getInstance().convert(doc, new FileOutputStream("template.pdf"), options);// pdf
	}
}

```



```java
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.test</groupId>
  <artifactId>MOOC16-08</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  
  <dependencies>
	<dependency>
		<groupId>fr.opensagres.xdocreport</groupId>
	    <artifactId>org.apache.poi.xwpf.converter.pdf</artifactId>
	    <version>1.0.6</version>
	</dependency>
	
	<!-- https://mvnrepository.com/artifact/org.apache.pdfbox/pdfbox -->
	<dependency>
	    <groupId>org.apache.pdfbox</groupId>
	    <artifactId>pdfbox</artifactId>
	    <version>2.0.13</version>
	</dependency>
	<!-- https://mvnrepository.com/artifact/org.apache.pdfbox/xmpbox -->
	<dependency>
	    <groupId>org.apache.pdfbox</groupId>
	    <artifactId>xmpbox</artifactId>
	    <version>2.0.13</version>
	</dependency>
	
	
	<!-- https://mvnrepository.com/artifact/com.itextpdf/itextpdf -->
	<dependency>
	    <groupId>com.itextpdf</groupId>
	    <artifactId>itextpdf</artifactId>
	    <version>5.5.13</version>
	</dependency>
	
	
  </dependencies>
</project>
```

**pdf总结**  
**• pdf操作，可读取解析、合并、删除页面**  
**• 产生pdf和修改pdf，建议先生成docx，再进行转化**  
**• API很多，需要多查询、多练习**  



实例文档在本文件夹内，另外 还有itext实例，有一个压缩包。





