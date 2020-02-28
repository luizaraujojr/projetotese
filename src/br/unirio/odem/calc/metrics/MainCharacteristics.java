package br.unirio.odem.calc.metrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Consumer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.management.modelmbean.XMLParseException;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;

import br.unirio.odem.calc.loader.ProjectLoader;
import br.unirio.odem.controller.ClusteringCalculator;
import br.unirio.odem.model.Project;

/**
 * Publishes metrics and characteristics for a set of projects
 * 
 * @author Marcio
 */
@SuppressWarnings("unused")
public class MainCharacteristics
{
//	private static String JAR_DIRECTORY = "..\\Users\\Marcio\\Desktop\\Resultados Pesquisa\\Resultados Clustering Apache ANT\\versoes";

	private static String JAR_DIRECTORY = new File("").getAbsolutePath() + "\\data\\jar";
	
	private static String ODEM_DIRECTORY = new File("").getAbsolutePath() + "\\data\\odem";
	
		/**
	 * Publishes information about a JAva class
	 */
    private void processClass(InputStream is, String version, String filename, PrintStream ps) throws ClassNotFoundException, IOException
	{
		ClassParser cp = new ClassParser(is, filename);
		JavaClass clazz = cp.parse();
		
		int countAttributes = clazz.getAttributes().length;
		int countMethods = clazz.getMethods().length;
		int countPublicMethods = 0;
		
		for (Method method : clazz.getMethods())
			if ((method.getAccessFlags() & Constants.ACC_PUBLIC) != 0)
				countPublicMethods++;
		
		String packageName = filename.substring(0, filename.lastIndexOf("/")).replace('/', '.');
		String className = filename.substring(filename.lastIndexOf("/")+1, filename.lastIndexOf("."));
		ps.println(version + "\t" + packageName + "\t" + className + "\t" + countAttributes + "\t" + countMethods + "\t" + countPublicMethods);
	}
	
    /**
     * Transverses a JAR file to publish information about its contents
     */
	private void transverseJarFile(String jarFile, String version, PrintStream ps) throws IOException, FileNotFoundException, ClassNotFoundException
	{
		JarFile jar = new JarFile(jarFile);
		Enumeration<JarEntry> e = jar.entries();

		while (e.hasMoreElements())
		{
			JarEntry file = e.nextElement();
			String fileName = file.getName();
			
			if (fileName.endsWith(".class"))
			{
				InputStream is = jar.getInputStream(file);
				processClass(is, version, fileName, ps);
				is.close();
			}
		}

		jar.close();
	}
	
	/**
	 * Publishes the characteristics and metrics for the coupling a given project
	 */
	private static void publishCouplingInformation(String versao, Project project) throws Exception
	{
		ClusteringCalculator cc = new ClusteringCalculator(project, project.getPackageCount());
		int dependencyCount = project.getDependencyCount();
		double mq = cc.calculateModularizationQuality();
		int evm = cc.calculateEVM();
		double aff = cc.calculateAfferentCoupling();
		double eff = cc.calculateEfferentCoupling();
		double lcom5 = cc.calculateLCOM5();
		double cbo = cc.calculateCBO();
		System.out.println(versao + "; D: " + dependencyCount + "; CBO: " + cbo + "; AFF: " + aff + "; EFF: " + eff + "; MQ: " + mq + "; EVM: " + evm + "; LCOM: " + lcom5);
	}

	/**
	 * Publishes the characteristics and metrics for the size of a given project
	 */
	private static void publishSizeInformation(String versao, Project project) throws Exception
	{
		ClusteringCalculator cc = new ClusteringCalculator(project, project.getPackageCount());
		int packageCount = cc.getPackageCount();
		int singleClassPackages = cc.countSingleClassPackages();
		int maximumClassConcentration = cc.getMaximumClassCount();
		double elegance = cc.calculateClassElegance();
		System.out.println(versao + "; P: " + packageCount + "; ELG: " + elegance + "; SCP: " + singleClassPackages + "; CONC: " + maximumClassConcentration);
	}
	
	/**
	 * List all the files from the main directory and its subdirectories
	 * @param inputFile - the main directory
	 * @param files - return a list of files
	 */
	private static void listFilesOnly(File inputFile, List<File> files) {
		File[] listfiles = inputFile.listFiles();
		for (File file: listfiles) {
			if(file.isDirectory()) {
				listFilesOnly(file, files);
			}
			else {
				files.add(file);
			}
		}
	}
	
	/**
	 * Get the file extension of a file
	 * @param file under analysis
	 * @return the extension without the dot
	 */
	private static String getFileExtension(File file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".")+1);
		else return "";
	}
	
	/**
	 * Loads and publishes data for all projects
	 */
	public static final void main(String[] args) throws Exception
	{
		FileOutputStream out = new FileOutputStream("size_metrics.data"); 
		PrintStream ps = new PrintStream(out);
		ps.println("version\tpackage\tclasse\tattr\tmeth\tpmeth");
		
		MainCharacteristics mc = new MainCharacteristics();
		ProjectLoader loader = new ProjectLoader();
				
		
		List<File> files = new ArrayList<File>();
		
		File dir = new File(ODEM_DIRECTORY);
		
		listFilesOnly(dir,files);
		
		for (File file: files) {
			if(file.isDirectory() == false && getFileExtension(file).equals("odem")) {
//				mc.transverseJarFile(JAR_DIRECTORY + "\\jhotdraw\\jhotdraw.jar", "jhotdraw", ps);
				Project project = loader.loadODEMRealVersion(file.getAbsolutePath());
				publishCouplingInformation(file.getName(), project);			
			}			
		}
			
		List<Project> projectsEVM = loader.loadOptimizedVersionsEVM();
		
		for (Project project : projectsEVM)
			publishSizeInformation("EMVopt", project);

		List<Project> projectsMQ = loader.loadOptimizedVersionsMQ();

		for (Project project : projectsMQ)
			publishSizeInformation("MQopt", project);
		
		ps.close();
		System.out.println("Finished!");
	}
}