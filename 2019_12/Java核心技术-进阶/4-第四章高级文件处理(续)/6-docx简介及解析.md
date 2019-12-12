## docx简介及解析

**Docx 简介**  
**• 以Microsoft Office的doc/docx为主要处理对象。**  
**• Word2003（包括）之前都是doc，文档格式不公开。**  
**• Word2007（包括）之后都是docx，遵循XML路线，文档格式公开。**  
**• docx为主要研究对象**  
**– 文字样式**  
**– 表格**  
**– 图片**  
**– 公式**  



**Docx 功能和处理**  
**• 常见功能**  
**– docx解析**  
**– docx生成（完全生成，模板加部分生成：套打）**  
**• 处理的第三方库**  
**– Jacob, COM4J (Windows 平台)**  
**– POI, docx4j, OpenOffice/Libre Office SDK (免费)**  
**– Aspose (收费)**  
**– 一些开源的OpenXML的包。**  



**POI**  
**• Apache POI**  
**– Apache 出品，必属精品， poi.apache.org**  
**– 可处理docx, xlsx, pptx, visio等office套件**  
**– 纯Java工具包，无需第三方依赖**  
**– 主要类**  
**• XWPFDocument  整个文档对象**  
**• XWPFParagraph  段落**  
**• XWPFRun  一个片段(字体样式相同的一段)**  
**• XWPFPicture 图片**  
**• XWPFTable  表格**  



```
├─src
│  ├─main
│  │  ├─java
│  │  │  └─docx
│  │  │          ImageRead.java
│  │  │          ImageWrite.java
│  │  │          RowSpanTable.java
│  │  │          SpanTable.java
│  │  │          TableRead.java
│  │  │          TableWrite.java
│  │  │          TemplateTest.java
│  │  │          TextRead.java
│  │  │          
│  │  └─resources
│  └─test
│      ├─java
│      └─resources
│  images.docx
│  pom.xml
│  simple.docx
│  simple2.docx
│  simpleTable.docx
│  template.docx
│  template2.docx
│  test.docx
│  test0.jpg
│  test1.jpg
```



```java
package docx;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject;

public class TextRead {

	public static void main(String[] args) throws Exception {
		readDocx();
	}
	
	public static void readDocx() throws Exception {
		InputStream is;
		is = new FileInputStream("test.docx");
		XWPFDocument xwpf = new XWPFDocument(is);
		
		List<IBodyElement> ibs= xwpf.getBodyElements();
		for(IBodyElement ib:ibs)
    	{
			BodyElementType bet = ib.getElementType();
			if(bet== BodyElementType.TABLE)
    		{
				//表格
				System.out.println("table" + ib.getPart());
    		}
			else
			{				
				//段落
				XWPFParagraph para = (XWPFParagraph) ib;
				System.out.println("It is a new paragraph....The indention is " 
				         + para.getFirstLineIndent() + "," + para.getIndentationFirstLine() );
				//System.out.println(para.getCTP().xmlText());
				
				List<XWPFRun> res = para.getRuns();
				//System.out.println("run");
				if(res.size()<=0)
				{
					System.out.println("empty line");
				}
				for(XWPFRun re: res)
				{							
					if(null == re.text()||re.text().length()<=0)
					{
						if(re.getEmbeddedPictures().size()>0)
						{
							System.out.println("image***" + re.getEmbeddedPictures().size());							
						} else
						{
							System.out.println("objects:" + re.getCTR().getObjectList().size());
							if(re.getCTR().xmlText().indexOf("instrText") > 0) {
								System.out.println("there is an equation field");
							}
							else
							{
								//System.out.println(re.getCTR().xmlText());
							}												
						}
					}
					else
					{						
						System.out.println("==="+ re.getCharacterSpacing() + re.text());
					}
				}				
			}
    	}		
		is.close();
	}	
}

```



```java
package docx;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject;

public class TextRead {

	public static void main(String[] args) throws Exception {
		readDocx();
	}
	
	public static void readDocx() throws Exception {
		InputStream is;
		is = new FileInputStream("test.docx");
		XWPFDocument xwpf = new XWPFDocument(is);
		
		List<IBodyElement> ibs= xwpf.getBodyElements();
		for(IBodyElement ib:ibs)
    	{
			BodyElementType bet = ib.getElementType();
			if(bet== BodyElementType.TABLE)
    		{
				//表格
				System.out.println("table" + ib.getPart());
    		}
			else
			{				
				//段落
				XWPFParagraph para = (XWPFParagraph) ib;
				System.out.println("It is a new paragraph....The indention is " 
				         + para.getFirstLineIndent() + "," + para.getIndentationFirstLine() );
				//System.out.println(para.getCTP().xmlText());
				
				List<XWPFRun> res = para.getRuns();
				//System.out.println("run");
				if(res.size()<=0)
				{
					System.out.println("empty line");
				}
				for(XWPFRun re: res)
				{							
					if(null == re.text()||re.text().length()<=0)
					{
						if(re.getEmbeddedPictures().size()>0)
						{
							System.out.println("image***" + re.getEmbeddedPictures().size());							
						} else
						{
							System.out.println("objects:" + re.getCTR().getObjectList().size());
							if(re.getCTR().xmlText().indexOf("instrText") > 0) {
								System.out.println("there is an equation field");
							}
							else
							{
								//System.out.println(re.getCTR().xmlText());
							}												
						}
					}
					else
					{						
						System.out.println("==="+ re.getCharacterSpacing() + re.text());
					}
				}				
			}
    	}		
		is.close();
	}	
}

```

```java
package docx;

/**
 * 本类完成docx的图片保存工作
 */
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;


public class ImageWrite {

    public static void main(String[] args) throws Exception {
        XWPFDocument doc = new XWPFDocument();
        XWPFParagraph p = doc.createParagraph();

        XWPFRun r = p.createRun();
        
        String[] imgFiles = new String[2];
        
        imgFiles[0] = "c:/temp/ecnu.jpg";
        imgFiles[1] = "c:/temp/shida.jpg";        		

        for(String imgFile : imgFiles) {
            int format;

            if(imgFile.endsWith(".emf")) format = XWPFDocument.PICTURE_TYPE_EMF;
            else if(imgFile.endsWith(".wmf")) format = XWPFDocument.PICTURE_TYPE_WMF;
            else if(imgFile.endsWith(".pict")) format = XWPFDocument.PICTURE_TYPE_PICT;
            else if(imgFile.endsWith(".jpeg") || imgFile.endsWith(".jpg")) format = XWPFDocument.PICTURE_TYPE_JPEG;
            else if(imgFile.endsWith(".png")) format = XWPFDocument.PICTURE_TYPE_PNG;
            else if(imgFile.endsWith(".dib")) format = XWPFDocument.PICTURE_TYPE_DIB;
            else if(imgFile.endsWith(".gif")) format = XWPFDocument.PICTURE_TYPE_GIF;
            else if(imgFile.endsWith(".tiff")) format = XWPFDocument.PICTURE_TYPE_TIFF;
            else if(imgFile.endsWith(".eps")) format = XWPFDocument.PICTURE_TYPE_EPS;
            else if(imgFile.endsWith(".bmp")) format = XWPFDocument.PICTURE_TYPE_BMP;
            else if(imgFile.endsWith(".wpg")) format = XWPFDocument.PICTURE_TYPE_WPG;
            else {
                System.err.println("Unsupported picture: " + imgFile +
                        ". Expected emf|wmf|pict|jpeg|png|dib|gif|tiff|eps|bmp|wpg");
                continue;
            }

            r.setText(imgFile);
            r.addBreak();
            r.addPicture(new FileInputStream(imgFile), format, imgFile, Units.toEMU(200), Units.toEMU(200)); // 200x200 pixels
            r.addBreak(BreakType.PAGE);
        }

        FileOutputStream out = new FileOutputStream("images.docx");
        doc.write(out);
        out.close();
    }


}
```

```java
package docx;

/**
 * 本类完成docx的表格内容读取
 */
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;


public class TableRead {
	public static void main(String[] args) throws Exception {
		testTable();
	}
	
	public static void testTable() throws Exception {
		InputStream is = new FileInputStream("simple2.docx");
		XWPFDocument xwpf = new XWPFDocument(is);
		List<XWPFParagraph> paras = xwpf.getParagraphs();
		//List<POIXMLDocumentPart> pdps = xwpf.getRelations();
		
		List<IBodyElement> ibs= xwpf.getBodyElements();
		for(IBodyElement ib:ibs)
    	{
			BodyElementType bet = ib.getElementType();
			if(bet== BodyElementType.TABLE)
    		{
				//表格
				System.out.println("table" + ib.getPart());
				XWPFTable table = (XWPFTable) ib;
	    		List<XWPFTableRow> rows=table.getRows(); 
	    		 //读取每一行数据
	    		for (int i = 0; i < rows.size(); i++) {
	    			XWPFTableRow  row = rows.get(i);
	    			//读取每一列数据
		    	    List<XWPFTableCell> cells = row.getTableCells(); 
		    	    for (int j = 0; j < cells.size(); j++) {
		    	    	XWPFTableCell cell=cells.get(j);
		    	    	System.out.println(cell.getText());
		    	    	List<XWPFParagraph> cps = cell.getParagraphs();
		    	    	System.out.println(cps.size());
					}
	    		}
    		}
			else
			{				
				//段落
				XWPFParagraph para = (XWPFParagraph) ib;
				System.out.println("It is a new paragraph....The indention is " 
				   + para.getFirstLineIndent() + "," + para.getIndentationFirstLine() + "," 
				   + para.getIndentationHanging()+"," + para.getIndentationLeft() + "," 
				   + para.getIndentationRight() + "," + para.getIndentFromLeft() + ","
				   + para.getIndentFromRight()+"," + para.getAlignment().getValue());
				
				//System.out.println(para.getAlignment());
				//System.out.println(para.getRuns().size());

				List<XWPFRun> res = para.getRuns();
				System.out.println("run");
				if(res.size()<=0)
				{
					System.out.println("empty line");
				}
				for(XWPFRun re: res)
				{					
					if(null == re.text()||re.text().length()<=0)
					{
						if(re.getEmbeddedPictures().size()>0)
						{
							System.out.println("image***" + re.getEmbeddedPictures().size());
							
						}
						else
						{
							System.out.println("objects:" + re.getCTR().getObjectList().size());
							System.out.println(re.getCTR().xmlText());
												
						}
					}
					else
					{
						System.out.println("===" + re.text());
					}
				}
				
			}
    	}		
		is.close();
	}
}

```

```java
package docx;

/*
 * 本类测试写入表格
 */

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

public class TableWrite {

 public static void main(String[] args) throws Exception {
 	try {
 		createSimpleTable();
 	}
 	catch(Exception e) {
 		System.out.println("Error trying to create simple table.");
 		throw(e);
 	} 	
 }

 public static void createSimpleTable() throws Exception {
     XWPFDocument doc = new XWPFDocument();

     try {
         XWPFTable table = doc.createTable(3, 3);

         table.getRow(1).getCell(1).setText("表格示例");

         XWPFParagraph p1 = table.getRow(0).getCell(0).getParagraphs().get(0);

         XWPFRun r1 = p1.createRun();
         r1.setBold(true);
         r1.setText("The quick brown fox");
         r1.setItalic(true);
         r1.setFontFamily("Courier");
         r1.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
         r1.setTextPosition(100);

         table.getRow(2).getCell(2).setText("only text");

         OutputStream out = new FileOutputStream("simpleTable.docx");
         try {
             doc.write(out);
         } finally {
             out.close();
         }
     } finally {
         doc.close();
     }
 } 
}
```



套打功能：

```java
package docx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;


public class TemplateTest {

	public static void main(String[] args) throws Exception {
		XWPFDocument doc = openDocx("template.docx");//导入模板文件
		Map<String, Object> params = new HashMap<>();//文字类 key-value
		params.put("${name}", "Tom");
		params.put("${sex}", "男");
		Map<String,String> picParams = new HashMap<>();//图片类 key-url
		picParams.put("${pic}", "c:/temp/ecnu.jpg");
		List<IBodyElement> ibes = doc.getBodyElements();
		for (IBodyElement ib : ibes) {
			if (ib.getElementType() == BodyElementType.TABLE) {
				replaceTable(ib, params, picParams, doc);
			}
		}
		writeDocx(doc, new FileOutputStream("template2.docx"));//输出
	}
	
	public static XWPFDocument openDocx(String url) {
		InputStream in = null;
		try {
			in = new FileInputStream(url);
			XWPFDocument doc = new XWPFDocument(in);
			return doc;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static void writeDocx(XWPFDocument outDoc, OutputStream out) {
		try {
			outDoc.write(out);
			out.flush();
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Matcher matcher(String str) {
		Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		return matcher;
	}
	
	/**
	 * 写入image
	 * @param run
	 * @param imgFile
	 * @param doc
	 * @throws InvalidFormatException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void replacePic(XWPFRun run,  String imgFile,  XWPFDocument doc) throws Exception {
		int format;
		if (imgFile.endsWith(".emf"))
			format = Document.PICTURE_TYPE_EMF;
		else if (imgFile.endsWith(".wmf"))
			format = Document.PICTURE_TYPE_WMF;
		else if (imgFile.endsWith(".pict"))
			format = Document.PICTURE_TYPE_PICT;
		else if (imgFile.endsWith(".jpeg") || imgFile.endsWith(".jpg"))
			format = Document.PICTURE_TYPE_JPEG;
		else if (imgFile.endsWith(".png"))
			format = Document.PICTURE_TYPE_PNG;
		else if (imgFile.endsWith(".dib"))
			format = Document.PICTURE_TYPE_DIB;
		else if (imgFile.endsWith(".gif"))
			format = Document.PICTURE_TYPE_GIF;
		else if (imgFile.endsWith(".tiff"))
			format = Document.PICTURE_TYPE_TIFF;
		else if (imgFile.endsWith(".eps"))
			format = Document.PICTURE_TYPE_EPS;
		else if (imgFile.endsWith(".bmp"))
			format = Document.PICTURE_TYPE_BMP;
		else if (imgFile.endsWith(".wpg"))
			format = Document.PICTURE_TYPE_WPG;
		else {
			System.err.println(
					"Unsupported picture: " + imgFile + ". Expected emf|wmf|pict|jpeg|png|dib|gif|tiff|eps|bmp|wpg");
			return;
		}
		if(imgFile.startsWith("http")||imgFile.startsWith("https")){
			run.addPicture(new URL(imgFile).openConnection().getInputStream(), format, "rpic",Units.toEMU(100),Units.toEMU(100));
		}else{
			run.addPicture(new FileInputStream(imgFile), format, "rpic",Units.toEMU(100),Units.toEMU(100));
		}
	}
	
	/**
	 * 替换表格内占位符
	 * @param para 表格对象
	 * @param params 文字替换map
	 * @param picParams 图片替换map
	 * @param indoc
	 * @throws Exception 
	 */
	public static void replaceTable(IBodyElement para ,Map<String, Object> params, 
			Map<String, String> picParams, XWPFDocument indoc)
			throws Exception {
		Matcher matcher;
		XWPFTable table;
		List<XWPFTableRow> rows;
		List<XWPFTableCell> cells;
		table = (XWPFTable) para;
		rows = table.getRows();
		for (XWPFTableRow row : rows) {
			cells = row.getTableCells();
			int cellsize = cells.size();
			int cellcount = 0;
			for(cellcount = 0; cellcount<cellsize;cellcount++){
				XWPFTableCell cell = cells.get(cellcount);
				String runtext = "";
				List<XWPFParagraph> ps = cell.getParagraphs();
				for (XWPFParagraph p : ps) {
					for(XWPFRun run : p.getRuns()){
						runtext = run.text();
						matcher = matcher(runtext);
						if (matcher.find()) {
							if (picParams != null) {
								for (String pickey : picParams.keySet()) {
									if (matcher.group().equals(pickey)) {
										run.setText("",0);
										replacePic(run, picParams.get(pickey), indoc);
									}
								}
							}
							if (params != null) {
								for (String pickey : params.keySet()) {
									if (matcher.group().equals(pickey)) {
										run.setText(params.get(pickey)+"",0);
									}
								}
							}
						}
					}
				}
			}
		}
	}
}

```

```java
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.test</groupId>
  <artifactId>MOOC16-06</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  
  <dependencies>
  	<dependency>
	    <groupId>org.apache.poi</groupId>
	    <artifactId>poi</artifactId>
    	<version>3.14</version>
	</dependency>
	<dependency>
	    <groupId>org.apache.poi</groupId>
	    <artifactId>ooxml-schemas</artifactId>
	    <version>1.3</version>
	</dependency>
	<dependency>
	    <groupId>org.apache.poi</groupId>
	    <artifactId>poi-ooxml-schemas</artifactId>
	    <version>3.14</version>
	</dependency>
	<dependency>
	    <groupId>org.apache.xmlbeans</groupId>
	    <artifactId>xmlbeans</artifactId>
	    <version>2.6.0</version>
	</dependency>
	<dependency>
	    <groupId>org.apache.poi</groupId>
	    <artifactId>poi-examples</artifactId>
	    <version>3.14</version>
	</dependency>
  </dependencies>
</project>
```



**Doc/Docx处理总结**  
**• 不同的Office工具，产生出来的docx文件格式不兼容**  
**• 不同的第三方包，能够解析和生成的内容也不同，使用的类也不同**  
**• doc/docx功能非常非常多，第三方包不是万能的，也存在无法解析的情况**  
**• API很多，需要多查询、多练习**  



实例文档放在：本文件夹下