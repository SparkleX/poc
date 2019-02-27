package com.next.maven;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

@Mojo(name = "gen", requiresDependencyResolution = ResolutionScope.COMPILE, defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class CodeGen extends AbstractMojo {
    
    
    @Parameter( defaultValue = "${project.build.directory}")
    private File outputDirectory;

    @Parameter( defaultValue = "${project.basedir}/target/generated-sources/java")
    private File javaFolder;

    @Parameter( defaultValue = "${project.basedir}/src/main/resources/")
    private File inputFolder;
    
	@Parameter( defaultValue = "${project}")
	private MavenProject project;
    
    public void execute() throws MojoExecutionException, MojoFailureException 
    {
        try 
        {
        	String jarFile="";
        	ClassLoader cl = CodeGen.class.getClassLoader();
            URL[] urls = ((URLClassLoader)cl).getURLs();
            for(URL url: urls)
            {
            	jarFile = url.getFile();
            	if(jarFile.contains("infra-resource-compiler"))
            	{
            		break;
            	}
            }
            
            if(System.getProperty("os.name").toLowerCase().contains("win"))
            {
            	jarFile = jarFile.substring(1);
            }
            ProcessBuilder build = new ProcessBuilder();
            build.command("java","-jar",jarFile,"--output="+javaFolder.getAbsolutePath(),"--input="+inputFolder.getAbsolutePath());
            build.inheritIO();
            Process process = build.start();
            process.waitFor();
            

        
		} catch (Exception e) 
        {
			e.printStackTrace();
			throw new MojoFailureException(e.getMessage());
		}
        
    }

}