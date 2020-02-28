package br.unirio.odem.calc.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.management.modelmbean.XMLParseException;

import br.unirio.odem.controller.CDAReader;
import br.unirio.odem.model.Project;
import br.unirio.odem.model.ProjectClass;
import br.unirio.odem.model.ProjectPackage;

/**
 * Class that loads real and optimized versions of Apache Ant
 * 
 * @author Marcio
 */
public class ProjectLoader
{
	private static String OPTIMIZED_DIRECTORY = "\\Users\\Marcio\\Desktop\\Resultados Pesquisa\\Resultados Clustering Apache ANT\\multi run\\v1.9.0\\";
	
	/**
	 * Input directory for real versions
	 */
	
	private static String INPUT_DIRECTORY =  new File(".").getAbsolutePath() + "D:\\Backup\\eclipse-workspace\\projetotese\\data\\odem\\";

	/**
	 * Datas das versoes do Apache Ant
	 */
	private static String[] VERSION_DATES = 
	{
		"2000-07-19",
		"2000-10-24",
		"2001-03-03",
		"2001-09-03",
		"2001-10-11",
		"2002-07-10",
		"2002-10-03",
		"2003-03-03",
		"2003-04-09",
		"2003-08-12",
		"2003-12-18",
		"2004-02-12",
		"2004-07-16",
		"2005-04-28",
		"2005-05-19",
		"2005-06-02",
		"2006-12-13",
		"2008-07-09",
		"2010-02-02",
		"2010-04-30",
		"2010-12-20",
		"2012-03-13",
		"2012-05-23",
		"2013-03-10"
	};
	
	/**
	 * Names of the real versions
	 */
	private static String[] REAL_VERSIONS = 
	{
		"apache-ant-1.1.0",
		"apache-ant-1.2.0",
		"apache-ant-1.3.0",
		"apache-ant-1.4.0",
		"apache-ant-1.4.1",
		"apache-ant-1.5.0",
		"apache-ant-1.5.1",
		"apache-ant-1.5.2",
		"apache-ant-1.5.3",
		"apache-ant-1.5.4",
		"apache-ant-1.6.0",
		"apache-ant-1.6.1",
		"apache-ant-1.6.2",
		"apache-ant-1.6.3",
		"apache-ant-1.6.4",
		"apache-ant-1.6.5",
		"apache-ant-1.7.0",
		"apache-ant-1.7.1",
		"apache-ant-1.8.0",
		"apache-ant-1.8.1",
		"apache-ant-1.8.2",
		"apache-ant-1.8.3",
		"apache-ant-1.8.4",
		"apache-ant-1.9.0"
	};
	
	/**
	 * Known external dependencies for real versions
	 */
	private static String[] REAL_VERSION_EXTERNAL_DEPENDENCIES = 
	{
//		// Versao 1.1.0
//		"org.apache.tools.ant.taskdefs.optional.XalanLiaison",
//		"org.apache.tools.ant.taskdefs.optional.XslpLiaison",
//		// Versao 1.3.0
//		"org.apache.tools.ant.taskdefs.optional.TraXLiaison",
//		// Versao 1.4.0
//		"at.dms.kjc.Main",
//		// Versao 1.5.0
//		"org.apache.tools.ant.taskdefs.optional.Test",
//		"org.apache.tools.ant.taskdefs.Get",
//		"org.apache.tools.ant.taskdefs.email.UUMailer",
//		"org.apache.tools.ant.taskdefs.email.MimeMailer",
//		// Versao 1.6.0
//		"org.apache.tools.ant.launch.AntMain",
//		"org.apache.tools.ant.launch.Locator",
//		"org.apache.tools.ant.util.optional.WeakishReference12",
//		// Versao 1.7.0
//		"org.apache.tools.ant.launch.Launcher",
//		"org.apache.tools.ant.util.java15.ProxyDiagnostics",
//		"org.apache.tools.ant.filters.util.JavaClassHelper",
//		// Versao 1.8.0
//		"org.apache.tools.ant.loader.AntClassLoader5",
//		"org.apache.tools.ant.taskdefs.optional.EchoProperties",
		
		
		"com.sun.awt.AWTUtilities",
        "com.sun.image.codec.jpeg.JPEGCodec",
        "com.sun.image.codec.jpeg.JPEGDecodeParam",
        "com.sun.image.codec.jpeg.JPEGImageDecoder",
        "edu.umd.cs.findbugs.annotations.Nullable",
		"java.beans.PropertyChangeEvent",
		"java.beans.PropertyChangeListener",
        "java.beans.PropertyChangeSupport",
        "java.beans.PropertyVetoException",
        "java.math.BigInteger",
        "java.net.MalformedURLException",
        "java.net.URI",
        "java.net.URISyntaxException",
        "java.net.URL",
        "java.net.URLConnection",
        "java.net.URLDecoder",
        "java.net.URLEncoder",
		"java.security.AccessControlException",
        "java.security.AccessControlException",
		"java.security.AccessController",
        "java.security.Permission",
		"java.security.PrivilegedAction",
        "java.security.PrivilegedAction",
		"java.text.AttributedCharacterIterator",
		"java.text.AttributedCharacterIterator$Attribute",
        "java.text.AttributedString",
        "java.text.CollationKey",
        "java.text.Collator",
		"java.text.DateFormat",
		"java.text.DateFormat$Field",
        "java.text.DecimalFormat",
		"java.text.Format",
		"java.text.Format$Field",
        "java.text.MessageFormat",
        "java.text.NumberFormat",
		"java.text.ParseException",
        "java.text.ParseException",
        "java.text.RuleBasedCollator",
		"java.text.SimpleDateFormat",
		"javax.accessibility.Accessible",
		"javax.accessibility.AccessibleContext",
		"javax.annotation.Nullable",
		"javax.imageio.ImageIO",
		"javax.jnlp.ServiceManager",
		"javax.print.attribute.Attribute",
		"javax.print.attribute.HashPrintRequestAttributeSet",
		"javax.print.attribute.PrintRequestAttributeSet",
		"javax.print.attribute.standard.PrinterResolution",
        "netscape.javascript.JSObject",        
        "org.jdesktop.layout.Baseline",
		"org.jdesktop.layout.LayoutStyle",	
		"sun.awt.CausedFocusEvent",
        "sun.awt.CausedFocusEvent$Cause",
		"sun.security.util.SecurityConstants",
		"java.net.InetAddress",
		"java.net.Socket",
		"java.net.ServerSocket",
		"java.net.URLStreamHandler",
		"FirewallPlugin",
		"com.sun.tools.javac.Main",
		"sun.tools.javac.Main",
		"java.net.PasswordAuthentication",
		"java.net.Authenticator",
		"javax.print.attribute.standard.MediaSize",
		"javax.print.attribute.standard.Chromaticity",
		"javax.print.attribute.standard.OrientationRequested",
		"javax.print.attribute.standard.MediaPrintableArea",
		"javax.print.attribute.standard.MediaSizeName",
		"javax.print.attribute.standard.JobName",
		"bsh.classpath.ClassManagerImpl",
		"java.net.HttpURLConnection",
		"java.nio.charset.Charset",
		"org.xml.sax.Attributes",
		"org.xml.sax.InputSource", 
		"org.xml.sax.helpers.DefaultHandler", 
		"java.security.MessageDigest", 
		"java.nio.CharBuffer",
		"java.nio.ByteBuffer",
		"java.security.NoSuchAlgorithmException",
		"java.nio.charset.MalformedInputException",
		"org.xml.sax.SAXParseException",
		"java.net.URLClassLoader",
		"java.nio.charset.CharacterCodingException",
		"java.nio.charset.UnsupportedCharsetException",
		"java.nio.charset.CharsetDecoder",
		"java.nio.charset.CodingErrorAction",
		"java.nio.charset.CharsetEncoder",
		"java.nio.charset.IllegalCharsetNameException",
		"org.xml.sax.SAXException",
		"org.xml.sax.ErrorHandler",
		"org.xml.sax.EntityResolver",
		"org.xml.sax.DTDHandler",
		"org.xml.sax.ContentHandler",
		"org.xml.sax.XMLReader",
		"org.xml.sax.helpers.XMLReaderFactory",
		"javax.print.attribute.standard.Media",
		"java.beans.BeanInfo",
		"java.beans.Introspector",
		"java.beans.IntrospectionException",
		"java.beans.PropertyDescriptor",
		"java.nio.Buffer",
		"java.nio.channels.FileChannel",
		"java.net.UnknownHostException",
		"java.text.CharacterIterator",
		"java.text.BreakIterator",
		"javax.annotation.Nonnull",
		"javax.annotation.concurrent.ThreadSafe",
		"javax.annotation.concurrent.GuardedBy",
		"java.text.FieldPosition",
		"java.nio.file.FileSystem",
		"java.nio.file.FileSystems",
		"java.nio.file.Path",
		"java.nio.file.Files",
		"java.nio.file.StandardCopyOption",
		"java.nio.file.CopyOption",
		"javax.print.attribute.standard.PageRanges",
		"javax.print.DocPrintJob",
		"javax.print.PrintException",
		"javax.print.Doc",
		"javax.print.PrintService",
		"javax.print.StreamPrintService",
		"javax.print.event.PrintJobEvent",
		"javax.print.event.PrintJobAdapter",
		"javax.print.PrintServiceLookup",
		"javax.print.DocFlavor",
		"javax.print.DocFlavor$SERVICE_FORMATTED",
		"javax.print.event.PrintJobListener",
		"javax.print.attribute.DocAttributeSet",
		"javax.print.SimpleDoc",
		"javax.print.attribute.DocAttribute",
		"javax.print.attribute.HashDocAttributeSet",
		"javax.print.attribute.Size2DSyntax",
		"javax.print.attribute.PrintJobAttribute",
		"javax.print.attribute.PrintRequestAttribute",
		"javax.print.attribute.AttributeSet",
		"javax.print.attribute.standard.NumberUp",
		"javax.print.attribute.standard.PrintQuality",
		"javax.print.attribute.HashAttributeSet",
		"javax.print.attribute.standard.SheetCollate",
		"javax.print.attribute.standard.Copies",
		"javax.print.attribute.standard.JobHoldUntil",
		"javax.print.attribute.standard.Finishings",
		"javax.print.attribute.standard.JobPriority",
		"javax.print.attribute.standard.Sides",
		"javax.print.attribute.standard.MediaTray",
		"javax.print.attribute.standard.PresentationDirection",
		"javax.print.StreamPrintServiceFactory",
		"javax.print.attribute.standard.Destination",
		"javax.print.attribute.IntegerSyntax",
		
		"CH.ifa.draw.standard.AlignCommand$1",
		"org.w3c.dom.DocumentType",
		"org.apache.batik.dom.GenericDOMImplementation",
		"org.apache.batik.svggen.SVGGraphics2D",
		"org.w3c.dom.Document",
		"org.w3c.dom.DOMImplementation",
		"net.roydesign.app.QuitJMenuItem",
		"net.roydesign.app.AboutJMenuItem",
		"ch.randelshofer.quaqua.QuaquaManager",
		"net.roydesign.app.Application",
		"net.roydesign.event.ApplicationEvent",
		"com.apple.mrj.swing.MacFileChooserUI",
		"apple.laf.AquaFileChooserUI",
		"com.apple.mrj.macos.libraries.InterfaceLib",
		"com.apple.mrj.jdirect.MethodClosureUPP",
		"com.apple.mrj.jdirect.MethodClosure",
		"com.apple.mrj.jdirect.Linker",
		"com.apple.mrj.MRJPrefsHandler",
		"com.apple.mrj.MRJQuitHandler",
		"com.apple.mrj.MRJPrintDocumentHandler",
		"com.apple.mrj.MRJOpenDocumentHandler",
		"com.apple.mrj.MRJOpenApplicationHandler",
		"com.apple.mrj.MRJAboutHandler",
		"com.apple.mrj.MRJAboutHandler",
		"com.apple.mrj.MRJApplicationUtils",
		"com.apple.eawt.ApplicationEvent",
		"com.apple.eawt.ApplicationAdapter",
		"com.apple.eawt.ApplicationListener",
		"com.apple.eawt.Application",
		"com.apple.eio.FileManager",
		"edu.stanford.ejalbert.BrowserLauncher",
		"glguerin.io.Pathname",
		"glguerin.util.MacPlatform",
		"glguerin.io.FileForker", 
		"com.apple.mrj.MRJFileUtils",
		"com.apple.mrj.MRJOSType",
		"quicktime.app.view.QTImageProducer",		
		"quicktime.QTSession", 
        "quicktime.util.QTHandle", 
        "quicktime.util.QTUtils", 
        "quicktime.std.image.GraphicsImporter", 
        "quicktime.util.QTHandleRef",
        "quicktime.app.view.GraphicsImporterDrawer",
        "quicktime.app.view.QTImageProducer"
		};

	/**
	 * Returns all real versions
	 */
	public String[] getRealVersions()
	{
		return REAL_VERSIONS;
	}
	
	/**
	 * Returns the dates for all real versions
	 */
	public String[] getRealVersionsDate()
	{
		return VERSION_DATES;
	}
	
	/**
	 * Loads a real version into a project
	 */
	public Project loadRealVersion(String versao) throws XMLParseException
	{
		CDAReader reader = new CDAReader();
		reader.setIgnoredClasses(REAL_VERSION_EXTERNAL_DEPENDENCIES);
		return reader.execute(INPUT_DIRECTORY + versao + ".odem");
	}
	
	
	/**
	 * Loads a ODEM real version into a project
	 */
	public Project loadODEMRealVersion(String file) throws XMLParseException
	{
		CDAReader reader = new CDAReader();
		reader.setIgnoredClasses(REAL_VERSION_EXTERNAL_DEPENDENCIES);
		return reader.execute(file);
	}
	
	
	/**
	 * Loads an optimized version into a project
	 */
	private Project loadOptimizedVersion(String solution) throws XMLParseException
	{
		CDAReader reader = new CDAReader();
		reader.setIgnoredClasses(REAL_VERSION_EXTERNAL_DEPENDENCIES);
		Project project = reader.execute("data\\odem\\apache-ant-1.9.0.odem");
		project.clearPackages();

		String[] tokens = solution.split(" ");
		
		for (int i = 0; i < tokens.length; i++)
		{
			ProjectClass _class = project.getClassIndex(i);
			int packageNumber = Integer.parseInt(tokens[i]);
			
			while (project.getPackageCount() <= packageNumber)
				project.addPackage("" + project.getPackageCount());
			
			ProjectPackage _package = project.getPackageIndex(packageNumber);
			_class.setPackage(_package);
		}
		
		return project;
	}
	
	/**
	 * Loads a set of optimized versions of a project
	 */
	public List<Project> loadOptimizedVersions(String filename) throws XMLParseException
	{
		List<Project> ciclos = new ArrayList<Project>();
		 
		try 
		{
 			BufferedReader br = new BufferedReader(new FileReader(OPTIMIZED_DIRECTORY + filename));
			String line;
 
			while ((line = br.readLine()) != null) 
			{
				int posOpen = line.indexOf('[');
				int posClose = line.indexOf(']');
				
				if (posOpen != -1 && posClose != -1)
					ciclos.add(loadOptimizedVersion(line.substring(posOpen+1, posClose)));
			}
			
			br.close();
 
		} catch (IOException e) 
		{
			e.printStackTrace();
		}

		return ciclos;
	}
	
	/**
	 * Loads the version optimized for EVM
	 */
	public List<Project> loadOptimizedVersionsEVM() throws XMLParseException
	{
		return loadOptimizedVersions("saida evm pseudo.txt");
	}

	/**
	 * Loads the version optimized for MQ
	 */
	public List<Project> loadOptimizedVersionsMQ() throws XMLParseException
	{
		return loadOptimizedVersions("saida mq pseudo.txt");
	}
}
