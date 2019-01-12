package compiler;

import java.io.File;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.next.infra.schema.Table;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

@SpringBootApplication
@Component
public class TableCompiler implements InitializingBean {
	public static void main(String[] args) throws Exception {
		ApplicationContext appContext = SpringApplication.run(TableCompiler.class, args);
		TableCompiler instance = appContext.getBean(TableCompiler.class);
		instance.run();
	}

	JAXBContext jaxbContext;
	Unmarshaller jaxbUnmarshaller;

	XmlMapper mapper;
	
	TableCompiler() throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Table.class);
		jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		jaxbUnmarshaller.setProperty("com.sun.xml.bind.ObjectFactory",new ObjectFactoryImpl());

        mapper = new XmlMapper();
        mapper.registerModule(new JaxbAnnotationModule());
	}

	void genarateCode(Path path) {
		try {
			File xmlFile = path.toFile();
			Table oTable = (Table) jaxbUnmarshaller.unmarshal(xmlFile);
			
			//Table oTable = mapper.readValue(xmlFile, Table.class);
			StringWriter sw = new StringWriter();
			template.process(oTable, sw);
			String outputFileName = FilenameUtils.getBaseName(xmlFile.getName()) + ".java";
			outputFileName = "../apps-resource-gen/src/main/java/gen/table/Bmo" + outputFileName;
			FileUtils.writeStringToFile(new File(outputFileName), sw.toString(), "utf8");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	void run() throws Exception {
		try (Stream<Path> paths = Files.walk(Paths.get("../apps-resource/src/main/resources/resource/table"))) {
			paths.filter(Files::isRegularFile).forEach(this::genarateCode);
		}
	}

	Template template;

	@Override
	public void afterPropertiesSet() throws Exception {
		File tableTemplateFile = ResourceUtils.getFile("classpath:resource/template/table.ftl");
		String content = new String(Files.readAllBytes(tableTemplateFile.toPath()));

		Configuration cfg = new Configuration(new Version(2, 3, 20));
		StringTemplateLoader dummyLoader = new StringTemplateLoader();
		dummyLoader.putTemplate("table", content);
		cfg.setTemplateLoader(dummyLoader);
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.US);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		template = cfg.getTemplate("table");

	}

}
